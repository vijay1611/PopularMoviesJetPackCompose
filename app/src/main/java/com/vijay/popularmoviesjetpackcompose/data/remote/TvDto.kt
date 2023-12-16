package com.vijay.popularmoviesjetpackcompose.data.remote

data class TvDto(
    val id:Int,
    val name:String,
    val tagline:String,
    val description:String,
    val first_brewed:String,
    val image_url:String?
)
