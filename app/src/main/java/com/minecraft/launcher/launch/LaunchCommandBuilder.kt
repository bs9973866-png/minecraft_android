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
        val mods = if (modpackNames.isEmpty()) listOf("Vanilla") else modpackNames
        val safeMods = mods.joinToString(",") { it.replace(" ", "_") }

        return buildString {
            append("sh -c 'java ")
            append(profile.recommendedJavaArgs)
            append(" -jar minecraft-client.jar --version ${version.id} --profile ${profile.id} --user ${safeAccount} --mods ${safeMods}'")
        }
    }
}
