package com.example.moviesapp.data.room.repository

import androidx.lifecycle.LiveData
import com.example.moviesapp.models.MovieItem

interface MoviesRepository {
val allMovies: LiveData<List<MovieItem>>

    suspend fun insertMovie(movieItem: MovieItem, onSuccess:() -> Unit)

    suspend fun deleteMovie(movieItem: MovieItem, onSuccess:() -> Unit)

}