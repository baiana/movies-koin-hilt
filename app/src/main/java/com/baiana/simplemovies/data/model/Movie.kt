package com.baiana.simplemovies.data.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val stars: Int,
    val posterPath: String,
    val backdropPath: String? = null)