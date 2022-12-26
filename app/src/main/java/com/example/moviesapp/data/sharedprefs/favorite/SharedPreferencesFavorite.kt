package com.example.moviesapp.data.sharedprefs.favorite

import android.content.Context
import android.preference.PreferenceManager


class SharedPreferencesFavorite(context: Context): AppFavorite {

    private val favoriteShared = context.getSharedPreferences("favorite", Context.MODE_PRIVATE)

    override fun setFavorite(key: String, value: Boolean) {
        favoriteShared.edit().putBoolean(key, value).apply()
    }

    override fun getFavorite(key: String): Boolean {
        return favoriteShared.getBoolean(key, false)
    }

    companion object {
        fun getFavorite(context: Context?, key: String): Boolean {
            val getFavoriteShared = context?.getSharedPreferences("favorite", Context.MODE_PRIVATE)!!
            return getFavoriteShared.getBoolean(key, false)
        }
    }
}