package com.baiana.simplemovies.di

import com.baiana.simplemovies.data.repository.MovieListRepository
import com.baiana.simplemovies.data.repository.MovieListRepositoryApiImp
import com.baiana.simplemovies.network.MoviesService
import com.baiana.simplemovies.network.retrofit.RetrofitConfig
import com.baiana.simplemovies.presentation.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single<Retrofit> { RetrofitConfig.getInstance() }
    factory<MoviesService> { RetrofitConfig.provideMoviesAPI(get()) }
}


val moviesListModule = module {
    single<MovieListRepository> { MovieListRepositoryApiImp(get()) }
    viewModel { MoviesViewModel(get()) }
}