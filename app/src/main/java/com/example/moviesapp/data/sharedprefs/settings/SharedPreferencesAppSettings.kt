package com.example.moviesapp.data.sharedprefs.settings

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesAppSettings @Inject constructor(
    @ApplicationContext appContext: Context
) : AppSettings {

    private val sharedPreferences = appContext.getSharedPreferences("settings", Context.MODE_PRIVATE)

    override fun setTheme(value: Boolean) {
        sharedPreferences.edit().putBoolean("theme", value).apply()
    }

    override fun getTheme(): Boolean {
        return sharedPreferences.getBoolean("theme", false)
    }

    override fun setGrid(int: Int) {
        sharedPreferences.edit().putInt("grid", int).apply()
    }

    override fun getGrid(): Int {
        return sharedPreferences.getInt("grid", 2)
    }
}