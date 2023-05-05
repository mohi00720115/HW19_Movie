package com.example.hw19_movie.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hw19_movie.model.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieDao(): IMovieDao
}