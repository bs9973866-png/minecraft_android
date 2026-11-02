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
        val clientJar = "/storage/emulated/0/Android/data/com.minecraft.launcher/files/minecraft-client.jar"

        return buildString {
            append("if [ -f \"")
            append(clientJar)
            append("\" ]; then java ")
            append(profile.recommendedJavaArgs)
            append(" -jar \"")
            append(clientJar)
            append("\" --version ${version.id} --profile ${profile.id} --user ${safeAccount} --mods ${safeMods}; else echo \"Minecraft client not found at ${clientJar}\"; exit 127; fi")
        }
    }
}
