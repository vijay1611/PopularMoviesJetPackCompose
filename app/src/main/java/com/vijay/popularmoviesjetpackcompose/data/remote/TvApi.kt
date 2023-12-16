package com.vijay.popularmoviesjetpackcompose.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface TvApi {
        @GET("beers")
        suspend fun getBeers(
            @Query("api_key") apiKey: String,
            @Query("page") page:Int,
            @Query("per_page") pageCount : Int,

        ):List<TvDto>

        companion object {
            const val BASE_URL = "https://api.punkapi.com/v2/"
        }


}