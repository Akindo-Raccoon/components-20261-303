package com.ud.riddle.service

import com.ud.riddle.models.GameDiscoveryResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface GameApiService {
    @GET("category/{category}/language/{language}/discovery")
    suspend fun getDiscovery(
        @Path("category") category: String,
        @Path("language") language: String
    ): GameDiscoveryResponse

    companion object {
        private const val BASE_URL = "http://10.0.2.2:3000/" // Para el emulador de Android

        fun create(): GameApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GameApiService::class.java)
        }
    }
}
