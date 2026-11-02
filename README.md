# Minecraft Android Launcher Prototype

This repository contains an Android launcher prototype for managing Java Minecraft installs on Android devices. The project is meant to be a strong foundation for a custom launcher UI, profile handling, modpack recommendations, and device-specific performance tuning.

## Current features

- version picker for Java-capable Minecraft builds
- account cards for Ely.by-style login and offline profiles
- modpack recommendations tied to selected version
- optimization profiles for weak / mid / high-end phones
- Material 3 launcher interface with tabs for versions, accounts, mods, and settings

## Key idea

The app focuses on a launcher experience rather than a game client itself. It is designed to evolve toward:

- version downloads and installs
- mod loader detection (Fabric / Forge / NeoForge)
- automatic modpack resolution and compatibility checks
- Java runtime and memory tuning per device
- launch configuration based on phone hardware

## Important note

This is a prototype and not an official Minecraft launcher or a redistribution of game assets. It does not ship game binaries, official client code, or copyrighted content beyond basic UI scaffolding and mock data.

## Repository structure

- `app/src/main/java/com/minecraft/launcher` — launcher logic and screens
- `app/src/main/res/values` — strings and theme resources
- `app/build.gradle.kts` — Android app module setup
- `README.md` — overview and project notes

## Next development areas

1. Version manifest download and installation logic
2. Fabric / Forge / NeoForge compatibility engine
3. Ely.by client auth flow
4. Auto-install and patching logic for recommended modpacks
5. Real Java launch command builder with memory/gpu flags

## Build

Open the project in Android Studio and sync Gradle. Then run the app on an emulator or connected Android device.

## Roadmap

- MVP launcher UI
- profile and account model
- modpack recommendation engine
- device profile optimization
- real client install and launch pipeline
