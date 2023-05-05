package com.example.hw19_movie.util

import com.example.hw19_movie.data.MovieItem
import com.example.hw19_movie.data.Result
import com.example.hw19_movie.model.entity.MovieEntity

fun listEntityToListMovieData(movieListResponse: List<MovieEntity>): List<MovieItem> {
    return movieListResponse.map { movieData ->
        movieData.run {
            MovieItem(title, releaseDate, posterPath, id)
        }
    }
}

fun listResultToMovieItemList(resultList: List<Result>?): List<MovieItem>? {
    return resultList?.map { movieList ->
        movieList.run {
            MovieItem(title, releaseDate, posterPath, id)
        }
    }
}
