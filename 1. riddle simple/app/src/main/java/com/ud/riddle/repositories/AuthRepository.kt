package com.ud.riddle.repositories

import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

class AuthRepository {
    private val auth: FirebaseAuth = Firebase.auth

    suspend fun login(email: String, password: String): Result<String> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val userEmail = result.user?.email ?: "Usuario desconocido"
            Result.success(userEmail)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}