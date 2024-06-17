package com.dipesh.mininetflix.screens.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import com.dipesh.mininetflix.genres.FetchGenresUseCase
import com.dipesh.mininetflix.genres.GenreDao
import com.dipesh.mininetflix.movie.usecases.FetchTrendingMoviesUseCase
import com.dipesh.mininetflix.movie.dao.MovieDao
import com.dipesh.mininetflix.movie.usecases.FetchNowPlayingMoviesUseCase
import com.dipesh.mininetflix.movie.usecases.FetchUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val fetchTrendingMoviesUseCase: FetchTrendingMoviesUseCase,
    private val fetchNowPlayingMoviesUseCase: FetchNowPlayingMoviesUseCase,
    private val fetchUpcomingMoviesUseCase: FetchUpcomingMoviesUseCase,
    private val fetchGenresUseCase: FetchGenresUseCase
): ViewModel() {

    val latestTrendingMovies = MutableStateFlow<List<MovieDao>>(emptyList())
    val latestNowPlayingMovies = MutableStateFlow<List<MovieDao>>(emptyList())
    val latestUpcomingMovies = MutableStateFlow<List<MovieDao>>(emptyList())
    val latestGenres = MutableStateFlow<List<GenreDao>>(emptyList())

    suspend fun fetchLastActiveMovies(forceUpdate: Boolean = false) {
        withContext(Dispatchers.Main.immediate) {
            if (forceUpdate || latestTrendingMovies.value.isEmpty()) {
                latestTrendingMovies.value = fetchTrendingMoviesUseCase.fetchTrendingMovies()
            }
        }
    }

    suspend fun fetchGenres(forceUpdate: Boolean = false) {
        withContext(Dispatchers.Main.immediate) {
            if (forceUpdate || latestGenres.value.isEmpty()) {
                latestGenres.value = fetchGenresUseCase.fetchGenres()
            }
        }
    }

    suspend fun fetchNowPlaying(forceUpdate: Boolean = false) {
        withContext(Dispatchers.Main.immediate) {
            if (forceUpdate || latestNowPlayingMovies.value.isEmpty()) {
                latestNowPlayingMovies.value = fetchNowPlayingMoviesUseCase.fetchNowPlayingMovies()
            }
        }
    }

    suspend fun fetchUpcomingMovies(forceUpdate: Boolean = false) {
        withContext(Dispatchers.Main.immediate) {
            if (forceUpdate || latestUpcomingMovies.value.isEmpty()) {
                latestUpcomingMovies.value = fetchUpcomingMoviesUseCase.fetchUpcomingMovies()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("DashboardViewModel", "onCleared()")
    }

}