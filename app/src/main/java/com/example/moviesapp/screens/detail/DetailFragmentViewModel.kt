package com.example.moviesapp.screens.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.REPO_IMPL
import com.example.moviesapp.data.retrofit.RetrofitRepository
import com.example.moviesapp.data.room.repository.MoviesRepositoryImpl
import com.example.moviesapp.models.MovieItem
import com.example.moviesapp.models.MovieTrailer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailFragmentViewModel : ViewModel() {
    private var repository = RetrofitRepository()
    val movieTrailer: MutableLiveData<Response<MovieTrailer>> = MutableLiveData()

    fun getMovieTrailer(movieId: String) {
        viewModelScope.launch {
            movieTrailer.value = repository.getMovieTrailer(movieId)
        }
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