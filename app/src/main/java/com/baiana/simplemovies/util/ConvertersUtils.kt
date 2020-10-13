package com.baiana.simplemovies.util

import com.baiana.simplemovies.data.model.Movie
import com.baiana.simplemovies.data.response.PopularMovieResult
import kotlin.math.roundToInt

fun List<PopularMovieResult>.convertToMovieModelList() =
    this.map { it ->
        val backdrop = if (it.backdropUrl.isNotBlank()) it.getCompleteBackdropUrl() else null
        val stars = it.rating.roundToInt()
        Movie(it.id, it.title, it.overview, stars, it.getCompletePosterUrl(), backdrop)
    }
