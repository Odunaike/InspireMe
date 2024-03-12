package com.example.inspireme.data.roomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface QuoteDao {
    @Insert
    suspend fun insert(quote: QuoteItem)
    @Delete
    suspend fun delete(quote: QuoteItem)
    @Query("SELECT * FROM likedquotes ORDER BY id ASC")
    fun getAllLikedQuotes(): Flow<List<QuoteItem>>
}