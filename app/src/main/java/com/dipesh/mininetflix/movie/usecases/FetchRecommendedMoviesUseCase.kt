package com.dipesh.mininetflix.movie.usecases

import com.dipesh.mininetflix.common.Constants
import com.dipesh.mininetflix.movie.dao.MovieDao
import com.dipesh.mininetflix.networking.MoviesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchRecommendedMoviesUseCase @Inject constructor(
    private val moviesApi: MoviesApi
) {
    private var recommendedMovies: List<MovieDao> = emptyList()

    suspend fun fetchRecommendedMovies(movieId: String = "653346"): List<MovieDao> {
        return withContext(Dispatchers.IO) {
            recommendedMovies = moviesApi.getRecommendedMoviesByMovieId(movieId).results.map { moviesSchema ->
                MovieDao(
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
            recommendedMovies
        }
    }
}