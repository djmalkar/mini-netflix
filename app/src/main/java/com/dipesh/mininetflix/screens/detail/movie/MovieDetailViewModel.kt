package com.dipesh.mininetflix.screens.detail.movie

import androidx.lifecycle.ViewModel
import com.dipesh.mininetflix.movie.dao.CastDao
import com.dipesh.mininetflix.movie.dao.MovieDao
import com.dipesh.mininetflix.movie.usecases.FetchMovieCreditsUseCase
import com.dipesh.mininetflix.movie.usecases.FetchRecommendedMoviesUseCase
import com.dipesh.mininetflix.movie.usecases.FetchSimilarMoviesUseCase
import com.dipesh.mininetflix.movie.usecases.FetchTrendingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val fetchSimilarMoviesCase: FetchSimilarMoviesUseCase,
    private val fetchRecommendedMoviesUseCase: FetchRecommendedMoviesUseCase,
    private val fetchTrendingMoviesUseCase: FetchTrendingMoviesUseCase,
    private val fetchMovieCreditsUseCase: FetchMovieCreditsUseCase
): ViewModel() {

    private val _similarMovies = MutableStateFlow<List<MovieDao>>(emptyList())
    val similarMovies : StateFlow<List<MovieDao>> = _similarMovies

    private val _recommendedMovies = MutableStateFlow<List<MovieDao>>(emptyList())
    val recommendedMovies : StateFlow<List<MovieDao>> = _recommendedMovies

    private val _trendingMovies = MutableStateFlow<List<MovieDao>>(emptyList())
    val trendingMovies : StateFlow<List<MovieDao>> = _trendingMovies

    private val _castInfo = MutableStateFlow<List<CastDao>>(emptyList())
    val castInfo : StateFlow<List<CastDao>> = _castInfo

    suspend fun fetchMoreMoviesData(forceUpdate: Boolean = false) {
        withContext(Dispatchers.Main.immediate) {
            if (forceUpdate || similarMovies.value.isEmpty()) {
                _similarMovies.value = fetchSimilarMoviesCase.fetchSimilarMovies().take(10)
                _recommendedMovies.value = fetchRecommendedMoviesUseCase.fetchRecommendedMovies().take(10)
                _trendingMovies.value = fetchTrendingMoviesUseCase.fetchTrendingMovies().take(10)
            }
        }
    }

    suspend fun fetchCastListData(forceUpdate: Boolean = false) {
        withContext(Dispatchers.Main.immediate) {
            if (forceUpdate || castInfo.value.isEmpty()) {
                _castInfo.value = fetchMovieCreditsUseCase.fetchMovieCredits().take(10)
            }
        }
    }

}