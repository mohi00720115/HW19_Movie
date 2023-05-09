package com.example.hw19_movie.ui.my_favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.hw19_movie.data.repository.Repository
import com.example.hw19_movie.model.entity.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyFavoriteViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun getMovie(id: Int): LiveData<MovieEntity> {
        return repository.getMovie(id).asLiveData()
    }

    fun delete(movieEntity: MovieEntity) {
        viewModelScope.launch {
            repository.delete(movieEntity)
        }
    }

}