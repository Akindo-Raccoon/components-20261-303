package com.ud.riddle.models.states

sealed class AuthUiState {
    object Idle : AuthUiState()
    object Loading : AuthUiState()
    data class Success(val userEmail: String) : AuthUiState()
    data class Error(val message: String) : AuthUiState()
}