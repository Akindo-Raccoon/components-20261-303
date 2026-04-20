package com.ud.riddle.repositories

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.ud.connect4ude.utils.UserPreferences
import com.ud.riddle.models.User
import kotlinx.coroutines.tasks.await

class AuthRepository {
    private val auth: FirebaseAuth = Firebase.auth

    suspend fun login(email: String, password: String, userPrefs: UserPreferences): Result<String> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val userEmail = result.user?.email ?: "Usuario desconocido"
            val userUid = result.user?.uid ?: "Usuario desconocido"
            userPrefs.saveUser(User(userUid, userEmail))
            Result.success(userEmail)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}