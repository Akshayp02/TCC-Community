package com.thecodingcult.truecommunity.community.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Define API interface
interface EmojiApiService {
    @GET("emojis?access_key=c46a19e555232c5750d70e2668e628433708b965") // Replace with your API key
    suspend fun getEmojis(): List<EmojiResponse>
}

// Emoji Response Model
data class EmojiResponse(
    val character: String,
    val slug: String // Name of the emoji
)

// Create Retrofit instance
object EmojiApi {
    val api: EmojiApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://emoji-api.com/") // API Base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EmojiApiService::class.java)
    }
}

