package com.example.inspireme.data.JamesClearDataLayer

import com.example.inspireme.models.JamesQuoteModel


interface JamesClearRepository {
    suspend fun getQuote(): JamesQuoteModel
}
class NetworkJamesQuoteRepository : JamesClearRepository{
    override suspend fun getQuote(): JamesQuoteModel {
        return JamesApi.retrofitService.getQuote()
    }
}