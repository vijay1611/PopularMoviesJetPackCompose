package com.vijay.popularmoviesjetpackcompose.data.mappers

import com.vijay.popularmoviesjetpackcompose.data.TvEntity
import com.vijay.popularmoviesjetpackcompose.data.remote.TvDto
import com.vijay.popularmoviesjetpackcompose.domain.TvModel

fun TvDto.toBeerEntity(): TvEntity {
    return TvEntity(
        id=id,
        name=name,
        tagline=tagline,
        description=description,
        firstBrewed = first_brewed,
        imageUrl = image_url

    )

}

fun TvEntity.toBeer():TvModel{
    return TvModel(
        id=id,
        name=name,
        tagline=tagline,
        description=description,
        firstBrewed = firstBrewed,
        imageUrl = imageUrl

    )
}

