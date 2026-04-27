package com.ud.riddle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ud.riddle.models.states.AuthUiState
import com.ud.riddle.ui.screens.LoginScreen
import com.ud.riddle.ui.screens.RootScreen
import com.ud.riddle.ui.theme.RiddleAppTheme
import com.ud.riddle.viewmodels.AuthViewModel

class MainActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            RiddleAppTheme {

                val uiState by authViewModel.uiState.collectAsState()

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        when (uiState) {

                            is AuthUiState.Success -> {
                                RootScreen()
                            }

                            is AuthUiState.Loading -> {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }

                            else -> {
                                LoginScreen(authViewModel)
                            }
                        }
                    }
                }
            }
        }
    }
}