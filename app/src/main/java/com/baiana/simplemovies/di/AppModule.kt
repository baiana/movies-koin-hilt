package com.baiana.simplemovies.di

import com.baiana.simplemovies.data.repository.MovieListRepository
import com.baiana.simplemovies.data.repository.MovieListRepositoryApiImp
import com.baiana.simplemovies.network.MoviesService
import com.baiana.simplemovies.network.retrofit.RetrofitConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit = RetrofitConfig.getInstance()

    @Singleton
    @Provides
    fun provideMoviesAPI(retrofit: Retrofit): MoviesService = RetrofitConfig.provideMoviesAPI(retrofit)

    @Singleton
    @Provides
    fun providesRepository(moviesApi: MoviesService): MovieListRepository = MovieListRepositoryApiImp(moviesApi)


}