package com.baiana.simplemovies.data.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularMovieBodyResponse(val movieList:List<PopularMovieResult>)
