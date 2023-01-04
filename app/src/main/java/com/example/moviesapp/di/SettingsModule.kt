package com.example.moviesapp.di

import com.example.moviesapp.data.retrofit.RetrofitRepository
import com.example.moviesapp.data.retrofit.api.ApiService
import com.example.moviesapp.data.sharedprefs.settings.AppSettings
import com.example.moviesapp.data.sharedprefs.settings.SharedPreferencesAppSettings
import com.example.moviesapp.models.MovieSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsModule {

    @Binds
    abstract fun bindAppSettings(
        appSettings: SharedPreferencesAppSettings
    ): AppSettings

    @Binds
    abstract fun bindRetrofit(
        retrofitRepository: RetrofitRepository
    ): MovieSource

}