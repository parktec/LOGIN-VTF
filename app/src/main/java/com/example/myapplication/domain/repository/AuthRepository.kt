package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.LoginResult
import com.example.myapplication.domain.model.RegisterResult

interface AuthRepository {
    suspend fun login(username: String, password: String): LoginResult
    suspend fun registrar(username: String, password: String, nombreCompleto: String): RegisterResult
}
