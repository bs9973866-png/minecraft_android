package com.minecraft.launcher.launch

import com.minecraft.launcher.model.LaunchConfig

data class LaunchExecutionResult(
    val success: Boolean,
    val message: String,
    val command: String
)

class LaunchExecutor {
    fun execute(config: LaunchConfig): LaunchExecutionResult {
        if (!config.safeToLaunch) {
            return LaunchExecutionResult(
                success = false,
                message = "Launch is blocked: account status or version is not safe to launch.",
                command = config.commandLine
            )
        }

        return try {
            val javaCheck = ProcessBuilder("sh", "-c", "command -v java").redirectErrorStream(true).start()
            val javaOutput = javaCheck.inputStream.bufferedReader().readText()
            val javaExit = javaCheck.waitFor()

            if (javaExit != 0 || javaOutput.isBlank()) {
                return LaunchExecutionResult(
                    success = false,
                    message = "Java runtime is not installed or not available on this device.",
                    command = config.commandLine
                )
            }

            val process = ProcessBuilder()
                .command("sh", "-c", config.commandLine)
                .redirectErrorStream(true)
                .start()

            val output = process.inputStream.bufferedReader().readText()
            val exitCode = process.waitFor()

            if (exitCode == 0) {
                LaunchExecutionResult(
                    success = true,
                    message = output.ifBlank { "Minecraft launched successfully." },
                    command = config.commandLine
                )
            } else {
                LaunchExecutionResult(
                    success = false,
                    message = output.ifBlank { "Minecraft exited with code $exitCode." },
                    command = config.commandLine
                )
            }
        } catch (e: Exception) {
            LaunchExecutionResult(
                success = false,
                message = "Unable to launch Minecraft on this device: ${e.message ?: "unknown error"}",
                command = config.commandLine
            )
        }
    }
}
