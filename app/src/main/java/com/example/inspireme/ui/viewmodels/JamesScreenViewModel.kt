package com.example.inspireme.ui.viewmodels


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.inspireme.InspireMeApplication
import com.example.inspireme.data.JamesClearDataLayer.NetworkJamesQuoteRepository
import com.example.inspireme.data.roomDB.LikedQuotesRepository
import com.example.inspireme.data.roomDB.QuoteDao
import com.example.inspireme.data.roomDB.QuoteItem
import com.example.inspireme.models.JamesQuoteModel
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface JamesNetworkState{
    data class Success(val quote: JamesQuoteModel): JamesNetworkState
    object Error: JamesNetworkState
    object Loading: JamesNetworkState
}

class JamesScreenViewModel(private val likedQuotesDao: QuoteDao): ViewModel() {

    var jamesNetworkState: JamesNetworkState by mutableStateOf(JamesNetworkState.Loading)

    init {
        getJamesQuote()
    }

    fun getJamesQuote() {
        viewModelScope.launch {
            try {
                val result = NetworkJamesQuoteRepository().getQuote()
                jamesNetworkState = JamesNetworkState.Success(result)
            } catch (e: IOException) {
                jamesNetworkState = JamesNetworkState.Error
            }
        }
    }

    suspend fun likeQuote(quote: String) {
        likedQuotesDao.insert(QuoteItem(quote = quote, author = "James Clear"))
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as InspireMeApplication)
                JamesScreenViewModel(application.database.quoteDao())
            }
        }
    }
}
