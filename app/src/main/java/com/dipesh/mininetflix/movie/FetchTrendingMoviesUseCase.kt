package com.dipesh.mininetflix.movie

import com.dipesh.mininetflix.BuildConfig
import com.dipesh.mininetflix.common.Constants
import com.dipesh.mininetflix.movie.dao.MovieDao
import com.dipesh.mininetflix.networking.MoviesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchTrendingMoviesUseCase @Inject constructor(
    private val moviesApi: MoviesApi
) {
    private var movies: List<MovieDao> = emptyList()

    suspend fun fetchTrendingMovies(): List<MovieDao> {
        return withContext(Dispatchers.IO) {
            movies = moviesApi.getTopRatedMovies(BuildConfig.API_KEY).results.map { moviesSchema ->
                MovieDao(
                    Constants.IMAGE_BASE_URL_W500 + moviesSchema.posterPath,
                    moviesSchema.genreIds,
                    moviesSchema.originalLanguage,
                    moviesSchema.title,
                    moviesSchema.overview,
                    moviesSchema.popularity,
                    moviesSchema.releaseDate,
                    moviesSchema.video,
                    moviesSchema.avgVote,
                    moviesSchema.voteCount
                )
            }
            movies
        }
    }
}