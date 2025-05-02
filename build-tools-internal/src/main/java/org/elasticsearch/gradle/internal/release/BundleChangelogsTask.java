/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the "Elastic License
 * 2.0", the "GNU Affero General Public License v3.0 only", and the "Server Side
 * Public License v 1"; you may not use this file except in compliance with, at
 * your election, the "Elastic License 2.0", the "GNU Affero General Public
 * License v3.0 only", or the "Server Side Public License, v 1".
 */

package org.elasticsearch.gradle.internal.release;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import org.elasticsearch.gradle.Version;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.file.Directory;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.file.FileCollection;
import org.gradle.api.file.RegularFile;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.tasks.InputDirectory;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.options.Option;
import org.gradle.process.ExecOperations;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import javax.annotation.Nullable;
import javax.inject.Inject;

import static java.util.stream.Collectors.toList;

public class BundleChangelogsTask extends DefaultTask {
    private static final Logger LOGGER = Logging.getLogger(BundleChangelogsTask.class);

    private final ConfigurableFileCollection changelogs;

    private final RegularFileProperty bundleFile;
    private final DirectoryProperty changelogDirectory;

    private final GitWrapper gitWrapper;

    @Nullable
    private String branch;
    private boolean updateExisting;
    private boolean finalize;

    @Option(
        option = "update-existing",
        description = "Only update entries that are already in the bundle. Useful for updating the bundle after a BC has been cut."
    )
    public void setUpdateExisting(boolean updateExisting) {
        this.updateExisting = updateExisting;
    }

    @Option(option = "branch", description = "Branch (or other ref) to use for generating the changelog bundle.")
    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Option(option = "finalize", description = "Specify that the bundle is finalized, i.e. that the version has been released.")
    public void setFinalize(boolean finalize) {
        this.finalize = finalize;
    }

    private static final ObjectMapper yamlMapper = new ObjectMapper(
        new YAMLFactory().enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
            .disable(YAMLGenerator.Feature.SPLIT_LINES)
            .enable(YAMLGenerator.Feature.INDENT_ARRAYS_WITH_INDICATOR)
            .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
            .enable(YAMLGenerator.Feature.LITERAL_BLOCK_STYLE)
    ).setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @Inject
    public BundleChangelogsTask(ObjectFactory objectFactory, ExecOperations execOperations) {
        changelogs = objectFactory.fileCollection();

        bundleFile = objectFactory.fileProperty();
        changelogDirectory = objectFactory.directoryProperty();

        gitWrapper = new GitWrapper(execOperations);
    }

    @TaskAction
    public void executeTask() throws IOException {
        if (branch == null) {
            throw new IllegalArgumentException("'branch' not specified.");
        }

        final String upstreamRemote = gitWrapper.getUpstream();

        try {
            checkoutChangelogs(gitWrapper, upstreamRemote, branch);
            Properties props = new Properties();
            props.load(new StringReader(gitWrapper.runCommand("git", "show", branch + ":build-tools-internal/version.properties")));
            String version = props.getProperty("elasticsearch");

            LOGGER.info("Finding changelog files...");

            List<ChangelogEntry> entries = this.changelogDirectory.getAsFileTree()
                .getFiles()
                .stream()
                .map(ChangelogEntry::parse)
                .sorted(Comparator.comparing(ChangelogEntry::getPr))
                .collect(toList());

            ChangelogBundle existingBundle = null;

            if (updateExisting) {
                var existingBundleFile = new File("docs/release-notes/changelog-bundles/" + version + ".yml");
                if (existingBundleFile.exists()) {
                    var bundle = ChangelogBundle.parse(existingBundleFile);
                    existingBundle = bundle;
                    entries = entries.stream()
                        .filter(e -> bundle.changelogs().stream().anyMatch(c -> c.getPr().equals(e.getPr())))
                        .toList();
                }
            }

            var isReleased = false;
            if (finalize) {
                isReleased = true;
            } else if (existingBundle != null) {
                isReleased = existingBundle.released();
            }

            ChangelogBundle bundle = new ChangelogBundle(version, isReleased, Instant.now().toString(), entries);

            yamlMapper.writeValue(new File("docs/release-notes/changelog-bundles/" + version + ".yml"), bundle);
        } finally {
            gitWrapper.runCommand("git", "restore", "-s@", "-SW", "--", "docs/changelog");
        }
    }

    private static void checkoutChangelogs(GitWrapper gitWrapper, String upstream, String ref) {
        gitWrapper.updateRemote(upstream);
        // TODO check for changes first
        gitWrapper.runCommand("rm", "-rf", "docs/changelog");
        var refSpec = upstream + "/" + ref;
        if (ref.contains("upstream/")) {
            refSpec = ref.replace("upstream/", upstream + "/");
        } else if (ref.matches("^[0-9a-f]+$")) {
            refSpec = ref;
        }
        gitWrapper.runCommand("git", "checkout", refSpec, "--", "docs/changelog");
    }

    @InputDirectory
    public DirectoryProperty getChangelogDirectory() {
        return changelogDirectory;
    }

    public void setChangelogDirectory(Directory dir) {
        this.changelogDirectory.set(dir);
    }

    @InputFiles
    public FileCollection getChangelogs() {
        return changelogs;
    }

    public void setChangelogs(FileCollection files) {
        this.changelogs.setFrom(files);
    }

    @OutputFile
    public RegularFileProperty getBundleFile() {
        return bundleFile;
    }

    public void setBundleFile(RegularFile file) {
        this.bundleFile.set(file);
    }
}
