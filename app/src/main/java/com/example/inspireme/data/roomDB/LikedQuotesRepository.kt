package com.example.inspireme.data.roomDB

import kotlinx.coroutines.flow.Flow

class LikedQuotesRepository(private val quoteDAO: QuoteDao) {
    suspend fun insertQuote(quote: QuoteItem) = quoteDAO.insert(quote)
    suspend fun deleteQuote(quote: QuoteItem) = quoteDAO.delete(quote)
    fun getAllLikedQuotes(): Flow<List<QuoteItem>> = quoteDAO.getAllLikedQuotes()
}