package com.baiana.simplemovies.network.retrofit

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitConfig {

    fun getInstance(){
        Retrofit.Builder().baseUrl(BuildConfig.BASE_API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
    fun provideMoviesAPI(retrofit: Retrofit): MoviesService = retrofit.create(MoviesService::class.java)
}