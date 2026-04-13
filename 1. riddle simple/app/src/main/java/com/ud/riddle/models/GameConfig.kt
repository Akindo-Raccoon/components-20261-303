package com.ud.riddle.models

import com.ud.riddle.models.enums.GameCategory
import com.ud.riddle.models.enums.GameLanguages
import com.ud.riddle.models.enums.GameVisibility

data class GameConfig(
    val category: GameCategory,
    val language: GameLanguages,
    val visibility: GameVisibility,
    val roomCode: String? = null
)