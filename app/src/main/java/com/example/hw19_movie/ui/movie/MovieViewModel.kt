package com.example.hw19_movie.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw19_movie.data.repository.Repository
import com.example.hw19_movie.model.entity.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private fun insertAll(movieEntity: List<MovieEntity>) {
        viewModelScope.launch { repository.insertAll(movieEntity) }
    }

    private fun insert(movieEntity: MovieEntity) {
        viewModelScope.launch {
            repository.insert(movieEntity)
        }
    }

    private fun getAllMovie(): Flow<List<MovieEntity>> {
        return repository.getAllMovie()
    }

    private fun getMovie(id: Int): Flow<MovieEntity> {
        return repository.getMovie(id)
    }

    private fun update(movieEntity: MovieEntity) {
        viewModelScope.launch {
            repository.update(movieEntity)
        }
    }

    private fun delete(movieEntity: MovieEntity) {
        viewModelScope.launch {
            repository.delete(movieEntity)
        }
    }

}