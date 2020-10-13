package com.baiana.simplemovies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baiana.simplemovies.data.repository.MovieListRepository
import com.baiana.simplemovies.data.model.CallResponse.*
import com.baiana.simplemovies.util.convertToMovieModelList
import kotlinx.coroutines.launch

class MoviesViewModel(private val api: MovieListRepository) : ViewModel() {


    fun getMovieList() {
        viewModelScope.launch {
            val response = api.getPopularMovies()
            if (response is Success) {
                val convertedList = response.result.results.convertToMovieModelList()
            } else {
                //todo handle error
            }
        }

    }
}