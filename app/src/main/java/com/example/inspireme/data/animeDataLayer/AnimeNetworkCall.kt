package com.example.inspireme.data.animeDataLayer

import com.example.inspireme.models.AnimeQuoteModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

interface AnimeApiService{
    @GET("quotes")
    suspend fun getAnimeQuotes(): List<AnimeQuoteModel>
}

private const val BASE_URL = "https://animechan.xyz/api/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

object AnimeApi {
    val retrofitService : AnimeApiService by lazy{
        retrofit.create(AnimeApiService::class.java)
    }
}

