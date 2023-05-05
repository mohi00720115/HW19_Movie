package com.example.hw19_movie.data.network

import com.example.hw19_movie.data.MovieData
import retrofit2.http.GET
import retrofit2.http.Query

interface IMovieNetwork {
    @GET("movie/popular")
    fun getAllMovie(@Query("page") page: Int): MovieData
}
