package com.ud.riddle.dominio.models

data class GameDiscoveryResponse(
    val clue: String,
    val word: String,
    val category: String,
    val language: String
)
