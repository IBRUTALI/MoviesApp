package com.example.moviesapp.data.retrofit

import com.example.moviesapp.data.retrofit.api.RetrofitInstance
import com.example.moviesapp.models.Movie
import com.example.moviesapp.models.MovieTrailer
import retrofit2.Response

class RetrofitRepository {
    suspend fun getPopularMovies(): Response<Movie> {
        return RetrofitInstance.api.getPopularMovies()
    }

    suspend fun getTop250(): Response<Movie> {
        return RetrofitInstance.api.getTop250()
    }

    suspend fun getMovieImages(movieId: String): Response<Movie> {
        return RetrofitInstance.api.getMovieImages(movieId)
    }

    suspend fun getMovieTrailer(movieId: String): Response<MovieTrailer> {
        return RetrofitInstance.api.getMovieTrailer(movieId)
   }
}