# Minecraft Android Launcher Prototype

This repository contains an Android launcher prototype for managing Java Minecraft installs on Android devices.

## Current direction

The project is moving from a static launcher mock toward a real launcher architecture with:

- version and profile selection
- automatic modpack recommendation by version and device tier
- Ely.by-style account flow
- launch configuration builder for Java arguments and selected modpacks
- device-specific optimization presets

## Included foundations

- launcher UI with tabbed navigation
- version selection cards
- account selection flow
- optimization profile cards
- modpack recommendations
- launch planning logic
- Ely auth manager prototype
- compatibility manager for modpack selection
- launch command builder prototype

## Planned next stage

1. Add a real version manifest and install manager.
2. Add metadata checks for Fabric / Forge / NeoForge compatibility.
3. Wire the Ely.by auth manager to a persisted token/session store.
4. Generate and execute launch commands based on active profile and selected mods.
5. Add a real file downloader and mod installation pipeline.

## Important note

This is a prototype and not an official Minecraft launcher or a redistribution of copyrighted game assets.

## Build

Open the project in Android Studio, let Gradle sync, and run the app on an emulator or a connected device.
