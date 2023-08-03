package com.example.inspireme.data.animeDataLayer

import com.example.inspireme.models.AnimeQuoteModel

interface AnimeRepository {
    suspend fun getAnimeQuotes(): List<AnimeQuoteModel>
}
class NetworkAnimeQuotesRepository : AnimeRepository {
    override suspend fun getAnimeQuotes(): List<AnimeQuoteModel> {
        return AnimeApi.retrofitService.getAnimeQuotes()
    }
}