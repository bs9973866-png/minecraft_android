package com.minecraft.launcher.model

data class MinecraftVersion(
    val id: String,
    val label: String,
    val type: String,
    val recommended: Boolean = false,
    val description: String
)

data class Modpack(
    val id: String,
    val name: String,
    val version: String,
    val compatibility: String,
    val description: String,
    val downloads: Int,
    val recommended: Boolean = false,
    val source: String = "Modrinth",
    val category: String = "General"
)

data class Account(
    val id: String,
    val username: String,
    val type: String,
    val status: String,
    val isActive: Boolean = false
)

data class DeviceProfile(
    val id: String,
    val name: String,
    val memoryLimitMb: Int,
    val renderQuality: String,
    val recommendedJavaArgs: String,
    val description: String = ""
)

data class ModLoader(
    val id: String,
    val name: String,
    val source: String,
    val compatibility: String,
    val description: String
)

data class ClientMod(
    val id: String,
    val name: String,
    val source: String,
    val version: String,
    val category: String,
    val description: String,
    val recommended: Boolean = false
)

data class ShaderPack(
    val id: String,
    val name: String,
    val source: String,
    val version: String,
    val description: String,
    val recommended: Boolean = false
)

data class ResourcePack(
    val id: String,
    val name: String,
    val source: String,
    val version: String,
    val description: String,
    val recommended: Boolean = false
)

data class ControlPreset(
    val id: String,
    val name: String,
    val layout: String,
    val description: String,
    val recommended: Boolean = false
)

data class LaunchPlan(
    val version: MinecraftVersion,
    val account: Account,
    val deviceProfile: DeviceProfile,
    val autoInstalledModpacks: List<String>,
    val renderQuality: String,
    val memoryLimitMb: Int,
    val recommendedJavaArgs: String,
    val safeToLaunch: Boolean = true
)

data class LaunchConfig(
    val version: MinecraftVersion,
    val account: Account,
    val deviceProfile: DeviceProfile,
    val modpackNames: List<String>,
    val javaCommand: String,
    val jvmArgs: String,
    val commandLine: String,
    val safeToLaunch: Boolean = true
)

data class LauncherUiState(
    val versions: List<MinecraftVersion>,
    val selectedVersion: MinecraftVersion,
    val accounts: List<Account>,
    val selectedAccountId: String,
    val modpacks: List<Modpack>,
    val deviceProfiles: List<DeviceProfile>,
    val selectedDeviceProfileId: String
)

data class LauncherSetupState(
    val selectedLoader: ModLoader,
    val selectedModpack: Modpack,
    val selectedMods: List<ClientMod>,
    val selectedShader: ShaderPack,
    val selectedResourcePack: ResourcePack,
    val selectedControlPreset: ControlPreset
)
