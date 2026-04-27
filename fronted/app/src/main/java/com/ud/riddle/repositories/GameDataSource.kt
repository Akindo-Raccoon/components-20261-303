package com.ud.riddle.repositories

import com.ud.riddle.models.Game

interface GameDataSource {
    fun createGame(playerId: String, onComplete: (String) -> Unit)
    fun joinGame(code: String, playerId: String, onComplete: () -> Unit)
    fun listenGame(code: String, onUpdate: (Game?) -> Unit)
    fun removeListener()
}