package com.vijay.popularmoviesjetpackcompose.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.vijay.popularmoviesjetpackcompose.data.TvEntity
import com.vijay.popularmoviesjetpackcompose.data.local.TvDatabase
import com.vijay.popularmoviesjetpackcompose.data.remote.TvApi
import com.vijay.popularmoviesjetpackcompose.data.remote.TvRemoteMediator
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
    fun providesBeerDatabase(@ApplicationContext context:Context):TvDatabase{
            return Room.databaseBuilder(
                context,
                TvDatabase::class.java,
                "beers.db"
            ).build()
    }

    @Provides
    @Singleton
    fun providesBeerAPi():TvApi{
        return Retrofit.Builder()
            .baseUrl(TvApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create( )
    }

    @Provides
    @Singleton
    fun provideBeerPager(beerDb:TvDatabase, beerApi: TvApi):Pager<Int,TvEntity>{
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = TvRemoteMediator(
                beerDb=beerDb,
                beerApi=beerApi
            ),
            pagingSourceFactory = {
                beerDb.dao.pagingSource()
            }
        )
    }
}