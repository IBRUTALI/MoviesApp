package com.example.moviesapp.screens.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.REPO_IMPL
import com.example.moviesapp.data.room.repository.MoviesRepositoryImpl
import com.example.moviesapp.models.MovieItem

class FavoriteFragmentViewModel: ViewModel() {
    fun getAllMovies(): LiveData<List<MovieItem>> {
        return REPO_IMPL.allMovies
    }
}