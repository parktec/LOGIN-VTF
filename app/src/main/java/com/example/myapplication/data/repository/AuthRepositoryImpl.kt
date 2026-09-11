package com.example.myapplication.data.repository

import android.content.Context
import com.example.myapplication.data.local.AppDatabase
import com.example.myapplication.data.local.UserEntity
import com.example.myapplication.domain.model.LoginResult
import com.example.myapplication.domain.model.RegisterResult
import com.example.myapplication.domain.repository.AuthRepository

// Repositorio que ya no usa datos falsos, ahora consulta la base
// de datos local con Room (tabla "users").
class AuthRepositoryImpl(context: Context) : AuthRepository {

    private val userDao = AppDatabase.getInstance(context).userDao()

    override suspend fun login(username: String, password: String): LoginResult {
        val user = userDao.login(username, password)
        return if (user != null) {
            LoginResult.Success(user.nombreCompleto)
        } else {
            LoginResult.Error("Usuario o contraseña incorrectos")
        }
    }

    override suspend fun registrar(username: String, password: String, nombreCompleto: String): RegisterResult {
        val existente = userDao.buscarPorUsername(username)
        if (existente != null) {
            return RegisterResult.Error("Ese usuario ya existe")
        }

        return try {
            userDao.registrarUsuario(
                UserEntity(
                    username = username,
                    password = password,
                    nombreCompleto = nombreCompleto
                )
            )
            RegisterResult.Success(username)
        } catch (e: Exception) {
            RegisterResult.Error("No se pudo registrar: ${e.message}")
        }
    }
}
