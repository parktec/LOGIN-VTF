package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.LoginResult
import com.example.myapplication.domain.repository.AuthRepository

class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String): LoginResult {
        if (username.isBlank() || password.isBlank()) {
            return LoginResult.Error("Username and password cannot be empty")
        }
        return repository.login(username, password)
    }
}
