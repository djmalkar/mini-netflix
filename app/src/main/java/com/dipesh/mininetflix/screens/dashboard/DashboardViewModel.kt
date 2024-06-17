package com.dipesh.mininetflix.screens.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import com.dipesh.mininetflix.movie.FetchTrendingMoviesUseCase
import com.dipesh.mininetflix.movie.dao.MovieDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val fetchTrendingMoviesUseCase: FetchTrendingMoviesUseCase
): ViewModel() {

    val lastActiveMovies = MutableStateFlow<List<MovieDao>>(emptyList())

    suspend fun fetchLastActiveMovies(forceUpdate: Boolean = false) {
        withContext(Dispatchers.Main.immediate) {
            if (forceUpdate || lastActiveMovies.value.isEmpty()) {
                lastActiveMovies.value = fetchTrendingMoviesUseCase.fetchTrendingMovies()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("DashboardViewModel", "onCleared()")
    }

}