package com.example.moviesapp.data.retrofit

import com.example.moviesapp.data.retrofit.api.ApiService
import com.example.moviesapp.data.retrofit.api.RetrofitInstance
import com.example.moviesapp.models.Movie
import com.example.moviesapp.models.MovieSource
import com.example.moviesapp.models.MovieTrailer
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitRepository @Inject constructor(
    retrofitInstance: RetrofitInstance
): MovieSource {
    val retrofit: Retrofit = retrofitInstance.retrofit
    private val api = retrofit.create(ApiService::class.java)

    override suspend fun getPopularMovies(): Response<Movie> {
        return api.getPopularMovies()
    }

    override suspend fun getTop250(): Response<Movie> {
        return api.getTop250()
    }

    override suspend fun getMovieImages(movieId: String): Response<Movie> {
        return api.getMovieImages(movieId)
    }

    override suspend fun getMovieTrailer(movieId: String): Response<MovieTrailer> {
        return api.getMovieTrailer(movieId)
   }
}