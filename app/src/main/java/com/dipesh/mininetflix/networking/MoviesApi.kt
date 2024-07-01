package com.dipesh.mininetflix.networking

import com.dipesh.mininetflix.BuildConfig
import com.dipesh.mininetflix.networking.genres.GenresSchema
import com.dipesh.mininetflix.networking.movie.MovieCreditSchema
import com.dipesh.mininetflix.networking.movie.MoviesSchema
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface MoviesApi {

    @Headers("accept:application/json")
    @GET("movie/top_rated?api_key=${BuildConfig.API_KEY}")
    suspend fun getTopRatedMovies(): MoviesSchema

    @Headers("accept:application/json")
    @GET("movie/now_playing?api_key=${BuildConfig.API_KEY}")
    suspend fun getNowPlayingMovies(): MoviesSchema

    @Headers("accept:application/json")
    @GET("movie/upcoming?api_key=${BuildConfig.API_KEY}")
    suspend fun getUpcomingMovies(): MoviesSchema

    @Headers("accept:application/json")
    @GET("movie/{movie_id}/similar?api_key=${BuildConfig.API_KEY}")
    suspend fun getSimilarMovies(@Path("movie_id") movieId: String): MoviesSchema

    @Headers("accept:application/json")
    @GET("movie/{movie_id}/recommendations?api_key=${BuildConfig.API_KEY}")
    suspend fun getRecommendedMoviesByMovieId(@Path("movie_id") movieId: String): MoviesSchema

    @Headers("accept:application/json")
    @GET("movie/{movie_id}/credits?api_key=${BuildConfig.API_KEY}")
    suspend fun getMovieCreditsById(@Path("movie_id") movieId: String): MovieCreditSchema

    @Headers("accept:application/json")
    @GET("genre/movie/list?api_key=${BuildConfig.API_KEY}")
    suspend fun getMoviesGenres(): GenresSchema

}