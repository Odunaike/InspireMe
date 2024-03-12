package com.example.inspireme.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.inspireme.R
import com.example.inspireme.data.roomDB.QuoteItem
import com.example.inspireme.ui.viewmodels.LikedQuotesScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LikesScreenApp(
    viewModel: LikedQuotesScreenViewModel
){
    val likedUiState by viewModel.likedUiState.collectAsState()
    Scaffold (
        topBar = {LikesScreenTopAppBar("Liked Quotes")}
    ){
        LikeScreenBody(likedList = likedUiState.likedQuotes,
            modifier = Modifier.padding(top = 57.dp)
        )
    }
}
@Composable
fun LikesScreenTopAppBar(screenName: String){
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
fun LikeScreenBody(likedList: List<QuoteItem>, modifier: Modifier){
    if (likedList.isEmpty()){
        Text(text = "no liked quotes")
    }else{
        LikedList(likedList = likedList, modifier)
    }
}
@Composable
fun LikedList(
    likedList: List<QuoteItem>,
    modifier: Modifier
){
    LazyColumn(modifier = modifier){
        items(items = likedList){
            LikedItem(item = it)
        }
    }
}
@Composable
fun LikedItem(item: QuoteItem){
    Card (
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = item.quote,
                    modifier = Modifier
                        .weight(1f)
                        .padding(10.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
                /*
                Image(
                    painter = painterResource(id = R.drawable.love_icon),
                    contentDescription = stringResource(id = R.string.like_quote),
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.CenterVertically),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
                 */
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Text(
                    text = "- ${item.author}",
                    style = MaterialTheme.typography.labelSmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.padding(end = 25.dp, start = 10.dp, bottom = 10.dp)
                )
            }
        }
    }
}
@Preview
@Composable
fun LikesScreenApppreview(){
    LikedItem(item = QuoteItem(author = "David", quote = "In life, you have to be good always, so that you can enjoy favour among men"))
}