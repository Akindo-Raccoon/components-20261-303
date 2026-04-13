package com.ud.riddle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.ud.riddle.models.AuthUiState
import com.ud.riddle.ui.screens.GameScreen
import com.ud.riddle.ui.screens.LoginScreen
import com.ud.riddle.ui.theme.RiddleAppTheme
import com.ud.riddle.viewmodels.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val authViewModel: AuthViewModel by viewModels()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RiddleAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val uiState by authViewModel.uiState.collectAsState()

                    when (uiState) {
                        is AuthUiState.Success -> GameScreen(innerPadding)
                        else -> LoginScreen(authViewModel)
                    }
                }
            }
        }
    }
}