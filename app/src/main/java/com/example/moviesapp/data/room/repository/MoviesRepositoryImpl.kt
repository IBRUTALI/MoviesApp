package com.example.moviesapp.data.room.repository

import androidx.lifecycle.LiveData
import com.example.moviesapp.data.room.dao.MoviesDao
import com.example.moviesapp.models.MovieItem

class MoviesRepositoryImpl(private val dao: MoviesDao): MoviesRepository {
    override val allMovies: LiveData<List<MovieItem>>
        get() = dao.getAllMovies()

    override suspend fun insertMovie(movieItem: MovieItem, onSuccess: () -> Unit) {
        dao.insertMovie(movieItem)
        onSuccess
    }

    override suspend fun deleteMovie(movieItem: MovieItem, onSuccess: () -> Unit) {
        dao.deleteMovie(movieItem)
        onSuccess
    }

}