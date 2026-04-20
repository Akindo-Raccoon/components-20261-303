package com.ud.riddle.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ud.riddle.repositories.FirebaseGameDataSource
import com.ud.riddle.repositories.LocalGameDataSource

class GameViewModelFactory(
    private val application: Application,
    private val isOnline: Boolean
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val dataSource = if (isOnline) {
            FirebaseGameDataSource()
        } else {
            LocalGameDataSource()
        }

        return GameViewModel(application, dataSource) as T
    }
}