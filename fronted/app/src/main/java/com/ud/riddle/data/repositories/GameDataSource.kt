package com.ud.riddle.data.repositories

import com.ud.riddle.dominio.models.Game

interface GameDataSource {
    fun createGame(playerId: String, word: String, clue: String, onComplete: (String) -> Unit)
    fun joinGame(code: String, playerId: String, onComplete: () -> Unit)
    fun listenGame(code: String, onUpdate: (Game?) -> Unit)
    fun removeListener()
}
