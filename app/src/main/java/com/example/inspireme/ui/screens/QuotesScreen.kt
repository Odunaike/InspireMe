package com.example.inspireme.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.InspireMeTheme
import com.example.inspireme.R
import com.example.inspireme.models.AnimeQuoteModel
import com.example.inspireme.ui.viewmodels.AnimeNetworkState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuotesScreenApp(animeNetworkState: AnimeNetworkState, screenName: String){
    Scaffold(
        topBar = {QuoteTopAppBar(screenName)}
    ) {
        when (animeNetworkState) {
            is AnimeNetworkState.Success -> QuotesList(animeNetworkState.quotes)
            is AnimeNetworkState.Loading -> LoadingComposable()
            is AnimeNetworkState.Error -> ErrorComposable()
        }
    }
}

@Composable
fun QuotesList(quotesList: List<AnimeQuoteModel>){
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 57.dp)
    ){
        items(quotesList){
            SingleQuoteCard(it)
        }
    }
}

@Composable
private fun QuoteTopAppBar(screenName: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 5.dp, end = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text= screenName,
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun SingleQuoteCard(quote: AnimeQuoteModel) {
    Card (
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment =   Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = quote.quote,
                    modifier = Modifier
                        .weight(1f)
                        .padding(10.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
                Image(painter = painterResource(id = R.drawable.love_icon),
                    contentDescription = stringResource(id = R.string.like_quote),
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.CenterVertically),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
            ) {
                Text(
                    text = "- ${quote.character}, ${quote.anime}",
                    style = MaterialTheme.typography.labelSmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.padding(end = 25.dp, start = 10.dp, bottom = 10.dp )
                )
            }
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

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun SpecialPreview(){
    InspireMeTheme(useDarkTheme = true) {
        ErrorComposable()
    }
}