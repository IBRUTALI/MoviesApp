package com.example.moviesapp.data.sharedprefs.settings

interface AppSettings {

    fun setTheme(value: Boolean)

    fun getTheme(): Boolean

    fun setGrid(int: Int)

    fun getGrid(): Int

}