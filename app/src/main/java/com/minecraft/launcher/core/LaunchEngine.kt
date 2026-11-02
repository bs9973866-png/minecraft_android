package com.minecraft.launcher.core

import com.minecraft.launcher.model.Account
import com.minecraft.launcher.model.DeviceProfile
import com.minecraft.launcher.model.MinecraftVersion

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

class LaunchEngine {
    fun createPlan(
        version: MinecraftVersion,
        account: Account,
        deviceProfile: DeviceProfile
    ): LaunchPlan {
        val recommendedMods = when (version.id) {
            "1.20.4" -> listOf("Fabulously Optimized", "Sodium Extras")
            "1.19.2" -> listOf("Create Immersive", "Simplified Forge Pack")
            "1.21.1" -> listOf("Rift Essentials", "Low-Input Performance")
            "1.16.5" -> listOf("NeoForge Arcade", "Classic Survival")
            else -> listOf("Vanilla")
        }

        return LaunchPlan(
            version = version,
            account = account,
            deviceProfile = deviceProfile,
            autoInstalledModpacks = recommendedMods,
            renderQuality = deviceProfile.renderQuality,
            memoryLimitMb = deviceProfile.memoryLimitMb,
            recommendedJavaArgs = deviceProfile.recommendedJavaArgs,
            safeToLaunch = account.status != "Queued" && version.label.isNotBlank()
        )
    }
}
