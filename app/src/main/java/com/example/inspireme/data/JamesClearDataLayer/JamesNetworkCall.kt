package com.example.inspireme.data.JamesClearDataLayer

import com.example.inspireme.models.JamesQuoteModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

interface JamesApiService{
    @GET("random")
    suspend fun getQuote(): JamesQuoteModel
}

private val BASE_URL = "https://www.jcquotes.com/api/quotes/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

object  JamesApi{
    val retrofitService: JamesApiService by lazy {
        retrofit.create(JamesApiService::class.java)
    }
}

