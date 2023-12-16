package com.vijay.popularmoviesjetpackcompose.data.remote


import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.vijay.popularmoviesjetpackcompose.data.TvEntity
import com.vijay.popularmoviesjetpackcompose.data.local.TvDatabase
import com.vijay.popularmoviesjetpackcompose.data.mappers.toBeerEntity
import kotlinx.coroutines.delay
import okio.IOException

@OptIn(ExperimentalPagingApi::class)
class TvRemoteMediator (
    private val beerDb : TvDatabase,
    private val beerApi: TvApi
):RemoteMediator<Int,TvEntity>() {


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TvEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType){
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null){
                        1
                    }else{
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            delay(2000L)
            val beers = beerApi.getBeers(
                apiKey="ae20ddebbad1388a330906a1449e37f7",
                page = loadKey,
                pageCount = state.config.pageSize
            )

            beerDb.withTransaction {
                if(loadType== LoadType.REFRESH){
                    beerDb.dao.clearAll()
                }
                val beerEntities = beers.map{it.toBeerEntity()}
                beerDb.dao.upsertAll(beerEntities)
            }
            MediatorResult.Success(
                endOfPaginationReached = beers.isEmpty()
            )
        }catch (e: IOException){
            MediatorResult.Error(e)
    }catch (e:retrofit2.HttpException){
        MediatorResult.Error(e)
    }
}
}