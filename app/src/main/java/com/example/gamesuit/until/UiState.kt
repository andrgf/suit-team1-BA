package com.example.gamesuit.until


import com.example.gamesuit.activity.data.retrofit.AuthResponse

sealed class UiState {
    object Loading : UiState()

    data class Success(
        val authResponse: AuthResponse
    ) : UiState()

    data class Error(val message: String) : UiState()
}