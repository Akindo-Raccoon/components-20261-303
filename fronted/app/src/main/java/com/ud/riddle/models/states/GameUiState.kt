package com.ud.riddle.models.states

sealed class GameUiState {
    object Idle : GameUiState()
    object Loading : GameUiState()
    object Success : GameUiState()
    data class Error(val message: String) : GameUiState()
}