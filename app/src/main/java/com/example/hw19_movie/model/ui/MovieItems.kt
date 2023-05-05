package com.example.hw19_movie.model.ui

data class MovieItems(
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val overview: String,
    val id: Int = 0,
)
