package com.baiana.simplemovies.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baiana.simplemovies.data.model.*
import com.baiana.simplemovies.data.repository.MovieListRepository
import com.baiana.simplemovies.data.model.CallResponse.*
import com.baiana.simplemovies.util.SingleLiveEvent
import com.baiana.simplemovies.util.convertToMovieModelList
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

class MoviesViewModel(private val api: MovieListRepository) : ViewModel() {

    private val _viewState = SingleLiveEvent<MoviesViewState>()
    val viewState get() = _viewState

    init {
        _viewState.value = MoviesViewState()
    }

    fun getMovieList() {
        _viewState.postValue(displayLoading())
        viewModelScope.launch {
            val response = api.getPopularMovies()
            if (response is Success) {
                val convertedList = response.result.results?.convertToMovieModelList()
                displayList(convertedList!!)
            } else if (response is Failure) {
                handleError(response.error)
            }
        }
    }

    private fun handleError(error: ErrorModel) {
        _viewState.postValue(displayError(error.message))

    }

    private fun displayList(convertedList: List<Movie>) {
        _viewState.postValue(displayMovies(convertedList))
    }
}