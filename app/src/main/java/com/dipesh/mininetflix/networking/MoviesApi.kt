package com.dipesh.mininetflix.networking

import com.dipesh.mininetflix.networking.genres.GenresSchema
import com.dipesh.mininetflix.networking.movie.MoviesSchema
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface MoviesApi {

    @Headers("accept:application/json")
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
    ): MoviesSchema

    @Headers("accept:application/json")
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
    ): MoviesSchema

    @Headers("accept:application/json")
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
    ): MoviesSchema

    @Headers("accept:application/json")
    @GET("genre/movie/list")
    suspend fun getMoviesGenres(
        @Query("api_key") apiKey: String,
    ): GenresSchema

}