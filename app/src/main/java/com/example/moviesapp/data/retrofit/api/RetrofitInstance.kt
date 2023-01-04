package com.example.moviesapp.data.retrofit.api

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitInstance @Inject constructor(
    val retrofit: Retrofit
)