package com.dipesh.mininetflix.screens.detail.movie

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dipesh.mininetflix.R


@Composable
fun MoreTab(
    movieId: String,
    viewModel: MovieDetailViewModel
) {

    val similarMovies = viewModel.similarMovies.collectAsState()
    val recommendedMovies = viewModel.recommendedMovies.collectAsState()
    val topRatedMovies = viewModel.trendingMovies.collectAsState()

    LaunchedEffect(Unit) {
        Log.d("MoreTab", "Fetching Similar Movies")
        viewModel.fetchMoreMoviesData(movieId)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Users Also Watched",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Users also Watch
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            recommendedMovies.value.forEach {
                Column(modifier = Modifier.width(IntrinsicSize.Max)) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(it.posterPath)
                            .build(),
                        placeholder = painterResource(R.drawable.trending_placeholder),
                        modifier = Modifier
                            .width(140.dp)
                            .clickable { },
                        contentScale = ContentScale.FillWidth,
                        contentDescription = "Recommended",
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Similar Titles You Might Enjoy",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Similar Titles
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            similarMovies.value.forEach {
                Column(modifier = Modifier.width(IntrinsicSize.Max)) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(it.posterPath)
                            .build(),
                        placeholder = painterResource(R.drawable.trending_placeholder),
                        modifier = Modifier
                            .width(140.dp)
                            .clickable { },
                        contentScale = ContentScale.FillWidth,
                        contentDescription = "Similar Movies",
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Box Office Hits",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Top-Rated Titles
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            topRatedMovies.value.forEach {
                Column(modifier = Modifier.width(IntrinsicSize.Max)) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(it.posterPath)
                            .build(),
                        placeholder = painterResource(R.drawable.trending_placeholder),
                        modifier = Modifier
                            .width(140.dp)
                            .clickable { },
                        contentScale = ContentScale.FillWidth,
                        contentDescription = "Top rated",
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }

}