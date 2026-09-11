package com.example.myapplication.presentation.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.repository.AuthRepositoryImpl
import com.example.myapplication.domain.usecase.LoginUseCase

class LoginViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            val repository = AuthRepositoryImpl(context.applicationContext)
            val useCase = LoginUseCase(repository)
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
