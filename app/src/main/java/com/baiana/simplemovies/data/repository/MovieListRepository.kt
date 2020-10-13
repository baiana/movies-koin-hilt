package com.baiana.simplemovies.data.repository

import com.baiana.simplemovies.data.response.PopularMovieResponse
import com.baiana.simplemovies.data.model.CallResponse

interface MovieListRepository {
    suspend fun getPopularMovies(): CallResponse<PopularMovieResponse>
    suspend fun getPopularMoviesPaginated(page: Int): CallResponse<PopularMovieResponse>
}