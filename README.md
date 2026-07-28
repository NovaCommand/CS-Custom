# CS-Custom

CloudStream3 custom repository currently publishing the `KissKH` provider.

## Add This Repo to CloudStream

Use this repository URL inside CloudStream:

`cloudstreamrepo://raw.githubusercontent.com/NovaCommand/CS-Custom/personal_build/repo.json`

Direct `repo.json` link:

`https://raw.githubusercontent.com/NovaCommand/CS-Custom/personal_build/repo.json`

Main plugin index (`plugins.json`):

`https://raw.githubusercontent.com/NovaCommand/CS-Custom/personal_build/plugins.json`

## Repository Information

- GitHub: `https://github.com/NovaCommand/CS-Custom`
- Active module: `KissKH/`
- Plugin artifact: `https://raw.githubusercontent.com/NovaCommand/CS-Custom/personal_build/KissKH.cs3`

## Current Plugin

`KissKH`

- Language: `en`
- Status: `1` (enabled)
- Version: `4`
- Type: `AsianDrama`
- Authors: `kraptor`, `ByAyzen`

## Build Commands (Windows PowerShell)

```powershell
./gradlew.bat build
./gradlew.bat derle
./gradlew.bat :KissKH:build
./gradlew.bat clean
```

Use `--info` or `--stacktrace` for debugging build/packaging issues.

## Project Layout

- `KissKH/src/main/kotlin/com/byayzen/KissKH.kt` -> provider logic (`getMainPage`, `search`, `load`, `loadLinks`)
- `KissKH/src/main/kotlin/com/byayzen/KissKHPlugin.kt` -> plugin entrypoint
- `KissKH/src/main/kotlin/com/byayzen/SubDecryptor.kt` -> subtitle decryption helper
- `repo.json` and `plugins.json` -> CloudStream repository and plugin index metadata

## CloudStream Docs

- Main wiki: https://cloudstream.miraheze.org/wiki/Main_Page
- Creating extensions: https://cloudstream.miraheze.org/wiki/Creating_extensions
- Scraping tutorial: https://cloudstream.miraheze.org/wiki/Scraping_tutorial
