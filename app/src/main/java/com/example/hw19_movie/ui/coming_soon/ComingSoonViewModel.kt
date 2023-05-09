package com.example.hw19_movie.ui.coming_soon

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.*
import com.example.hw19_movie.model.ui.MovieItem
import com.example.hw19_movie.data.repository.Repository
import com.example.hw19_movie.model.entity.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ComingSoonViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun getMovie(id: Int): LiveData<MovieEntity> {
        return repository.getMovie(id).asLiveData()
    }

    private val _comingSoonMovieList = MutableLiveData<List<MovieItem>>()
    val comingSoonMovieList: MutableLiveData<List<MovieItem>> = _comingSoonMovieList

    fun getAllMovieService(page: Int) {
        viewModelScope.launch {
            try {
                comingSoonMovieList.postValue(repository.getAllMovieService(page))
            }catch (e: Exception){
                Log.e(ContentValues.TAG, "getAllMovieService: ${e.message}")
            }
        }
    }

}