package com.example.moviesapp.data.sharedprefs.favorite

interface AppFavorite {

    fun setFavorite(key: String, value: Boolean)

    fun getFavorite(key: String): Boolean

}