package com.baiana.simplemovies.presentation.movieDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.baiana.simplemovies.R
import com.baiana.simplemovies.data.model.Movie
import com.baiana.simplemovies.databinding.FragmentMovieDetailBinding
import com.baiana.simplemovies.util.loadWithGlide


class MovieDetailsFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentMovieDetailBinding.inflate(layoutInflater)
    }
    private val movie by lazy {
        this.arguments?.getParcelable<Movie>("movieItem")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backBTN.setOnClickListener { returnToMain() }
        movie?.apply { displayMovieDetails(this) } ?: returnToMain()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    private fun returnToMain() {
        findNavController().navigate(R.id.backToList)
        arguments = null
    }

    private fun displayMovieDetails(details: Movie) {
        binding.apply {
            titleTXT.text = details.title
            overviewTXT.text = details.overview
            starsTXT.text = details.stars.toString()
            backgroundIMG.loadWithGlide(details.backdropPath ?: details.posterPath)
        }
    }

}