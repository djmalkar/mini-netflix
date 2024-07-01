package com.dipesh.mininetflix.movie.usecases

import com.dipesh.mininetflix.common.Constants
import com.dipesh.mininetflix.movie.dao.MovieDao
import com.dipesh.mininetflix.networking.MoviesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchSimilarMoviesUseCase @Inject constructor(
    private val moviesApi: MoviesApi
) {
    private var upcomingMovies: List<MovieDao> = emptyList()

    suspend fun fetchSimilarMovies(movieId: String): List<MovieDao> {
        return withContext(Dispatchers.IO) {
            upcomingMovies = moviesApi.getSimilarMovies(movieId).results.map { moviesSchema ->
                MovieDao(
                    moviesSchema.id,
                    Constants.IMAGE_BASE_URL_W154 + moviesSchema.posterPath,
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
            upcomingMovies
        }
    }
}