package com.vijay.popularmoviesjetpackcompose.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TvEntity(
    @PrimaryKey
    val id:Int,
    val name:String,
    val tagline:String,
    val description:String,
    val firstBrewed:String,
    val imageUrl:String?
)
