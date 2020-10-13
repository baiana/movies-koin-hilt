package com.baiana.simplemovies.data.repository

import com.baiana.simplemovies.data.response.PopularMovieResponse
import com.baiana.simplemovies.network.MoviesService
import com.baiana.simplemovies.data.model.CallResponse
import com.baiana.simplemovies.data.model.ErrorModel
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton


class MovieListRepositoryApiImp @Inject constructor(private val api: MoviesService) : MovieListRepository {

    override suspend fun getPopularMovies(): CallResponse<PopularMovieResponse> {
        with(api.getPopularMovies()) {
            return@getPopularMovies if (isSuccessful && body() != null) {
                CallResponse.Success(body()!!)
            } else {
                CallResponse.Failure(ErrorModel(code(), message()))
            }
        }
    }

    override suspend fun getPopularMoviesPaginated(page: Int): CallResponse<PopularMovieResponse> {
        with(api.getPopularMovies(page)) {
            return@getPopularMoviesPaginated if (isSuccessful && body() != null) {
                CallResponse.Success(body()!!)
            } else {
                CallResponse.Failure(ErrorModel(code(), message()))
            }
        }
    }


}