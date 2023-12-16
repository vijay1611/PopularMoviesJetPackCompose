package com.vijay.popularmoviesjetpackcompose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.vijay.popularmoviesjetpackcompose.data.TvEntity
import com.vijay.popularmoviesjetpackcompose.data.mappers.toBeer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@HiltViewModel
class TvViewModel @Inject constructor(
    pager: Pager<Int,TvEntity>
)  :ViewModel() {

    val beerPagingFlow = pager
        .flow
        .map { pagingData-> pagingData.map { it.toBeer() }
        }.cachedIn(viewModelScope)

}