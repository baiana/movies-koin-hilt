package com.baiana.simplemovies.presentation.movieDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import com.baiana.simplemovies.data.model.Movie
import com.baiana.simplemovies.databinding.FragmentMovieDetailBinding
import com.baiana.simplemovies.util.loadWithGlide


class MovieDetailsFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentMovieDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    private fun displayMovieDetails(details: Movie) {
        binding.apply {
            titleTXT.text = details.title
            overviewTXT.text = details.overview
            starsTXT.text = details.stars.toString()
            backgroundIMG.loadWithGlide(details.backdropPath ?: details.posterPath)
        }
    }

}