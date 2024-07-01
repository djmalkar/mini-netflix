package com.dipesh.mininetflix.movie.usecases

import com.dipesh.mininetflix.common.Constants
import com.dipesh.mininetflix.movie.dao.CastDao
import com.dipesh.mininetflix.networking.MoviesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchMovieCreditsUseCase @Inject constructor(
    private val moviesApi: MoviesApi
) {
    private var casts: List<CastDao> = emptyList()

    suspend fun fetchMovieCredits(movieId: String = "653346"): List<CastDao> {
        return withContext(Dispatchers.IO) {
            casts = moviesApi.getMovieCreditsById(movieId).cast.map { castSchema ->
                CastDao(
                    Constants.IMAGE_BASE_URL_W154 + castSchema.profilePath,
                    castSchema.originalName
                )
            }
            casts
        }
    }
}