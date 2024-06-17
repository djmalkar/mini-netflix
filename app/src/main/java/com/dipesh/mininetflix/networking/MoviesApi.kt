package com.dipesh.mininetflix.networking

import com.dipesh.mininetflix.networking.movie.MoviesSchema
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
    ): MoviesSchema

}