package com.vijay.popularmoviesjetpackcompose.domain

data class TvModel(
    val id:Int,
    val name:String,
    val tagline:String,
    val firstBrewed:String,
    val description:String,
    val imageUrl:String?
)
