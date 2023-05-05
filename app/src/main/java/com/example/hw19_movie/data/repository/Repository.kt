package com.example.hw19_movie.data.repository

import com.example.hw19_movie.data.local.db.IMovieDao
import com.example.hw19_movie.model.entity.MovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository (
//    private val movieNetwork: IMovieNetwork,
    private val iMovieDao: IMovieDao
) {

    suspend fun insertAll(movieEntity: List<MovieEntity>) {
        iMovieDao.insertAll(movieEntity)
    }

    suspend fun insert(movieEntity: MovieEntity) {
        iMovieDao.insert(movieEntity)
    }

    fun getAllMovie(): Flow<List<MovieEntity>> {
        return iMovieDao.getAllMovie()
    }

    fun getMovie(id: Int): Flow<MovieEntity> {
        return iMovieDao.getMovie(id)
    }

    suspend fun update(movieEntity: MovieEntity) {
        iMovieDao.update(movieEntity)
    }

    suspend fun delete(movieEntity: MovieEntity) {
        iMovieDao.delete(movieEntity)
    }


}
