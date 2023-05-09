package com.example.hw19_movie.model.ui

data class MovieItem(
    val title: String?,
    val releaseDate: String?,
    val posterPath: String?,
    var flag: Boolean = false,
    val id: Int?,
)
