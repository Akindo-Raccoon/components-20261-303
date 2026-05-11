package com.ud.riddle.dominio.models

import com.ud.riddle.dominio.models.enums.GameCategory
import com.ud.riddle.dominio.models.enums.GameLanguages
import com.ud.riddle.dominio.models.enums.GameStateEnum
import com.ud.riddle.dominio.models.enums.GameVisibility

data class Game(
    val category: GameCategory = GameCategory.FOOD,
    val language: GameLanguages = GameLanguages.SPANISH,
    val visibility: GameVisibility = GameVisibility.PUBLIC,
    val code: String = "",
    val status: GameStateEnum = GameStateEnum.CREATING_PLAYERS,
    val player1: String = "",
    val player2: String = "",
    val turnPlayerId: String = "",
    val winnerPlayerId: String = "",
    val word: String = "",
    val clue: String = ""
)
