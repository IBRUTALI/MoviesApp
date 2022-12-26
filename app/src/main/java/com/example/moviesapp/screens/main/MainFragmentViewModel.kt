package com.example.moviesapp.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.MAIN
import com.example.moviesapp.REPO_IMPL
import com.example.moviesapp.data.retrofit.RetrofitRepository
import com.example.moviesapp.data.room.MoviesRoomDatabase
import com.example.moviesapp.data.room.repository.MoviesRepositoryImpl
import com.example.moviesapp.data.sharedprefs.favorite.SharedPreferencesFavorite
import retrofit2.Response
import com.example.moviesapp.models.Movie
import com.example.moviesapp.models.MovieItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainFragmentViewModel(application: Application): AndroidViewModel(application) {
    private var repository = RetrofitRepository()
    val myMovie: MutableLiveData<Response<Movie>> = MutableLiveData()
    private val context = application
    private val sharedPreferences = SharedPreferencesFavorite(context)

    fun getPopularMovies() {
        viewModelScope.launch {
            myMovie.value = repository.getPopularMovies()
        }
    }

    fun initDatabase() {
        val daoMovie = MoviesRoomDatabase.getInstance(context).getMovieDao()
        REPO_IMPL = MoviesRepositoryImpl(daoMovie)
    }

    fun insertMovie(movieItem: MovieItem, onSuccess:() -> Unit) {
        viewModelScope.launch (Dispatchers.IO) {
            REPO_IMPL.insertMovie(movieItem) {
                onSuccess()
            }
        }
    }

    fun deleteMovie(movieItem: MovieItem, onSuccess:() -> Unit) {
        viewModelScope.launch (Dispatchers.IO) {
            REPO_IMPL.deleteMovie(movieItem) {
                onSuccess()
            }
        }
    }
}