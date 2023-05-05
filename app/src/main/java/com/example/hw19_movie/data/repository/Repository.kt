package com.example.hw19_movie.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.hw19_movie.data.MovieItem
import com.example.hw19_movie.data.local.db.IMovieDao
import com.example.hw19_movie.data.network.IMovieNetwork
import com.example.hw19_movie.model.entity.MovieEntity
import com.example.hw19_movie.util.listEntityToListMovieData
import com.example.hw19_movie.util.listResultToMovieItemList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository(
    private val movieNetwork: IMovieNetwork,
    private val iMovieDao: IMovieDao
) {

    suspend fun insertAll(movieEntity: List<MovieEntity>) {
        iMovieDao.insertAll(movieEntity)
    }

    suspend fun insert(movieEntity: MovieEntity) {
        iMovieDao.insert(movieEntity)
    }

    fun getAllMovie(): Flow<List<MovieItem>> {
        return flow {
            iMovieDao.getAllMovie().collect {
                listEntityToListMovieData(it)
                Log.e(TAG, "getAllMovie: listEntityToListMovieData $it")
            }
        }
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

    suspend fun getAllMovieService(page: Int): List<MovieItem>? {
//        try {
        return listResultToMovieItemList(movieNetwork.getAllMovieService(page).results)
//        } catch (e: Exception) {
//            Log.e(TAG, "getAllMovieService: ERROR :( ${e.message}")
//        }
    }


}
