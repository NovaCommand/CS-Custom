# AGENTS.md

## Project Snapshot
- This repo builds a CloudStream3 plugin set; currently the active module is `KissKH/`.
- GitHub repository: `https://github.com/NovaCommand/CS-Custom`.
- Root Gradle scripts auto-discover plugin modules (`settings.gradle.kts`) and only package enabled ones (`status = 1` in each module `build.gradle.kts`).
- Main provider implementation lives in `KissKH/src/main/kotlin/com/byayzen/KissKH.kt`; plugin entrypoint is `KissKHPlugin.kt`.
- Existing AI/project guidance discovered from the requested glob: `README.md` (no extra agent rule files were found in that search set).

## Architecture You Need First
- **Build layer:** root `build.gradle.kts` defines common Android/Kotlin config and custom tasks; module scripts provide plugin metadata via `cloudstream { ... }`.
- **Plugin registration:** `KissKHPlugin.kt` uses `@CloudstreamPlugin` and registers providers in `load(context)`.
- **Provider flow (`KissKH.kt`):**
  1) home/search endpoints build UI cards,
  2) `load(url)` fetches title details and episode list,
  3) `loadLinks(...)` resolves streams and subtitles for playback.
- **Subtitle crypto boundary:** `SubDecryptor.kt` handles AES/CBC decryption used by `KissKH.kt` when subtitle URLs are encrypted `.txt` payloads.
- **Repo distribution metadata:** `repo.json` + `plugins.json` point to published `.cs3` artifacts and plugin index URLs.

## Critical Workflows (Windows PowerShell)
- Build everything enabled:
  - `./gradlew.bat build`
- Build only active plugins via custom task:
  - `./gradlew.bat derle`
- Build only the KissKH module:
  - `./gradlew.bat :KissKH:build`
- Clean artifacts:
  - `./gradlew.bat clean`
- Use `--info` (or `--stacktrace`) when debugging Gradle/plugin packaging issues.

## Project-Specific Patterns To Follow
- Keep provider classes extending `MainAPI` with the CloudStream contract methods (`getMainPage`, `search`, `load`, `loadLinks`). See `KissKH.kt`.
- Keep plugin metadata in module `cloudstream { ... }` block aligned with code behavior (language, status, tvTypes, authors). See `KissKH/build.gradle.kts`.
- JSON mapping is done with Jackson annotations (`@JsonProperty`) in local data classes near usage (`KissKH.kt`), not in a separate model package.
- Network calls frequently combine Jsoup + `app.get(...)` and should preserve existing headers/cookies patterns in `KissKH.kt`.
- Subtitles may require decryption before emission; reuse `SubDecryptor` instead of duplicating crypto code.

## Integration Points & External Dependencies
- CloudStream add-repo URI: `cloudstreamrepo://raw.githubusercontent.com/NovaCommand/CS-Custom/personal_build/repo.json`
- Raw repo metadata URL: `https://raw.githubusercontent.com/NovaCommand/CS-Custom/personal_build/repo.json`
- Raw plugin index URL: `https://raw.githubusercontent.com/NovaCommand/CS-Custom/personal_build/plugins.json`
- CloudStream wiki main page: https://cloudstream.miraheze.org/wiki/Main_Page
- Extension creation guide: https://cloudstream.miraheze.org/wiki/Creating_extensions
- Scraping tutorial: https://cloudstream.miraheze.org/wiki/Scraping_tutorial
- CloudStream plugin APIs come from `com.lagradost.cloudstream3` and project dependency `com.github.recloudstream:cloudstream` (root `build.gradle.kts`).
- External metadata enrichment uses TMDB lookups inside `KissKH.kt`; changes here affect title/poster/year consistency.
- Stream/subtitle key resolution calls remote Google Apps Script endpoints (`KissKH.kt`); treat failures as expected runtime conditions.

## Safe Change Strategy For Agents
- When touching scraping logic, validate all three paths: home cards, details/episodes, and `loadLinks` extraction.
- When changing build metadata (`status`, `version`, URLs), also verify generated plugin index files remain consistent (`plugins.json`, `repo.json`).
- Prefer minimal, localized edits in `KissKH.kt`; this file mixes endpoint contracts, parsing, and playback extraction in one provider class.
