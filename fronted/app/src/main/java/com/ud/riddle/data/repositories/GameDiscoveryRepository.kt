package com.ud.riddle.data.repositories

import com.ud.riddle.data.service.GameApiService
import com.ud.riddle.dominio.models.GameDiscoveryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GameDiscoveryRepository(
    private val apiService: GameApiService = GameApiService.create()
) {
    suspend fun getDiscovery(category: String, language: String): Result<GameDiscoveryResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getDiscovery(category, language)
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
