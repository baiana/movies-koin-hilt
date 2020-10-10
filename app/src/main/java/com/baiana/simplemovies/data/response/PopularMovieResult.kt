package com.baiana.simplemovies.data.response

import com.baiana.simplemovies.util.Constants
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularMovieResult(
    val id: Int,
    val title: String,
    val overview: String,
    @Json(name = "vote_average")
    val rating: Float,
    @Json(name = "poster_path")
    val posterUrl: String,
    @Json(name = "backdrop_path")
    val backdropUrl: String
)

fun PopularMovieResult.getCompletePosterUrl() = Constants.BASE_PICTURE_URL + posterUrl
fun PopularMovieResult.getCompleteBackdropUrl() = Constants.BASE_PICTURE_URL + backdropUrl
