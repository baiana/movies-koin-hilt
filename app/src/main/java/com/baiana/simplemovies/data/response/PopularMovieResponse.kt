package com.baiana.simplemovies.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularMovieResponse(
    @Json(name = "page")
    val currentPage: Int,
    @Json(name = "total_results")
    val totalResults: Int,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "results")
    val results: List<PopularMovieResult>? = null
)