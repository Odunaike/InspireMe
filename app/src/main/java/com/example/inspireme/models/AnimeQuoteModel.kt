package com.example.inspireme.models

import kotlinx.serialization.Serializable

@Serializable
data class AnimeQuoteModel(
    val anime: String,
    val character: String,
    val quote: String
)
