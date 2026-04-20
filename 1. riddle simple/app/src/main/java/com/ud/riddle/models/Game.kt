package com.ud.riddle.models

import com.ud.riddle.models.enums.GameCategory
import com.ud.riddle.models.enums.GameLanguages
import com.ud.riddle.models.enums.GameStateEnum
import com.ud.riddle.models.enums.GameVisibility

data class Game(
    val category: GameCategory = GameCategory.FOOD,
    val language: GameLanguages = GameLanguages.SPANISH,
    val visibility: GameVisibility = GameVisibility.PUBLIC,
    val code: String = "",
    val status: GameStateEnum = GameStateEnum.WAITING,
    val player1: String = "",
    val player2: String = "",
    val turnPlayerId: String = "",
    val winnerPlayerId: String = ""
)