package com.ud.riddle.dominio.models

data class Player(
    val name:String,
    var isImpostor: Boolean = false
)