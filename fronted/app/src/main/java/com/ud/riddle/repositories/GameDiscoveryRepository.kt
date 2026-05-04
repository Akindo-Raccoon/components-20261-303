package com.ud.riddle.repositories

import com.ud.riddle.service.GameApiService
import com.ud.riddle.models.GameDiscoveryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GameDiscoveryRepository(
    private val apiService: GameApiService = GameApiService.create()
) {
    suspend fun getRiddle(category: String, language: String): Result<GameDiscoveryResponse> {
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
