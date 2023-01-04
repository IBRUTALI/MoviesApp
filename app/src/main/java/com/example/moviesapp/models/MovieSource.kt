package com.example.moviesapp.models

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieSource {

    suspend fun getPopularMovies(): Response<Movie>

    suspend fun getTop250(): Response<Movie>

    suspend fun getMovieImages(movieId: String): Response<Movie>

    suspend fun getMovieTrailer(movieId: String): Response<MovieTrailer>

}