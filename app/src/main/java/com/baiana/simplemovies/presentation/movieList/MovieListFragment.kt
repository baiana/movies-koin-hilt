package com.baiana.simplemovies.presentation.movieList

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.baiana.simplemovies.R
import com.baiana.simplemovies.data.model.Movie
import com.baiana.simplemovies.presentation.MovieListViewModel
import kotlinx.android.synthetic.main.movie_list_fragment.*

class MovieListFragment : Fragment() {

    private lateinit var viewModel: MovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun setupRecyclerView(list: ArrayList<Movie>) {
        moviesRV?.apply {
            if (adapter == null) {
                adapter = MoviesRecyclerAdapter(list, resources)
            } else {
                (adapter as MoviesRecyclerAdapter).swap(list)
            }
        }
    }

}