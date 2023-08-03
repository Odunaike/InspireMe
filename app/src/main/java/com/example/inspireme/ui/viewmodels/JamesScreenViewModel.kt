package com.example.inspireme.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inspireme.data.JamesClearDataLayer.NetworkJamesQuoteRepository
import com.example.inspireme.models.JamesQuoteModel
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface JamesNetworkState{
    data class Success(val quote: JamesQuoteModel): JamesNetworkState
    object Error: JamesNetworkState
    object Loading: JamesNetworkState
}

class JamesScreenViewModel: ViewModel() {

    var jamesNetworkState: JamesNetworkState by mutableStateOf(JamesNetworkState.Loading)

    init {
        getJamesQuote()
    }

    fun getJamesQuote(){
        viewModelScope.launch {
            try {
                val result = NetworkJamesQuoteRepository().getQuote()
                jamesNetworkState = JamesNetworkState.Success(result)
            }catch(e: IOException){
                jamesNetworkState = JamesNetworkState.Error
            }
        }
    }
}