package com.example.inspireme.models

import kotlinx.serialization.Serializable

@Serializable
data class JamesQuoteModel(
    val rawText: String,
    val text: String,
    val source: String,
    val clickToTweetId: String
)
