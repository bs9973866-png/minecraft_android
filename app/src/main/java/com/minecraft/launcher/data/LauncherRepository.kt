package com.minecraft.launcher.data

import com.minecraft.launcher.core.LaunchProfileGenerator
import com.minecraft.launcher.launch.LaunchCommandBuilder
import com.minecraft.launcher.model.Account
import com.minecraft.launcher.model.ClientMod
import com.minecraft.launcher.model.ControlPreset
import com.minecraft.launcher.model.DeviceProfile
import com.minecraft.launcher.model.LaunchConfig
import com.minecraft.launcher.model.LaunchPlan
import com.minecraft.launcher.model.MinecraftVersion
import com.minecraft.launcher.model.ModLoader
import com.minecraft.launcher.model.Modpack
import com.minecraft.launcher.model.ResourcePack
import com.minecraft.launcher.model.ShaderPack

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
            description = "High FPS and stable performance for mid-tier phones.",
            downloads = 420000,
            recommended = true,
            source = "Modrinth",
            category = "Optimization"
        ),
        Modpack(
            id = "create-immersive",
            name = "Create Immersive",
            version = "1.19.2",
            compatibility = "Forge",
            description = "Creative automation pack with strong support.",
            downloads = 190000,
            source = "CurseForge",
            category = "Automation"
        ),
        Modpack(
            id = "rift-essentials",
            name = "Rift Essentials",
            version = "1.21.1",
            compatibility = "Fabric",
            description = "Performance-first pack with QoL improvements.",
            downloads = 94000,
            source = "Modrinth",
            category = "Performance"
        ),
        Modpack(
            id = "neoforge-arcade",
            name = "NeoForge Arcade",
            version = "1.16.5",
            compatibility = "NeoForge",
            description = "Optimized for older devices and lightweight packs.",
            downloads = 62000,
            source = "CurseForge",
            category = "Lite"
        )
    )

    fun getModLoaders(): List<ModLoader> = listOf(
        ModLoader("fabric", "Fabric", "Modrinth", "1.20-1.21", "Fast, lightweight, best for mobile-friendly builds"),
        ModLoader("forge", "Forge", "CurseForge", "1.16-1.19", "Classic mod support and broad compatibility"),
        ModLoader("neoforge", "NeoForge", "CurseForge", "1.20+", "Modern Forge branch with improved performance"),
        ModLoader("quilt", "Quilt", "Modrinth", "1.20+", "Modern Fabric-like ecosystem with modded stability")
    )

    fun getModSelection(): List<ClientMod> = listOf(
        ClientMod("sodium", "Sodium", "Modrinth", "0.5.8", "Performance", "Huge FPS boost for modern versions", true),
        ClientMod("lithium", "Lithium", "Modrinth", "0.11.4", "Performance", "Reduces lag and improves chunk loading", true),
        ClientMod("entityculling", "EntityCulling", "Modrinth", "1.6.2", "Optimization", "Improves performance in crowded areas", true),
        ClientMod("jei", "JEI", "CurseForge", "15.2.0", "QoL", "Recipe and item browser", false),
        ClientMod("journeymap", "JourneyMap", "CurseForge", "5.9.0", "Map", "Minimap and map tracking", false),
        ClientMod("betterfps", "BetterFPS", "Modrinth", "8.1.1", "Performance", "Advanced rendering tweaks", false)
    )

    fun getShaders(): List<ShaderPack> = listOf(
        ShaderPack("sildurs", "Sildur's Vibrant Shaders", "Modrinth", "1.20.4", "Balanced quality and performance", true),
        ShaderPack("complementary", "Complementary Shaders", "Modrinth", "1.20.4", "High-quality visuals with moderate hardware cost", false),
        ShaderPack("bsl", "BSL Shaders", "CurseForge", "1.20.4", "Very nice visuals for stronger devices", false)
    )

    fun getResourcePacks(): List<ResourcePack> = listOf(
        ResourcePack("vanilla-plus", "Vanilla+", "Modrinth", "1.20.4", "Clean visual upgrade without major style changes", true),
        ResourcePack("retina", "Retina", "CurseForge", "1.20.4", "High-detail textures for stronger mobile GPUs", false),
        ResourcePack("minimal-ui", "Minimal UI Pack", "Modrinth", "1.20.4", "Cleaner HUD and UI styling", false)
    )

    fun getControlPresets(): List<ControlPreset> = listOf(
        ControlPreset("touch-classic", "Classic Touch", "Left thumb + right thumb + quick actions", "Simple and familiar mobile layout", true),
        ControlPreset("touch-advanced", "Advanced Hybrid", "Expanded hotkeys + touchpad + action slots", "Best for PvP and building"),
        ControlPreset("console", "Console Style", "Dual thumbsticks + quick inventory buttons", "More gamepad-like mobile layout")
    )

    fun getModpacksForVersion(versionId: String): List<Modpack> =
        getModpacks().filter { it.version == versionId }

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

    fun buildLaunchPlan(
        version: MinecraftVersion,
        account: Account,
        deviceProfile: DeviceProfile
    ): LaunchPlan {
        val generator = LaunchProfileGenerator()
        val autoInstalled = getModpacksForVersion(version.id)
            .map { it.name }
            .ifEmpty { generator.recommendedModpackNames(version.id) }

        return LaunchPlan(
            version = version,
            account = account,
            deviceProfile = deviceProfile,
            autoInstalledModpacks = autoInstalled,
            renderQuality = deviceProfile.renderQuality,
            memoryLimitMb = deviceProfile.memoryLimitMb,
            recommendedJavaArgs = generator.buildJvmArgs(deviceProfile),
            safeToLaunch = account.status != "Queued" && account.username.isNotBlank()
        )
    }

    fun buildLaunchConfig(
        version: MinecraftVersion,
        account: Account,
        deviceProfile: DeviceProfile
    ): LaunchConfig {
        val plan = buildLaunchPlan(version, account, deviceProfile)
        val command = LaunchCommandBuilder().buildCommand(
            version = version,
            account = account,
            profile = deviceProfile,
            modpackNames = plan.autoInstalledModpacks
        )

        return LaunchConfig(
            version = version,
            account = account,
            deviceProfile = deviceProfile,
            modpackNames = plan.autoInstalledModpacks,
            javaCommand = "java",
            jvmArgs = plan.recommendedJavaArgs,
            commandLine = command,
            safeToLaunch = plan.safeToLaunch
        )
    }
}
