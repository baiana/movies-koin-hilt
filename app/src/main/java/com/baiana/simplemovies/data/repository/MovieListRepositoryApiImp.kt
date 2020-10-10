package com.baiana.simplemovies.data.repository

import com.baiana.simplemovies.data.response.PopularMovieResponse
import com.baiana.simplemovies.data.response.PopularMovieResult
import com.baiana.simplemovies.network.MoviesService
import com.baiana.simplemovies.util.CallResponse

class MovieListRepositoryApiImp(private val api: MoviesService) : MovieListRepository {

    override suspend fun getPopularMovies(): CallResponse<PopularMovieResponse> {
        with(api.getPopularMovies()) {
            return@getPopularMovies if (isSuccessful && body() != null) {
                CallResponse.Success(body()!!)
            } else {
                CallResponse.Failure(code().toString() + message())
            }
        }
    }

    override suspend fun getPopularMoviesPaginated(page: Int): CallResponse<PopularMovieResponse> {
        with(api.getPopularMovies(page)) {
            return@getPopularMoviesPaginated if (isSuccessful && body() != null) {
                CallResponse.Success(body()!!)
            } else {
                CallResponse.Failure(code().toString() + message())
            }
        }
    }


}