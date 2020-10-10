package com.baiana.simplemovies.network

import com.baiana.simplemovies.BuildConfig
import com.baiana.simplemovies.data.response.PopularMovieResponse
import com.baiana.simplemovies.util.CallResponse
import com.baiana.simplemovies.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("/movie/popular?api_key=${BuildConfig.API_TOKEN}&language=${Constants.LANGUAGE}")
    suspend fun getPopularMovies(@Query("page") pageNumber: Int = 1): Response<PopularMovieResponse>


//    @GET("/movie/popular?api_key=${BuildConfig.API_TOKEN}&language=${Constants.LANGUAGE}")
//    suspend fun getMovieDetails(@Query("page") pageNumber: Int = 1): CallResponse<MovieDetailsResult>


}
