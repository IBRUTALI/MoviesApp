package com.example.moviesapp.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviesapp.models.MovieItem

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movieItem: MovieItem)

    @Delete
    suspend fun deleteMovie(movieItem: MovieItem)

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): LiveData<List<MovieItem>>
}