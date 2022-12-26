package com.example.moviesapp.screens.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.MAIN
import com.example.moviesapp.REPO_IMPL
import com.example.moviesapp.data.sharedprefs.favorite.SharedPreferencesFavorite
import com.example.moviesapp.models.MovieItem

class FavoriteFragmentViewModel: ViewModel() {
    private val sharedPreferences = SharedPreferencesFavorite(MAIN)
    private val valueBoolean = MutableLiveData<Boolean>()

    fun setFavorite(key: String, value: Boolean) {
        sharedPreferences.setFavorite(key, value)
    }

    fun getFavorite(key: String): MutableLiveData<Boolean> {
        valueBoolean.value = sharedPreferences.getFavorite(key)
        return  valueBoolean
    }

    fun getAllMovies(): LiveData<List<MovieItem>> {
        return REPO_IMPL.allMovies
    }
}