package com.example.moviesapp

import com.example.moviesapp.data.sharedprefs.settings.AppSettings
import com.example.moviesapp.data.sharedprefs.settings.SharedPreferencesAppSettings

object Singletons {

    private var column: Int? = 2

    private val appSettings: AppSettings by lazy {
        SharedPreferencesAppSettings(MAIN)
    }

    fun setGrid(int: Int) {
        appSettings.setGrid(int)
        column = int
    }

    fun getGrid(): Int {
        return appSettings.getGrid()
    }
}
