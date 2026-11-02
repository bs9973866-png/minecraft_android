package com.minecraft.launcher.launch

import com.minecraft.launcher.model.LaunchConfig

data class LaunchExecutionResult(
    val success: Boolean,
    val message: String,
    val command: String
)

class LaunchExecutor {
    fun execute(config: LaunchConfig): LaunchExecutionResult {
        return try {
            val process = ProcessBuilder()
                .command("sh", "-c", config.commandLine)
                .redirectErrorStream(true)
                .start()

            val output = process.inputStream.bufferedReader().readText()
            val exitCode = process.waitFor()

            if (exitCode == 0) {
                LaunchExecutionResult(
                    success = true,
                    message = output.ifBlank { "Java runtime started successfully." },
                    command = config.commandLine
                )
            } else {
                LaunchExecutionResult(
                    success = false,
                    message = output.ifBlank { "Java runtime exited with code $exitCode." },
                    command = config.commandLine
                )
            }
        } catch (e: Exception) {
            LaunchExecutionResult(
                success = false,
                message = "Unable to run Java on this device: ${e.message ?: "unknown error"}",
                command = config.commandLine
            )
        }
    }
}
