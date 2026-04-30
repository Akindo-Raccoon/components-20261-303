package com.ud.riddle.viewmodels


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ud.connect4ude.utils.UserPreferences
import com.ud.riddle.service.GameApiService
import com.ud.riddle.models.Game
import com.ud.riddle.models.states.GameUiState
import com.ud.riddle.repositories.GameDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class GameViewModel(
    application: Application,
    private val dataSource: GameDataSource,
    private val apiService: GameApiService = GameApiService.create()
) : AndroidViewModel(application) {

    private val userPrefs = UserPreferences(application)

    private val _gameState = MutableStateFlow<Game?>(null)
    val gameState: StateFlow<Game?> = _gameState.asStateFlow()

    private val _uiState = MutableStateFlow<GameUiState>(GameUiState.Idle)
    val uiState = _uiState.asStateFlow()

    private var currentCode: String? = null

    fun startNewGame(category: String = "animales", language: String = "es") {
        val userId = userPrefs.getUser()?.uid

        if (userId.isNullOrBlank()) {
            _uiState.value = GameUiState.Error("No existe usuario")
            return
        }

        _uiState.value = GameUiState.Loading

        viewModelScope.launch {
            try {
                Log.d("RETROFIT_DEBUG", "Iniciando petición -> Categoria: $category, Idioma: $language")

                // Llamada al servicio GameApiService
                val response = apiService.getDiscovery(category, language)

                // LOG DETALLADO PARA VER QUE TRAE EL SERVICIO
                Log.d("RETROFIT_DEBUG", "RESPUESTA RECIBIDA:")
                Log.d("RETROFIT_DEBUG", "  - Palabra: ${response.word}")
                Log.d("RETROFIT_DEBUG", "  - Pista: ${response.clue}")
                Log.d("RETROFIT_DEBUG", "  - Categoria: ${response.category}")
                Log.d("RETROFIT_DEBUG", "  - Idioma: ${response.language}")

                // Creamos el juego con los datos del backend
                dataSource.createGame(userId, response.word, response.clue) { code ->
                    currentCode = code
                    listenToGame(code)
                }
            } catch (e: Exception) {
                Log.e("RETROFIT_DEBUG", "ERROR AL CONSUMIR SERVICIO: ${e.message}")
                e.printStackTrace()
                _uiState.value = GameUiState.Error("Error al obtener reto: ${e.message}")
            }
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
