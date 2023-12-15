package com.vijay.popularmoviesjetpackcompose.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.vijay.popularmoviesjetpackcompose.data.BeerEntity
import com.vijay.popularmoviesjetpackcompose.data.local.BeerDatabase
import com.vijay.popularmoviesjetpackcompose.data.remote.BeerApi
import com.vijay.popularmoviesjetpackcompose.data.remote.BeerRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesBeerDatabase(@ApplicationContext context:Context):BeerDatabase{
            return Room.databaseBuilder(
                context,
                BeerDatabase::class.java,
                "beers.db"
            ).build()
    }

    @Provides
    @Singleton
    fun providesBeerAPi():BeerApi{
        return Retrofit.Builder()
            .baseUrl(BeerApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create( )
    }

    @Provides
    @Singleton
    fun provideBeerPager(beerDb:BeerDatabase,beerApi: BeerApi):Pager<Int,BeerEntity>{
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = BeerRemoteMediator(
                beerDb=beerDb,
                beerApi=beerApi
            ),
            pagingSourceFactory = {
                beerDb.dao.pagingSource()
            }
        )
    }
}