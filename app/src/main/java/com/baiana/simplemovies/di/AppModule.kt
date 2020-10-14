package com.baiana.simplemovies.di

import com.baiana.simplemovies.data.repository.MovieListRepository
import com.baiana.simplemovies.data.repository.MovieListRepositoryApiImp
import com.baiana.simplemovies.network.MoviesService
import com.baiana.simplemovies.network.retrofit.RetrofitConfig
import com.baiana.simplemovies.presentation.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single<Retrofit> { RetrofitConfig.getInstance() }
    single<MoviesService> { RetrofitConfig.provideMoviesAPI(get<Retrofit>()) }

    factory<MovieListRepository> { MovieListRepositoryApiImp(get<MoviesService>()) }
    viewModel { MoviesViewModel(get<MovieListRepository>()) }
}


