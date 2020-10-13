package com.baiana.simplemovies.presentation

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.baiana.simplemovies.data.model.CallResponse
import com.baiana.simplemovies.data.model.ErrorModel
import com.baiana.simplemovies.data.model.MoviesViewState
import com.baiana.simplemovies.data.repository.MovieListRepository
import com.baiana.simplemovies.data.response.PopularMovieResponse
import com.baiana.simplemovies.data.response.PopularMovieResult
import com.baiana.simplemovies.testRules.CoroutinesTestRule
import io.mockk.*
import junit.framework.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

@ExperimentalCoroutinesApi
class MovieViewModelTest {

    private lateinit var getMoviesResponse: CallResponse<PopularMovieResponse>

    private lateinit var stateObserver: Observer<MoviesViewState>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    private val mockRepository = mockk<MovieListRepository> {
        coEvery { getPopularMovies() } answers { getMoviesResponse }
    }
    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setUp() {
        stateObserver = spyk(Observer { })
        viewModel = MoviesViewModel(mockRepository).also {
            it.viewState.observeForever(stateObserver)
        }
        getMoviesResponse = mockk()
    }

    @After
    fun finish() {
        unmockkAll()
    }

    @Test
    fun `When getMovies is called,start loading and then return list of movies`() {
        getMoviesResponse =
            CallResponse.Success(PopularMovieResponse(1, 5, 34, generateFakeResponseList()))
        val slots = mutableListOf<MoviesViewState>()

        viewModel.getMovieList()

        verify(exactly = 3) { stateObserver.onChanged(capture(slots)) }
        val (_, loading, movies) = slots

        assertTrue(loading.isLoading)
        assert(movies.movieList?.isNotEmpty() == true && !movies.isLoading)
        assert(movies.errorMessage == null && movies.errorId == null)
    }

    @Test
    fun `When getMovies is called,start loading and then return 404 error`() {
        val error = ErrorModel(404,"Filmes não encontrados")
        getMoviesResponse = CallResponse.Failure(error)
        val slots = mutableListOf<MoviesViewState>()

        viewModel.getMovieList()

        verify(exactly = 3) { stateObserver.onChanged(capture(slots)) }
        val (_, loading, fail) = slots

        assertTrue(loading.isLoading)
        assertNotNull(fail.errorMessage)
        assert(fail.errorMessage == error.message && !fail.isLoading)
        assertNull(fail.movieList)
    }


    private fun generateFakeResponseList(): List<PopularMovieResult> {
        val movieExemple = PopularMovieResult(
            Random(3).nextInt(),
            "title",
            "overview",
            Random(1).nextFloat(),
            "posterpath",
            "url"
        )
        return listOf(movieExemple, movieExemple, movieExemple, movieExemple)
    }


}