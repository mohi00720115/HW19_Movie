package com.example.hw19_movie.util

import com.example.hw19_movie.data.MovieItem
import com.example.hw19_movie.data.Result
import com.example.hw19_movie.model.entity.MovieEntity

/**
 * Convert MovieEntity to MovieItem
 */
fun listEntityToListMovieItem(movieListResponse: List<MovieEntity>): List<MovieItem> {
    return movieListResponse.map { movieData ->
        movieData.run {
            MovieItem(title, releaseDate, posterPath, false, id)
        }
    }
}

/**
 * Convert MovieItem to MovieEntity
 */
fun movieItemToMovieEntity(movieItem: MovieItem): MovieEntity {
    return movieItem.run {
        MovieEntity(title, releaseDate, posterPath, id)
    }
}

/**
 * Convert Result to MovieItem
 */
fun listResultToMovieItemList(resultList: List<Result>?): List<MovieItem>? {
    return resultList?.map { movieList ->
        movieList.run {
            MovieItem(title, releaseDate, posterPath, false, id)
        }
    }

}
