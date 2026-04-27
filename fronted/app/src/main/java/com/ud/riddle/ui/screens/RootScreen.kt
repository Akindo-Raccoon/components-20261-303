package com.ud.riddle.ui.screens

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.ud.riddle.viewmodels.GameViewModel
import com.ud.riddle.viewmodels.GameViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RootScreen() {

    val context = LocalContext.current
    val application = context.applicationContext as Application

    var isOnline by remember { mutableStateOf<Boolean?>(null) }

    if (isOnline == null) {
        ModeSelectionScreen { selected ->
            isOnline = selected
        }
    } else {

        val viewModel: GameViewModel = viewModel(
            factory = GameViewModelFactory(application, isOnline!!)
        )

        MatchScreen(viewModel = viewModel)
    }
}