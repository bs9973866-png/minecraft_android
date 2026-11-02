package com.minecraft.launcher.core

import com.minecraft.launcher.model.DeviceProfile

class LaunchProfileGenerator {
    fun buildJvmArgs(profile: DeviceProfile): String {
        return when (profile.id) {
            "low" -> "-Xms512M -Xmx1536M -XX:+UseG1GC -Djava.awt.headless=true"
            "mid" -> "-Xms1024M -Xmx3072M -XX:+UseG1GC -Djava.awt.headless=true"
            "high" -> "-Xms2048M -Xmx6144M -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -Djava.awt.headless=true"
            else -> profile.recommendedJavaArgs
        }
    }

    fun recommendedModpackNames(versionId: String): List<String> = when (versionId) {
        "1.20.4" -> listOf("Fabulously Optimized", "Sodium Extras")
        "1.19.2" -> listOf("Create Immersive", "Better Compatibility")
        "1.21.1" -> listOf("Rift Essentials", "Low-Input Performance")
        "1.16.5" -> listOf("NeoForge Arcade", "Classic Survival")
        else -> listOf("Vanilla")
    }
}
