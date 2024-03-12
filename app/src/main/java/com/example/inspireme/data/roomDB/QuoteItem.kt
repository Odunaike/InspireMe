package com.example.inspireme.data.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "likedquotes")
data class QuoteItem (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val author: String,
    val quote: String
)