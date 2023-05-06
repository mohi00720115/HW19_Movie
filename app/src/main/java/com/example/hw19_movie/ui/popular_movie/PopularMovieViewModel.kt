package com.example.hw19_movie.ui.popular_movie

import android.content.ContentValues
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
class PopularMovieViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun insert(movieItem: MovieItem) {
        viewModelScope.launch {
            repository.insert(movieItem)
        }
    }

    private val _popularMovieList = MutableLiveData<List<MovieItem>>()
    val popularMovieList: MutableLiveData<List<MovieItem>> = _popularMovieList

    fun getAllMovieService(page: Int) {
        viewModelScope.launch {
            try {
                popularMovieList.postValue(repository.getAllMovieService(page))
            }catch (e: Exception){
                Log.e(ContentValues.TAG, "getAllMovieService: ${e.message}")
            }
        }
    }

    fun getMovie(id: Int): LiveData<MovieEntity> {
        return repository.getMovie(id).asLiveData()
    }

}