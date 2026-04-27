package com.ud.riddle.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ud.riddle.models.states.AuthUiState
import com.ud.riddle.viewmodels.GameViewModel

@Composable
fun MatchScreen(viewModel: GameViewModel) {

    val game by viewModel.gameState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    var codeInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (uiState is AuthUiState.Error) {
            Text(
                text = (uiState as AuthUiState.Error).message,
                color = Color.Red
            )
        }

        if (game == null) {

            TextField(
                value = codeInput,
                onValueChange = { codeInput = it },
                label = { Text("Código") }
            )

            Button(onClick = {
                viewModel.joinGameByCode(codeInput)
            }) {
                Text("Unirse")
            }

            Button(onClick = {
                viewModel.startNewGame()
            }) {
                Text("Crear partida")
            }

        } else {
            GameScreen(viewModel = viewModel)
        }
    }
}