package com.example.moviesapp.screens.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.moviesapp.data.sharedprefs.settings.SharedPreferencesAppSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SettingsFragmentViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel() {
    private val sharedPreferences = SharedPreferencesAppSettings(context)

    fun setTheme(value: Boolean) {
        sharedPreferences.setTheme(value)
    }

    fun getTheme(): Boolean {
        return sharedPreferences.getTheme()
    }

    fun setGrid(int: Int) {
        sharedPreferences.setGrid(int)
    }

    fun getGrid(): Int {
        return sharedPreferences.getGrid()
    }

}