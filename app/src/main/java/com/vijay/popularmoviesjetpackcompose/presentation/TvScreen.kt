package com.plcoding.composepaging3caching.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.vijay.popularmoviesjetpackcompose.domain.TvModel
import com.vijay.popularmoviesjetpackcompose.presentation.BeerItem
import com.vijay.popularmoviesjetpackcompose.presentation.SearchBar

@Composable
fun BeerScreen(
    tvModel: LazyPagingItems<TvModel>
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = tvModel.loadState) {
        if(tvModel.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (tvModel.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    var searchQuery by remember { mutableStateOf("") }

    SearchBar(
        modifier = Modifier.padding(16.dp).height(20.dp),
        onSearch = { query ->
            // Perform search or filtering based on the query
            searchQuery = query
            // Call your search function here or update your data accordingly
        },
        onClear = {
            // Clear any search results or reset your data
            searchQuery = ""
        }
    )

    Box(modifier = Modifier.fillMaxSize()) {
        if(tvModel.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                items(tvModel) { tv ->
                    if(tv != null) {
                        BeerItem(
                            tvModel = tv,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                item {
                    if(tvModel.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}