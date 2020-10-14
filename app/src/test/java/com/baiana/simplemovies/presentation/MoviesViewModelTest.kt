package com.baiana.simplemovies.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.baiana.simplemovies.data.model.CallResponse
import com.baiana.simplemovies.data.model.ErrorModel
import com.baiana.simplemovies.data.model.MoviesViewState
import com.baiana.simplemovies.data.repository.MovieListRepository
import com.baiana.simplemovies.data.response.PopularMovieResponse
import com.baiana.simplemovies.data.response.PopularMovieResult
import io.mockk.*
import junit.framework.Assert.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import java.util.*

@ExperimentalCoroutinesApi
class MoviesViewModelTest() : KoinTest {

    private lateinit var responseMovieList: CallResponse<PopularMovieResponse>
    private lateinit var stateObserver: Observer<MoviesViewState>
    private lateinit var viewModel: MoviesViewModel

    @get: Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(module(override = true) {
            single<MovieListRepository> { mockk(relaxed = true) { coEvery { getPopularMovies() } answers { responseMovieList } } }
            viewModel { MoviesViewModel(get()) }
        })
    }

    @Before
    fun init() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        responseMovieList = mockk()
        stateObserver = spyk(Observer { })
        viewModel = get<MoviesViewModel>().also { it.viewState.observeForever(stateObserver) }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
        stopKoin()
    }

    @Test
    fun `When getMovies is called,start loading and then return list of movies`() {
        responseMovieList =
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
        val error = ErrorModel(404, "Filmes não encontrados")
        responseMovieList = CallResponse.Failure(error)
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