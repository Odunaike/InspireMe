package com.example.inspireme.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.InspireMeTheme
import com.example.compose.gradient_black
import com.example.compose.gradient_white
import com.example.inspireme.R
import com.example.inspireme.collections.homeCategoryList
import com.example.inspireme.models.HomeCategoryModel

@ExperimentalMaterial3Api
@Composable
fun HomeScreenApp(
    onClickAnime:()->Unit,
    onClickJames: () -> Unit
){
    Scaffold(
        topBar = {HomeTopAppBar()}
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(top = 57.dp)
                .verticalScroll(state = rememberScrollState())
        ) {
            HomeCategoryCard(category = homeCategoryList.get(0)) {}
            HomeCategoryCard(category = homeCategoryList.get(1)) {}
            HomeCategoryCard(category = homeCategoryList.get(2), onClick = onClickAnime)
            HomeCategoryCard(category = homeCategoryList.get(3)) {}
            HomeCategoryCard(category = homeCategoryList.get(4), onClick = onClickJames)
        }
    }
}

@Composable
fun HomeTopAppBar(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 5.dp, end = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text= stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(5.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = { /*TODO*/ },
            ) {
            Image(painter = painterResource(id = R.drawable.love_icon),
                contentDescription = stringResource(id = R.string.liked_quotes),
                modifier = Modifier
                    .padding(5.dp)
                    .size(50.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
        }
    }
}


@Composable
fun HomeCategoryCard(category: HomeCategoryModel, onClick: ()-> Unit){
    Card(
        modifier = Modifier
            .wrapContentSize()
            .fillMaxWidth()
            .height(250.dp)
            .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 2.dp)
            .clickable(onClick = onClick, enabled = true)
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = category.imageID),
                contentScale = ContentScale.Crop,
                contentDescription = null)
            val brush = Brush.verticalGradient(
                listOf(gradient_white, gradient_black)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(90.dp)
                    .background(brush = brush),
                verticalArrangement = Arrangement.Bottom

            ) {
                Text(text = stringResource(id = category.categoryName),
                    color = Color.White,
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier
                        .padding(
                            start = 20.dp,
                            end = 10.dp,
                            top = 10.dp,
                            bottom = 10.dp,
                        )
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true )
@Composable
fun MyPreview(){
    InspireMeTheme(useDarkTheme = false) {
       // HomeScreenApp {}
    }
}