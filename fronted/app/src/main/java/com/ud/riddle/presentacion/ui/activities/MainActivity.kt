package com.ud.riddle.presentacion.ui.activities

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ud.connect4ude.utils.UserPreferences
import com.ud.riddle.dominio.models.states.AuthUiState
import com.ud.riddle.presentacion.ui.screens.LoginScreen
import com.ud.riddle.presentacion.ui.screens.RootScreen
import com.ud.riddle.presentacion.ui.theme.RiddleAppTheme
import com.ud.riddle.presentacion.viewmodels.AuthViewModel
import androidx.compose.runtime.getValue

class MainActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val userPrefs = UserPreferences(this.application)
        enableEdgeToEdge()

        setContent {
            RiddleAppTheme {
                val currentUser = userPrefs.getUser()

                Scaffold(
                    modifier = Modifier.Companion.fillMaxSize()
                ) { innerPadding ->

                    Box(
                        modifier = Modifier.Companion.padding(innerPadding)
                    ) {
                        if (currentUser != null) {
                            RootScreen()
                        } else {
                            val uiState by authViewModel.uiState.collectAsState()
                            when (uiState) {

                                is AuthUiState.Success -> {
                                    RootScreen()
                                }

                                is AuthUiState.Loading -> {
                                    Box(
                                        modifier = Modifier.Companion.fillMaxSize(),
                                        contentAlignment = Alignment.Companion.Center
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
}