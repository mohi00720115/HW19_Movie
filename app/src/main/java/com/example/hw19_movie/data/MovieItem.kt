package com.example.hw19_movie.data

data class MovieItem(
    val title: String?,
    val releaseDate: String?,
    val posterPath: String?,
    var flag: Boolean = false,
    val id: Int?,
)
