package com.baiana.simplemovies.presentation.movieList

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baiana.simplemovies.data.model.Movie
import com.baiana.simplemovies.databinding.MovieListItemBinding
import com.baiana.simplemovies.util.loadWithGlide

class MoviesRecyclerAdapter(private var list: ArrayList<Movie>, val resources: Resources) :
    RecyclerView.Adapter<MoviesRecyclerAdapter.ItemHolder>() {

    var detailsClickListener: OnMovieClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding =
            MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(binding, resources)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(list[position], detailsClickListener)
    }

    override fun getItemCount() = list.count()


    class ItemHolder(private val binding: MovieListItemBinding, private val resources: Resources) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie, clickListener: OnMovieClickListener?) {
            with(binding) {
                titleTXT.text = item.title
                overviewTXT.text = item.overview
                starsTXT.text = item.stars.toString()

                posterIMG.loadWithGlide(item.posterPath)

                containerCL.setOnClickListener{
                    clickListener?.onClick(item)
                }
            }
        }
    }

    fun swap(movieList: ArrayList<Movie>) {
        this.list = movieList
        notifyDataSetChanged()
    }

    fun addToList(movieList: ArrayList<Movie>) {
        this.list.addAll(movieList)
        notifyDataSetChanged()
    }

    interface OnMovieClickListener {
        fun onClick(item: Movie)
    }
}
