package com.ud.connect4ude.utils

import android.content.Context
import com.google.gson.Gson
import androidx.core.content.edit
import com.ud.riddle.models.User

class UserPreferences(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveUser(user: User) {
        val json = gson.toJson(user)
        sharedPreferences.edit { putString("user_data", json) }
    }

    fun getUser(): User? {
        val json = sharedPreferences.getString("user_data", null)
        return if (json != null) {
            gson.fromJson(json, User::class.java)
        } else null
    }

    fun clearData() {
        sharedPreferences.edit { clear() }
    }
}