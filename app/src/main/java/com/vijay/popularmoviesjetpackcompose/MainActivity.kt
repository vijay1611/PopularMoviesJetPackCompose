package com.vijay.popularmoviesjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.plcoding.composepaging3caching.presentation.BeerScreen
import com.vijay.popularmoviesjetpackcompose.presentation.TvViewModel
import com.vijay.popularmoviesjetpackcompose.ui.theme.PopularMoviesJetPackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PopularMoviesJetPackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel= hiltViewModel<TvViewModel>()
                    val tvModel = viewModel.beerPagingFlow.collectAsLazyPagingItems()

                   BeerScreen(
                       tvModel = tvModel)
                }
            }
        }
    }
}

