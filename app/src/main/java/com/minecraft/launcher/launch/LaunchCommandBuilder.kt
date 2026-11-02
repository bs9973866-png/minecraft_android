package com.minecraft.launcher.launch

import com.minecraft.launcher.model.Account
import com.minecraft.launcher.model.DeviceProfile
import com.minecraft.launcher.model.MinecraftVersion

class LaunchCommandBuilder {
    fun buildCommand(
        version: MinecraftVersion,
        account: Account,
        profile: DeviceProfile,
        modpackNames: List<String>
    ): String {
        val safeAccount = if (account.username.isBlank()) "offline" else account.username
        val modsSuffix = if (modpackNames.isEmpty()) "vanilla" else modpackNames.joinToString(",")

        return buildString {
            append("java ")
            append(profile.recommendedJavaArgs)
            append(" -jar minecraft-client.jar --version ${version.id} --profile ${profile.id} --user ${safeAccount} --mods ${modsSuffix}")
        }
    }
}
