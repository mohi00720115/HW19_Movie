package com.example.hw19_movie.data.local.db

import androidx.room.*
import com.example.hw19_movie.data.MovieData
import com.example.hw19_movie.model.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movieEntity: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieEntity: MovieEntity)

    @Query("SELECT * FROM movie_table")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_table WHERE movie_id = :id")
    fun getMovie(id : Int): Flow<MovieEntity>

    @Update
    suspend fun update(movieEntity: MovieEntity)

    @Delete
    suspend fun delete(movieEntity: MovieEntity)

}