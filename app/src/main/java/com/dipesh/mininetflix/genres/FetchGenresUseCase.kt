package com.dipesh.mininetflix.genres

import com.dipesh.mininetflix.BuildConfig
import com.dipesh.mininetflix.networking.MoviesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchGenresUseCase @Inject constructor(
    private val moviesApi: MoviesApi
) {
    private var genres: List<GenreDao> = emptyList()

    suspend fun fetchGenres(): List<GenreDao> {
        return withContext(Dispatchers.IO) {
            genres = moviesApi.getMoviesGenres(BuildConfig.API_KEY).genres.map { genreSchema ->
                GenreDao(
                    genreSchema.id,
                    genreSchema.name
                )
            }
            genres
        }
    }
}