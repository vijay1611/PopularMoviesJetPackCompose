package com.vijay.popularmoviesjetpackcompose.presentation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text


import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


import com.vijay.popularmoviesjetpackcompose.domain.TvModel
import com.vijay.popularmoviesjetpackcompose.ui.theme.PopularMoviesJetPackComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
    onClear: () -> Unit
) {
    val searchTextState = remember { mutableStateOf("") }

    OutlinedTextField(
        value = searchTextState.value,
        onValueChange = {
            searchTextState.value = it
            onSearch(it)
        },
        modifier = modifier.fillMaxWidth(),
        placeholder = { "Search Movies" },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(searchTextState.value)
            }
        ),
        trailingIcon = {
            if (searchTextState.value.isNotEmpty()) {
                IconButton(onClick = {
                    searchTextState.value = ""
                    onClear()
                }) {
                    Icon(Icons.Default.Clear, contentDescription = "Clear")
                }
            }
        }
    )
}




@Composable
fun BeerItem(
    tvModel:TvModel,
    modifier: Modifier=Modifier
){
 Card(
      modifier=modifier
  ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp)
        ) {
            AsyncImage(
                model =tvModel.imageUrl,
                contentDescription = tvModel.name,
                modifier= Modifier
                    .weight(1f)
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier=Modifier.weight(3f)
                .fillMaxHeight(),
                verticalArrangement = Arrangement.Center){
                Text(text = tvModel.name,
                    modifier=Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = tvModel.tagline,
                    modifier=Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = tvModel.description,
                    modifier=Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "First brewd in ${tvModel.firstBrewed}",
                    modifier=Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    fontSize = 10.sp
                    )
            }
        }
  }

}

@Preview
@Composable
fun BeerItemPreview(){
    PopularMoviesJetPackComposeTheme {
        BeerItem(
            tvModel = TvModel(
                id=1,
                name = "Beer",
                tagline = "This is cool beer",
                firstBrewed = "07/2023",
                description = "This is a description for beer. \n This is next line",
                imageUrl = null

            ),
            modifier = Modifier.fillMaxWidth())
    }

    }
