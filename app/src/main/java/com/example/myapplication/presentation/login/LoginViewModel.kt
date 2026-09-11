package com.example.myapplication.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.LoginResult
import com.example.myapplication.domain.usecase.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _state = mutableStateOf<LoginResult?>(null)
    val state: State<LoginResult?> = _state

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _state.value = LoginResult.Loading
            val result = loginUseCase(username, password)
            _state.value = result
        }
    }
}
