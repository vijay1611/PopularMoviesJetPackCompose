package com.vijay.popularmoviesjetpackcompose.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.vijay.popularmoviesjetpackcompose.data.TvEntity


@Dao
interface TvDao {

    @Upsert
    suspend fun upsertAll(beers: List<TvEntity>)

    @Query("SELECT * FROM tventity")
    fun pagingSource(): PagingSource<Int, TvEntity>

    @Query("DELETE FROM  tventity")
    suspend fun clearAll()
}