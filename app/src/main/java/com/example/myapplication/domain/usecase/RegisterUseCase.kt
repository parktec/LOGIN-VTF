package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.RegisterResult
import com.example.myapplication.domain.repository.AuthRepository

class RegisterUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String, nombreCompleto: String): RegisterResult {
        if (username.isBlank() || password.isBlank() || nombreCompleto.isBlank()) {
            return RegisterResult.Error("Completa todos los campos")
        }
        if (password.length < 4) {
            return RegisterResult.Error("La contraseña debe tener al menos 4 caracteres")
        }
        return repository.registrar(username, password, nombreCompleto)
    }
}
