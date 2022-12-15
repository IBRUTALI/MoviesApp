package com.example.moviesapp.data.retrofit.api

import com.example.moviesapp.models.Movie
import com.example.moviesapp.models.MovieTrailer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("ru/API/MostPopularMovies/k_un861t3l")
    suspend fun getPopularMovies(): Response<Movie>

    @GET("ru/API/Top250Movies/k_un861t3l")
    suspend fun getTop250(): Response<Movie>

    @GET("ru/API/Images/k_un861t3l/movieId/Short")
    suspend fun getMovieImages(movieId: String): Response<Movie>

    @GET("ru/API/Trailer/k_un861t3l/{movieId}")
    suspend fun getMovieTrailer(@Path("movieId") movieId: String): Response<MovieTrailer>
}