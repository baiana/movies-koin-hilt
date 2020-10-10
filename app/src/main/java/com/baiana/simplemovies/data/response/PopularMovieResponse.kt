package com.baiana.simplemovies.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularMovieResponse(
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "page")
    val currentPage: Int,
    val results: PopularMovieBodyResponse

)