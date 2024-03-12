package com.example.inspireme.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.InspireMeTheme
import com.example.inspireme.R
import com.example.inspireme.models.JamesQuoteModel
import com.example.inspireme.ui.viewmodels.JamesNetworkState
import com.example.inspireme.ui.viewmodels.JamesScreenViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteCardApp(jamesNetworkState: JamesNetworkState, screenName: String, viewModel: JamesScreenViewModel){
    Scaffold(
        topBar = {QuoteTopAppBar(screenName = screenName)},
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        when (jamesNetworkState){
            is JamesNetworkState.Success -> QuoteCard(quote = jamesNetworkState.quote, viewModel = viewModel)
            is JamesNetworkState.Loading ->     LoadingComposable()
            is JamesNetworkState.Error -> ErrorComposable()
        }

    }
}

@Composable
private fun QuoteTopAppBar(screenName: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text= screenName,
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
fun QuoteCard(quote: JamesQuoteModel, viewModel: JamesScreenViewModel){
    val coroutineScope = rememberCoroutineScope()
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp, bottom = 80.dp, start = 40.dp, end = 50.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 7.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp,),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = quote.text,
                style = MaterialTheme.typography.displayMedium,
                fontSize = 24.sp
            )
            Image(painter = painterResource(id = R.drawable.love_icon),
                contentDescription = stringResource(id = R.string.liked_quotes),
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(30.dp)
                    .shadow(elevation = 3.dp, shape = CircleShape)
                    .clickable(enabled = true, onClick = {       //added the function to add a quote to db
                        coroutineScope.launch {
                            viewModel.likeQuote(quote.text)
                        }
                    }),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
        }
        
    }
}

@Composable
private fun LoadingComposable(){

}
@Composable
private fun ErrorComposable(){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        IconButton(onClick = { /*TODO*/ }) {
            Image(
                painter = painterResource(id = R.drawable.baseline_refresh_24),
                modifier = Modifier.size(40.dp),
                contentDescription = "refresh" ,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
            Text(text = "Error! Click to refresh",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview (){
  QuoteTopAppBar(screenName = "hello")
}