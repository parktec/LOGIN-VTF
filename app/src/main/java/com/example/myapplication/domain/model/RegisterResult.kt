package com.example.myapplication.domain.model

sealed class RegisterResult {
    object Loading : RegisterResult()
    data class Success(val username: String) : RegisterResult()
    data class Error(val message: String) : RegisterResult()
}
