package com.ud.riddle.models.enums

enum class GameVisibility(val label: String, val icon: String, val description: String) {
    PUBLIC("Pública", "🌐", "Cualquiera puede unirse"),
    PRIVATE("Privada", "🔒", "Solo con código")
}