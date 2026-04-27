package com.ud.riddle.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ud.riddle.models.Game
import com.ud.riddle.models.enums.GameCategory
import com.ud.riddle.models.enums.GameLanguages
import com.ud.riddle.models.enums.GameVisibility

@Composable
fun GameConfigScreen(
    onCreateGame: (Game) -> Unit
) {
    var selectedCategory by remember { mutableStateOf(GameCategory.MOVIES) }
    var selectedLanguage by remember { mutableStateOf(GameLanguages.SPANISH) }
    var selectedVisibility by remember { mutableStateOf(GameVisibility.PRIVATE) }
    val roomCode = remember { generateRoomCode() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Nueva partida",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(28.dp))

        // --- Categoría ---
        SectionLabel("Categoría")
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            GameCategory.entries.forEach { category ->
                CategoryCard(
                    category = category,
                    isSelected = selectedCategory == category,
                    modifier = Modifier.weight(1f),
                    onClick = { selectedCategory = category }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        HorizontalDivider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.outlineVariant)
        Spacer(modifier = Modifier.height(24.dp))

        // --- Idioma ---
        SectionLabel("Idioma")
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            GameLanguages.entries.forEach { language ->
                LanguageChip(
                    language = language,
                    isSelected = selectedLanguage == language,
                    modifier = Modifier.weight(1f),
                    onClick = { selectedLanguage = language }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        HorizontalDivider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.outlineVariant)
        Spacer(modifier = Modifier.height(24.dp))

        // --- Tipo de partida ---
        SectionLabel("Tipo de partida")
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            GameVisibility.entries.forEach { visibility ->
                VisibilityCard(
                    visibility = visibility,
                    isSelected = selectedVisibility == visibility,
                    modifier = Modifier.weight(1f),
                    onClick = { selectedVisibility = visibility }
                )
            }
        }

        // Código de sala (solo visible en modo privado)
        if (selectedVisibility == GameVisibility.PRIVATE) {
            Spacer(modifier = Modifier.height(12.dp))
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                tonalElevation = 0.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Código de sala",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = roomCode,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.Monospace,
                        color = MaterialTheme.colorScheme.primary,
                        letterSpacing = 2.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = {
                onCreateGame(
                    Game(
                        category = selectedCategory,
                        language = selectedLanguage,
                        visibility = selectedVisibility
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Crear partida", fontSize = 15.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(
        text = text.uppercase(),
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.8.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun CategoryCard(
    category: GameCategory,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.outlineVariant
    val bgColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer
    else MaterialTheme.colorScheme.surface

    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = if (isSelected) 1.5.dp else 0.5.dp,
                color = borderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        color = bgColor
    ) {
        Column(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = category.icon, fontSize = 20.sp)
            Text(
                text = category.label,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer
                else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun LanguageChip(
    language: GameLanguages,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .border(
                width = if (isSelected) 1.5.dp else 0.5.dp,
                color = if (isSelected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        color = if (isSelected) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.surface
    ) {
        Text(
            text = "${language.flag} ${language.label}",
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = if (isSelected) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun VisibilityCard(
    visibility: GameVisibility,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .border(
                width = if (isSelected) 1.5.dp else 0.5.dp,
                color = if (isSelected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
        color = if (isSelected) MaterialTheme.colorScheme.inverseSurface
        else MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = visibility.icon, fontSize = 22.sp)
            Text(
                text = visibility.label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = if (isSelected) MaterialTheme.colorScheme.inverseOnSurface
                else MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = visibility.description,
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                color = if (isSelected) MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.7f)
                else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun generateRoomCode(): String {
    val chars = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789"
    return "${(0..1).map { chars.random() }.joinToString("")}-${(0..3).map { chars.filter { c -> c.isDigit() }.random() }.joinToString("")}"
}