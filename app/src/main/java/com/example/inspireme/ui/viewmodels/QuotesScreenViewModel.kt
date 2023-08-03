package com.example.inspireme.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inspireme.data.animeDataLayer.NetworkAnimeQuotesRepository
import com.example.inspireme.models.AnimeQuoteModel
import com.example.inspireme.models.JamesQuoteModel
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AnimeNetworkState{
    data class Success(val quotes: List<AnimeQuoteModel>): AnimeNetworkState
    object Error: AnimeNetworkState
    object Loading: AnimeNetworkState
}
class QuotesScreenViewModel: ViewModel() {

    var animeNetworkState: AnimeNetworkState by mutableStateOf(AnimeNetworkState.Loading)

    init {
        getAnimeQuotes()
    }

    fun getAnimeQuotes(){
        viewModelScope.launch {
            try {
                val result = NetworkAnimeQuotesRepository().getAnimeQuotes()
                animeNetworkState = AnimeNetworkState.Success(result)
            }catch(e: IOException){
                animeNetworkState = AnimeNetworkState.Error
            }
        }
    }

}