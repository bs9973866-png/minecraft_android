package com.minecraft.launcher.auth

import java.util.UUID

data class AuthSession(
    val provider: String,
    val username: String,
    val accessToken: String,
    val userId: String,
    val expiresAt: Long
)

data class AuthResult(
    val success: Boolean,
    val session: AuthSession? = null,
    val error: String? = null
)

class ElyAuthManager {
    private val sessions = mutableMapOf<String, AuthSession>()

    fun login(username: String, password: String): AuthResult {
        if (username.isBlank() || password.isBlank()) {
            return AuthResult(false, error = "Username and password are required")
        }

        val token = "ely_${UUID.randomUUID()}"
        val session = AuthSession(
            provider = "Ely.by",
            username = username,
            accessToken = token,
            userId = UUID.randomUUID().toString(),
            expiresAt = System.currentTimeMillis() + 1000L * 60L * 60L * 24L * 30L
        )

        sessions[username] = session
        return AuthResult(true, session)
    }

    fun activeSessionFor(username: String): AuthSession? = sessions[username]

    fun isSessionValid(session: AuthSession?): Boolean {
        if (session == null) return false
        return session.expiresAt > System.currentTimeMillis()
    }
}
