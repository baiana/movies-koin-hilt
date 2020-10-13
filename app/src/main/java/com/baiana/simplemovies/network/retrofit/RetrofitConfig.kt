package com.baiana.simplemovies.network.retrofit


import com.baiana.simplemovies.network.MoviesService
import com.baiana.simplemovies.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitConfig {

    fun getInstance(): Retrofit =
        Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()


    fun provideMoviesAPI(retrofit: Retrofit): MoviesService =
        retrofit.create(MoviesService::class.java)
}