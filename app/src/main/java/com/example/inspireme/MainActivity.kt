@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.inspireme

import android.bluetooth.BluetoothHidDeviceAppQosSettings
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.compose.InspireMeTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inspireme.ui.screens.HomeScreenApp
import com.example.inspireme.ui.screens.QuoteCardApp
import com.example.inspireme.ui.screens.QuotesScreenApp
import com.example.inspireme.ui.viewmodels.JamesScreenViewModel
import com.example.inspireme.ui.viewmodels.QuotesScreenViewModel

enum class QuotesAppScreens() {
    Home,
    Anime,
    James
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InspireMeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyNavigation()
                }
            }
        }
    }
}


@Composable
fun MyNavigation(
    navController: NavHostController = rememberNavController()
){
    Scaffold() {
        NavHost(
            navController = navController,
            startDestination = QuotesAppScreens.Home.name,
            modifier = Modifier.padding(it)
        ){

            composable(route = QuotesAppScreens.Home.name){
                HomeScreenApp(
                    onClickAnime = {navController.navigate(QuotesAppScreens.Anime.name)},
                    onClickJames = {navController.navigate(QuotesAppScreens.James.name)}
                )
            }
            composable(route = QuotesAppScreens.Anime.name){
                val viewModel: QuotesScreenViewModel = viewModel()
                val networkState = viewModel.animeNetworkState
                QuotesScreenApp(networkState, "${QuotesAppScreens.Anime.name} Quotes")
            }
            composable(route = QuotesAppScreens.James.name){
                val viewModel: JamesScreenViewModel = viewModel()
                val networkState = viewModel.jamesNetworkState
                QuoteCardApp(jamesNetworkState = networkState, screenName = "James Clear Quotes" )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InspireMeTheme {

    }
}