package com.baiana.simplemovies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baiana.simplemovies.data.model.*
import com.baiana.simplemovies.data.repository.MovieListRepository
import com.baiana.simplemovies.data.model.CallResponse.*
import com.baiana.simplemovies.util.SingleLiveEvent
import com.baiana.simplemovies.util.convertToMovieModelList
import kotlinx.coroutines.launch

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
                response.result.results?.convertToMovieModelList()?.apply {
                    displayList(this)
                }
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