package com.baiana.simplemovies.data.model

import com.baiana.simplemovies.presentation.MoviesViewModel

data class MoviesViewState internal constructor(
    val movieList: ArrayList<Movie>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val errorId: Int? = null,
    val searchResult: ArrayList<Movie>? = null
)

fun MoviesViewModel.displayError(error: String? = null, errorId: Int? = null) = MoviesViewState(errorId = errorId, errorMessage = error, isLoading = false)
fun MoviesViewModel.displayLoading() = MoviesViewState(isLoading = true)
fun MoviesViewModel.displayMovies(list: List<Movie>) = MoviesViewState(movieList = ArrayList<Movie>(list), isLoading = false)
