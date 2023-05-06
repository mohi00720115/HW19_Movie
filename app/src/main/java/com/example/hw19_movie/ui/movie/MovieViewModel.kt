package com.example.hw19_movie.ui.movie

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.example.hw19_movie.data.MovieItem
import com.example.hw19_movie.data.repository.Repository
import com.example.hw19_movie.model.entity.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun insertAll(movieEntity: List<MovieEntity>) {
        viewModelScope.launch { repository.insertAll(movieEntity) }
    }

    fun insert(movieItem: MovieItem) {
        viewModelScope.launch {
            repository.insert(movieItem)
        }
    }

    fun getAllMovie(): LiveData<List<MovieItem>> {
        return repository.getAllMovie().asLiveData()
    }

    fun getMovie(id: Int): LiveData<MovieEntity> {
        return repository.getMovie(id).asLiveData()
    }

    fun update(movieEntity: MovieEntity) {
        viewModelScope.launch {
            repository.update(movieEntity)
        }
    }

    fun delete(movieEntity: MovieEntity) {
        viewModelScope.launch {
            repository.delete(movieEntity)
        }
    }

    private val _movieList = MutableLiveData<List<MovieItem>>()
    val movieList: MutableLiveData<List<MovieItem>> = _movieList

    fun getAllMovieService(page: Int) {
        viewModelScope.launch {
            try {
                movieList.postValue(repository.getAllMovieService(page))
            } catch (e: Exception) {
                Log.e(TAG, "getAllMovieService: ${e.message}")
            }
        }
    }

}