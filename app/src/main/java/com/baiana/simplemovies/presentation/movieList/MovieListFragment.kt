package com.baiana.simplemovies.presentation.movieList

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.baiana.simplemovies.R
import com.baiana.simplemovies.data.model.Movie
import com.baiana.simplemovies.presentation.MoviesViewModel
import com.baiana.simplemovies.presentation.movieList.MoviesRecyclerAdapter.*
import kotlinx.android.synthetic.main.movie_list_fragment.*

class MovieListFragment : Fragment() {

    private lateinit var viewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservables()
    }

    private fun setupObservables() {
        viewModel.viewState.observe(this) {
            when {
                it.movieList != null && it.movieList.isNotEmpty() -> {
                    setupRecyclerView(it.movieList)
                }
                it.errorId != null || it.errorMessage != null -> {
                    handleError(it.errorId, it.errorMessage)
                }
            }
            handleLoading(it.isLoading)
        }
        viewModel.getMovieList()
    }

    private fun handleLoading(isLoading: Boolean) {
        progressPB.visibility = if (isLoading) View.VISIBLE else View.GONE

    }

    private fun handleError(errorId: Int?, errorMessage: String?) {
        Toast.makeText(
            context,
            errorMessage ?: resources.getString(errorId ?: R.string.default_error),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun openDetailsScreen(item: Movie) {
        val bundle = bundleOf("movieItem" to item)
        view?.findNavController()?.navigate(R.id.openDetails, bundle)
    }

    private fun setupRecyclerView(list: ArrayList<Movie>) {
        moviesRV?.apply {
            if (adapter == null) {
                adapter = MoviesRecyclerAdapter(list, resources).apply {
                    detailsClickListener = object : OnMovieClickListener {
                        override fun onClick(item: Movie) {
                            openDetailsScreen(item)
                        }
                    }
                }
            } else {
                (adapter as MoviesRecyclerAdapter).swap(list)
            }
        }
    }

}