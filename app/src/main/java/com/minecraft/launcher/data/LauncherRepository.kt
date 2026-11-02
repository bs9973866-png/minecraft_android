package com.minecraft.launcher.data

import com.minecraft.launcher.model.Account
import com.minecraft.launcher.model.DeviceProfile
import com.minecraft.launcher.model.MinecraftVersion
import com.minecraft.launcher.model.Modpack

class LauncherRepository {
    fun getVersions(): List<MinecraftVersion> = listOf(
        MinecraftVersion(
            id = "1.20.4",
            label = "1.20.4",
            type = "Java",
            recommended = true,
            description = "Stable build with broad mod and shader support"
        ),
        MinecraftVersion(
            id = "1.19.2",
            label = "1.19.2",
            type = "Forge",
            recommended = false,
            description = "Classic mod support and many optimization packs"
        ),
        MinecraftVersion(
            id = "1.21.1",
            label = "1.21.1",
            type = "Fabric",
            recommended = false,
            description = "Modern performance profile for lightweight systems"
        ),
        MinecraftVersion(
            id = "1.16.5",
            label = "1.16.5",
            type = "NeoForge",
            recommended = false,
            description = "Legacy but still highly compatible with community packs"
        )
    )

    fun getAccounts(): List<Account> = listOf(
        Account("ely", "Ely.by", "Ely.by", "Synced", true),
        Account("offline", "Offline", "Offline", "Ready", false),
        Account("alt1", "Alt_1", "Premium", "Queued", false)
    )

    fun getModpacks(): List<Modpack> = listOf(
        Modpack(
            id = "fabulously-optimized",
            name = "Fabulously Optimized",
            version = "1.20.4",
            compatibility = "Fabric",
            description = "High FPS and stable performance for mid-tier phones",
            downloads = 420000
        ),
        Modpack(
            id = "create-immersive",
            name = "Create Immersive",
            version = "1.19.2",
            compatibility = "Forge",
            description = "Creative automation with strong compatibility",
            downloads = 190000
        ),
        Modpack(
            id = "rift-essentials",
            name = "Rift Essentials",
            version = "1.21.1",
            compatibility = "Fabric",
            description = "Performance-first profile with QoL improvements",
            downloads = 94000
        )
    )

    fun getDeviceProfiles(): List<DeviceProfile> = listOf(
        DeviceProfile(
            id = "low",
            name = "Low-End",
            memoryLimitMb = 2048,
            renderQuality = "Balanced",
            recommendedJavaArgs = "-Xms512M -Xmx1536M -XX:+UseG1GC"
        ),
        DeviceProfile(
            id = "mid",
            name = "Mid-Range",
            memoryLimitMb = 4096,
            renderQuality = "Ultra",
            recommendedJavaArgs = "-Xms1024M -Xmx3072M -XX:+UseG1GC"
        ),
        DeviceProfile(
            id = "high",
            name = "High-End",
            memoryLimitMb = 8192,
            renderQuality = "Extreme",
            recommendedJavaArgs = "-Xms2048M -Xmx6144M -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC"
        )
    )
}
