---
navigation_title: "Elasticsearch"
mapped_pages:
  - https://www.elastic.co/guide/en/elasticsearch/reference/current/es-connectors-release-notes.html
  - https://www.elastic.co/guide/en/elasticsearch/reference/current/es-release-notes.html
  - https://www.elastic.co/guide/en/elasticsearch/reference/master/release-notes-9.1.0.html
  - https://www.elastic.co/guide/en/elasticsearch/reference/master/migrating-9.1.html
---

# Elasticsearch release notes [elasticsearch-release-notes]

Review the changes, fixes, and more in each version of Elasticsearch.

To check for security updates, go to [Security announcements for the Elastic stack](https://discuss.elastic.co/c/announcements/security-announcements/31).

% Release notes include only features, enhancements, and fixes. Add breaking changes, deprecations, and known issues to the applicable release notes sections.

% ## version.next [felasticsearch-next-release-notes]
% **Release date:** Month day, year

% ### Features and enhancements [elasticsearch-next-features-enhancements]
% *

% ### Fixes [elasticsearch-next-fixes]
% *

## 9.1.0 [elasticsearch-910-release-notes]
**Release date:** April 01, 2025

### Breaking changes [elasticsearch-910-breaking]

TLS:
* Drop `TLS_RSA` cipher support for JDK 24 [#123600](https://github.com/elastic/elasticsearch/pull/123600)

### Deprecations [elasticsearch-910-deprecation]

Machine Learning:
* Add deprecation warning for flush API [#121667](https://github.com/elastic/elasticsearch/pull/121667) (issue: {es-issue}121506[#121506])

Search:
* Deprecate Behavioral Analytics CRUD apis [#122960](https://github.com/elastic/elasticsearch/pull/122960)

### Features and enhancements [elasticsearch-910-features-enhancements]

Allocation:
* Introduce `AllocationBalancingRoundSummaryService` [#120957](https://github.com/elastic/elasticsearch/pull/120957)

Authorization:
* Do not fetch reserved roles from native store when Get Role API is called [#121971](https://github.com/elastic/elasticsearch/pull/121971)

Data streams:
* Add index mode to get data stream API [#122486](https://github.com/elastic/elasticsearch/pull/122486)

ES|QL:
* Add initial grammar and changes for FORK [#121948](https://github.com/elastic/elasticsearch/pull/121948)
* Allow partial results in ES|QL [#121942](https://github.com/elastic/elasticsearch/pull/121942)
* Allow skip shards with `_tier` and `_index` in ES|QL [#123728](https://github.com/elastic/elasticsearch/pull/123728)
* Fix Driver status iterations and `cpuTime` [#123290](https://github.com/elastic/elasticsearch/pull/123290) (issue: {es-issue}122967[#122967])
* Implement runtime skip_unavailable=true [#121240](https://github.com/elastic/elasticsearch/pull/121240)
* Initial support for unmapped fields [#119886](https://github.com/elastic/elasticsearch/pull/119886)
* Introduce `allow_partial_results` setting in ES|QL [#122890](https://github.com/elastic/elasticsearch/pull/122890)
* Introduce a pre-mapping logical plan processing step [#121260](https://github.com/elastic/elasticsearch/pull/121260)
* Render `aggregate_metric_double` [#122660](https://github.com/elastic/elasticsearch/pull/122660)
* Retry ES|QL node requests on shard level failures [#120774](https://github.com/elastic/elasticsearch/pull/120774)
* Support partial results in CCS in ES|QL [#122708](https://github.com/elastic/elasticsearch/pull/122708)
* Support subset of metrics in aggregate metric double [#121805](https://github.com/elastic/elasticsearch/pull/121805)

Health:
* Add health indicator impact to `HealthPeriodicLogger` [#122390](https://github.com/elastic/elasticsearch/pull/122390)

ILM+SLM:
* Improve SLM Health Indicator to cover missing snapshot [#121370](https://github.com/elastic/elasticsearch/pull/121370)

Inference:
* [Inference API] Rename `model_id` prop to model in EIS sparse inference request body [#122272](https://github.com/elastic/elasticsearch/pull/122272)

Infra/Core:
* Improve size limiting string message [#122427](https://github.com/elastic/elasticsearch/pull/122427)

Ingest Node:
* Allow setting the `type` in the reroute processor [#122409](https://github.com/elastic/elasticsearch/pull/122409) (issue: {es-issue}121553[#121553])
* Run `TransportEnrichStatsAction` on local node [#121256](https://github.com/elastic/elasticsearch/pull/121256)

Machine Learning:
* Adding elser default endpoint for EIS [#122066](https://github.com/elastic/elasticsearch/pull/122066)
* Adding endpoint creation validation to `ElasticInferenceService` [#117642](https://github.com/elastic/elasticsearch/pull/117642)
* Adding integration for VoyageAI embeddings and rerank models [#122134](https://github.com/elastic/elasticsearch/pull/122134)
* Adding support for binary embedding type to Cohere service embedding type [#120751](https://github.com/elastic/elasticsearch/pull/120751)
* Adding support for specifying embedding type to Jina AI service settings [#121548](https://github.com/elastic/elasticsearch/pull/121548)
* ES|QL `change_point` processing command [#120998](https://github.com/elastic/elasticsearch/pull/120998)

Mapping:
* Enable synthetic recovery source by default when synthetic source is enabled. Using synthetic recovery source significantly improves indexing performance compared to regular recovery source. [#122615](https://github.com/elastic/elasticsearch/pull/122615) (issue: {es-issue}116726[#116726])
* Enable the use of nested field type with index.mode=time_series [#122224](https://github.com/elastic/elasticsearch/pull/122224) (issue: {es-issue}120874[#120874])
* Improved error message when index field type is invalid [#122860](https://github.com/elastic/elasticsearch/pull/122860)
* Introduce `FallbackSyntheticSourceBlockLoader` and apply it to keyword fields [#119546](https://github.com/elastic/elasticsearch/pull/119546)
* Store arrays offsets for ip fields natively with synthetic source [#122999](https://github.com/elastic/elasticsearch/pull/122999)
* Store arrays offsets for keyword fields natively with synthetic source instead of falling back to ignored source. [#113757](https://github.com/elastic/elasticsearch/pull/113757)
* Use `FallbackSyntheticSourceBlockLoader` for `unsigned_long` and `scaled_float` fields [#122637](https://github.com/elastic/elasticsearch/pull/122637)
* Use `FallbackSyntheticSourceBlockLoader` for boolean and date fields [#124050](https://github.com/elastic/elasticsearch/pull/124050)
* Use `FallbackSyntheticSourceBlockLoader` for number fields [#122280](https://github.com/elastic/elasticsearch/pull/122280)

Search:
* Account for the `SearchHit` source in circuit breaker [#121920](https://github.com/elastic/elasticsearch/pull/121920) (issue: {es-issue}89656[#89656])
* Optionally allow text similarity reranking to fail [#121784](https://github.com/elastic/elasticsearch/pull/121784)

Stats:
* Run XPack usage actions on local node [#122933](https://github.com/elastic/elasticsearch/pull/122933)

Store:
* Abort pending deletion on `IndicesService` close [#123569](https://github.com/elastic/elasticsearch/pull/123569)

Vector Search:
* Adds implementations of dotProduct and cosineSimilarity painless methods to operate on float vectors for byte fields [#122381](https://github.com/elastic/elasticsearch/pull/122381) (issue: {es-issue}117274[#117274])

Watcher:
* Run `TransportGetWatcherSettingsAction` on local node [#122857](https://github.com/elastic/elasticsearch/pull/122857)

### Fixes [elasticsearch-910-fixes]

Allocation:
* `DesiredBalanceReconciler` always returns `AllocationStats` [#122458](https://github.com/elastic/elasticsearch/pull/122458)

Analysis:
* Adjust exception thrown when unable to load hunspell dict [#123743](https://github.com/elastic/elasticsearch/pull/123743)

Data streams:
* Updating `TransportRolloverAction.checkBlock` so that non-write-index blocks do not prevent data stream rollover [#122905](https://github.com/elastic/elasticsearch/pull/122905)

ES|QL:
* Add support to VALUES aggregation for spatial types [#122886](https://github.com/elastic/elasticsearch/pull/122886) (issue: {es-issue}122413[#122413])
* Avoid over collecting in Limit or Lucene Operator [#123296](https://github.com/elastic/elasticsearch/pull/123296)
* ESQL: Fix inconsistent results in using scaled_float field [#122586](https://github.com/elastic/elasticsearch/pull/122586) (issue: {es-issue}122547[#122547])
* ESQL: Remove estimated row size assertion [#122762](https://github.com/elastic/elasticsearch/pull/122762) (issue: {es-issue}121535[#121535])
* Fix early termination in `LuceneSourceOperator` [#123197](https://github.com/elastic/elasticsearch/pull/123197)
* Fix function registry concurrency issues on constructor [#123492](https://github.com/elastic/elasticsearch/pull/123492) (issue: {es-issue}123430[#123430])
* Fix functions emitting warnings with no source [#122821](https://github.com/elastic/elasticsearch/pull/122821) (issue: {es-issue}122588[#122588])
* Implicit numeric casting for CASE/GREATEST/LEAST [#122601](https://github.com/elastic/elasticsearch/pull/122601) (issue: {es-issue}121890[#121890])
* Reduce iteration complexity for plan traversal [#123427](https://github.com/elastic/elasticsearch/pull/123427)
* Remove duplicated nested commands [#123085](https://github.com/elastic/elasticsearch/pull/123085)
* Revive inlinestats [#122257](https://github.com/elastic/elasticsearch/pull/122257)
* Use a must boolean statement when pushing down to Lucene when scoring is also needed [#124001](https://github.com/elastic/elasticsearch/pull/124001) (issue: {es-issue}123967[#123967])

Engine:
* Hold store reference in `InternalEngine#performActionWithDirectoryReader(...)` [#123010](https://github.com/elastic/elasticsearch/pull/123010) (issue: {es-issue}122974[#122974])

Indices APIs:
* Updates the deprecation info API to not warn about system indices and data streams [#122951](https://github.com/elastic/elasticsearch/pull/122951)

Infra/Core:
* Include data streams when converting an existing resource to a system resource [#121392](https://github.com/elastic/elasticsearch/pull/121392)
* Reduce Data Loss in System Indices Migration [#121327](https://github.com/elastic/elasticsearch/pull/121327)
* System Index Migration Failure Results in a Non-Recoverable State [#122326](https://github.com/elastic/elasticsearch/pull/122326)

Ingest Node:
* Fix geoip databases index access after system feature migration (again) [#122938](https://github.com/elastic/elasticsearch/pull/122938)
* apm-data: Use representative count as event.success_count if available [#119995](https://github.com/elastic/elasticsearch/pull/119995)

Machine Learning:
* Add `ElasticInferenceServiceCompletionServiceSettings` [#123155](https://github.com/elastic/elasticsearch/pull/123155)
* Add enterprise license check to inference action for semantic text fields [#122293](https://github.com/elastic/elasticsearch/pull/122293)
* Fix serialising the inference update request [#122278](https://github.com/elastic/elasticsearch/pull/122278)
* Retry on streaming errors [#123076](https://github.com/elastic/elasticsearch/pull/123076)
* Set Connect Timeout to 5s [#123272](https://github.com/elastic/elasticsearch/pull/123272)
* Updates to allow using Cohere binary embedding response in semantic search queries [#121827](https://github.com/elastic/elasticsearch/pull/121827)

Search:
* Fix handling of auto expand replicas for stateless indices [#122365](https://github.com/elastic/elasticsearch/pull/122365)
* Handle search timeout in `SuggestPhase` [#122357](https://github.com/elastic/elasticsearch/pull/122357) (issue: {es-issue}122186[#122186])

Snapshot/Restore:
* Fork post-snapshot-delete cleanup off master thread [#122731](https://github.com/elastic/elasticsearch/pull/122731)
* Limit number of suppressed S3 deletion errors [#123630](https://github.com/elastic/elasticsearch/pull/123630) (issue: {es-issue}123354[#123354])

Suggesters:
* Return an empty suggestion when suggest phase times out [#122575](https://github.com/elastic/elasticsearch/pull/122575) (issue: {es-issue}122548[#122548])
* Support duplicate suggestions in completion field [#121324](https://github.com/elastic/elasticsearch/pull/121324) (issue: {es-issue}82432[#82432])

Transform:
* If the Transform is configured to write to an alias as its destination index, when the delete_dest_index parameter is set to true, then the Delete API will now delete the write index backing the alias [#122074](https://github.com/elastic/elasticsearch/pull/122074) (issue: {es-issue}121913[#121913])

Vector Search:
* Knn vector rescoring to sort score docs [#122653](https://github.com/elastic/elasticsearch/pull/122653) (issue: {es-issue}119711[#119711])

### Upgrades [elasticsearch-910-upgrade]

Snapshot/Restore:
* Upgrade AWS SDK to v1.12.746 [#122431](https://github.com/elastic/elasticsearch/pull/122431)


