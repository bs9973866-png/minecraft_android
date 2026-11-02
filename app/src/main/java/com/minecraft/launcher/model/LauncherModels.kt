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
    val recommended: Boolean = false
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

data class LaunchConfig(
    val version: MinecraftVersion,
    val account: Account,
    val deviceProfile: DeviceProfile,
    val javaArgs: String,
    val modpackNames: List<String>
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
