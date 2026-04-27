package com.ud.riddle.viewmodels


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ud.connect4ude.utils.UserPreferences
import com.ud.riddle.models.Game
import com.ud.riddle.models.states.GameUiState
import com.ud.riddle.repositories.GameDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class GameViewModel(
    application: Application,
    private val dataSource: GameDataSource
) : AndroidViewModel(application) {

    private val userPrefs = UserPreferences(application)

    private val _gameState = MutableStateFlow<Game?>(null)
    val gameState: StateFlow<Game?> = _gameState.asStateFlow()

    private val _uiState = MutableStateFlow<GameUiState>(GameUiState.Idle)
    val uiState = _uiState.asStateFlow()

    private var currentCode: String? = null

    fun startNewGame() {
        val userId = userPrefs.getUser()?.uid

        if (userId.isNullOrBlank()) {
            _uiState.value = GameUiState.Error("No existe usuario")
            return
        }

        _uiState.value = GameUiState.Loading

        dataSource.createGame(userId) { code ->
            currentCode = code
            listenToGame(code)
        }
    }

    fun joinGameByCode(code: String) {
        val userId = userPrefs.getUser()?.uid

        if (userId.isNullOrBlank()) {
            _uiState.value = GameUiState.Error("No existe usuario")
            return
        }

        _uiState.value = GameUiState.Loading

        dataSource.joinGame(code, userId) {
            currentCode = code
            listenToGame(code)
        }
    }

    private fun listenToGame(code: String) {
        dataSource.listenGame(code) { updatedGame ->
            _gameState.value = updatedGame
            _uiState.value = GameUiState.Success
        }
    }

    override fun onCleared() {
        super.onCleared()
        dataSource.removeListener()
    }
}