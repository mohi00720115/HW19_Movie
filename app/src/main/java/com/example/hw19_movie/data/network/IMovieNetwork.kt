package com.example.hw19_movie.data.network

import com.example.hw19_movie.model.dto.MovieData
import retrofit2.http.GET
import retrofit2.http.Query

interface IMovieNetwork {
    @GET("movie/popular")
    suspend fun getAllMovieService(@Query("page") page: Int = 1): MovieData
}
