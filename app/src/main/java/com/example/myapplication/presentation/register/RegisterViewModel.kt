package com.example.myapplication.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.RegisterResult
import com.example.myapplication.domain.usecase.RegisterUseCase
import kotlinx.coroutines.launch

class RegisterViewModel(private val registerUseCase: RegisterUseCase) : ViewModel() {

    private val _state = mutableStateOf<RegisterResult?>(null)
    val state: State<RegisterResult?> = _state

    fun registrar(username: String, password: String, nombreCompleto: String) {
        viewModelScope.launch {
            _state.value = RegisterResult.Loading
            _state.value = registerUseCase(username, password, nombreCompleto)
        }
    }
}
