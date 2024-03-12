package com.example.inspireme.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.inspireme.InspireMeApplication
import com.example.inspireme.data.roomDB.LikedQuotesRepository
import com.example.inspireme.data.roomDB.QuoteDao
import com.example.inspireme.data.roomDB.QuoteItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class LikedQuotesScreenViewModel(private val likedQuotesDao: QuoteDao): ViewModel(){
    val likedUiState: StateFlow<LikedUiState> = likedQuotesDao.getAllLikedQuotes().map { LikedUiState(it)}
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = LikedUiState()
        )
    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as InspireMeApplication)
                LikedQuotesScreenViewModel(application.database.quoteDao())
            }
        }
    }
}
data class LikedUiState(val likedQuotes: List<QuoteItem> = listOf())