package com.minecraft.launcher.compat

import com.minecraft.launcher.model.Modpack

class ModpackCompatibilityManager {
    fun selectCompatibleModpacks(versionId: String, deviceTier: String): List<Modpack> {
        val pool = listOf(
            Modpack(
                id = "fabulously-optimized",
                name = "Fabulously Optimized",
                version = "1.20.4",
                compatibility = "Fabric",
                description = "Best performance for modern Android devices",
                downloads = 420000,
                recommended = true
            ),
            Modpack(
                id = "create-immersive",
                name = "Create Immersive",
                version = "1.19.2",
                compatibility = "Forge",
                description = "Creative automation with solid compatibility",
                downloads = 190000
            ),
            Modpack(
                id = "rift-essentials",
                name = "Rift Essentials",
                version = "1.21.1",
                compatibility = "Fabric",
                description = "Balanced optimization for low-input systems",
                downloads = 94000
            ),
            Modpack(
                id = "neoforge-arcade",
                name = "NeoForge Arcade",
                version = "1.16.5",
                compatibility = "NeoForge",
                description = "Low-memory configuration for older phones",
                downloads = 62000
            )
        )

        return when (deviceTier) {
            "low" -> pool.filter { it.version == "1.16.5" || it.version == "1.19.2" }
            "mid" -> pool.filter { it.version == versionId || it.recommended }
            "high" -> pool.filter { it.version == versionId || it.version == "1.20.4" }
            else -> pool.filter { it.version == versionId }
        }
    }
}
