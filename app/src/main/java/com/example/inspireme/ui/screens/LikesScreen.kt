@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.inspireme.ui.screens

import android.graphics.drawable.Icon
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.inspireme.R
import com.example.inspireme.data.roomDB.QuoteItem
import com.example.inspireme.ui.viewmodels.LikedQuotesScreenViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@Composable
fun LikesScreenApp(
    viewModel: LikedQuotesScreenViewModel
){
    val likedUiState by viewModel.likedUiState.collectAsState()
    Scaffold (
        topBar = {LikesScreenTopAppBar("Liked Quotes")}
    ){
        LikeScreenBody(likedList = likedUiState.likedQuotes,
            modifier = Modifier.padding(top = 57.dp),
            viewModel = viewModel
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
fun LikeScreenBody(likedList: List<QuoteItem>, modifier: Modifier, viewModel: LikedQuotesScreenViewModel){
    if (likedList.isEmpty()){
        Text(text = "no liked quotes")
    }else{
        LikedList(likedList = likedList, modifier = modifier, viewModel = viewModel)
    }
}
@Composable
fun LikedList(
    likedList: List<QuoteItem>,
    modifier: Modifier,
    viewModel: LikedQuotesScreenViewModel
){
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(modifier = modifier){
        items(items = likedList, key = {it.id}){
            
            LikedItem(
                item = it,
                onRemove = {
                    coroutineScope.launch {
                        viewModel.deleteQuote(it)
                    }
                }
            )
        }
    }
}
@Composable
fun LikedItem(item: QuoteItem, onRemove: ()-> Unit){

    var isRemoveAnimate by remember {
        mutableStateOf(true)
    }
    val coroutineScope = rememberCoroutineScope()
    val swipeToDismissSate = rememberSwipeToDismissBoxState(
        confirmValueChange = {state ->
            if (state == SwipeToDismissBoxValue.EndToStart){
                coroutineScope.launch {
                    isRemoveAnimate = false
                    delay(500) // you have to use an equal delay with duration of your animation.Also your animation shpuld start with a delay before the item removed from the database. Else the animation will not show
                    onRemove()
                }
                true
            }else
                false
        }
    )

    AnimatedVisibility(
        visible = isRemoveAnimate,
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = 500),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismissBox(
            state = swipeToDismissSate,
            backgroundContent = {
                /*
                val backGroundColor by animateColorAsState(
                    targetValue = when(swipeToDismissSate.dismissDirection){ //there is .currentValue and .dismissState
                        SwipeToDismissBoxValue.EndToStart -> Color.Red
                        SwipeToDismissBoxValue.StartToEnd -> Color.Green
                        SwipeToDismissBoxValue.Settled -> Color.Transparent
                    },
                    label = "Animate bg color"
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backGroundColor)
                )
                 */
                val color = if(swipeToDismissSate.dismissDirection == SwipeToDismissBoxValue.EndToStart){
                    Color.Red
                }else Color.Transparent
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
                        .background(color = color),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        modifier = Modifier.padding(end = 20.dp),
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        ) {
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
    }

}

@Preview
@Composable
fun LikesScreenApppreview(){
    LikedItem(
        item = QuoteItem(
            author = "David",
            quote = "In life, you have to be good always, so that you can enjoy favour among men"
        ),
        onRemove = {}
    )
}