package com.example.hw19_movie.ui.movie

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.example.hw19_movie.data.MovieItem
import com.example.hw19_movie.data.repository.Repository
import com.example.hw19_movie.model.entity.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun insertAll(movieEntity: List<MovieEntity>) {
        viewModelScope.launch { repository.insertAll(movieEntity) }
    }

    fun insert(movieEntity: MovieEntity) {
        viewModelScope.launch {
            repository.insert(movieEntity)
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

    private val _popularMovieList = MutableLiveData<List<MovieItem>>()
    val popularMovieList: MutableLiveData<List<MovieItem>> = _popularMovieList

    fun getAllMovieService(page: Int) {
        viewModelScope.launch {
            try {
                popularMovieList.postValue(repository.getAllMovieService(page))
            }catch (e:Exception){
                Log.e(TAG, "getAllMovieService: ${e.message}")
            }
        }
    }

}