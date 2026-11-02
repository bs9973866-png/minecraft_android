# Minecraft Android Launcher Prototype

A lightweight Android launcher prototype for managing Java Minecraft installs, modpacks, launcher profiles, Android device optimization, and account presets.

This project is intended as a starting point for a custom launcher UI and architecture. It does not bundle or redistribute Minecraft assets or official game clients.

## Included

- version selection for Java Minecraft builds
- launcher profile management
- account entries for offline and Ely.by styled login flow
- modpack recommendations by selected version
- optimization presets for low / mid / high-end Android devices
- Material 3 UI scaffold for a future full launcher app

## Project structure

- `app/src/main/java/com/minecraft/launcher/` – app code
- `app/src/main/res/` – resources and strings
- `README.md` – project overview

## Android setup

Open this folder in Android Studio and let Gradle sync. Then run the app on a connected device or emulator.

## Notes

This is a prototype, not a complete proprietary launcher. For a production-grade launcher, you would add:

- a real account auth layer,
- download and install logic for selected client versions,
- Java runtime management,
- modpack validation,
- safe file extraction and patching,
- per-device launch optimization.

## Recommended next steps

1. Add version manifest fetcher and download manager.
2. Add Fabric / Forge / NeoForge adapter logic.
3. Add Ely.by OAuth flow and token storage.
4. Add a real game process launcher with JVM flags.
5. Add modpack compatibility validation before launch.
