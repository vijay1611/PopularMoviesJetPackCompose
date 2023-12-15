package com.vijay.popularmoviesjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.vijay.popularmoviesjetpackcompose.presentation.BeerScreen
import com.vijay.popularmoviesjetpackcompose.presentation.BeerViewModel
import com.vijay.popularmoviesjetpackcompose.ui.theme.PopularMoviesJetPackComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

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
                    val viewModel= hiltViewModel<BeerViewModel>()
                    val beers = viewModel.beerPagingFlow.collectAsLazyPagingItems()
                   BeerScreen(
                       beers = beers)
                }
            }
        }
    }
}
