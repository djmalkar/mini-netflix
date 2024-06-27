package com.dipesh.mininetflix.screens.detail.movie

import androidx.lifecycle.ViewModel
import com.dipesh.mininetflix.movie.dao.MovieDao
import com.dipesh.mininetflix.movie.usecases.FetchSimilarMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val fetchSimilarMoviesCase: FetchSimilarMoviesUseCase
): ViewModel() {

    val similarMovies = MutableStateFlow<List<MovieDao>>(emptyList())

    suspend fun fetchInitialData(forceUpdate: Boolean = false) {
        withContext(Dispatchers.Main.immediate) {
            if (forceUpdate || similarMovies.value.isEmpty()) {
                similarMovies.value = fetchSimilarMoviesCase.fetchSimilarMovies().take(10)
            }
        }
    }

}