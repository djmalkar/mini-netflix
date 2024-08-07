package com.dipesh.mininetflix.screens.dashboard

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dipesh.mininetflix.R
import com.dipesh.mininetflix.genres.GenreDao
import com.dipesh.mininetflix.movie.dao.MovieDao

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onMovieDetailClicked: (String) -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {

    val trendingMovies = viewModel.latestTrendingMovies.collectAsState()
    val nowPlayingMovies = viewModel.latestNowPlayingMovies.collectAsState()
    val upcomingMovies = viewModel.latestUpcomingMovies.collectAsState()
    val genres = viewModel.latestGenres.collectAsState()

    LaunchedEffect(Unit) {
        Log.d("DashboardScreen", "Fetching Initial Dashboard Data")
        viewModel.fetchInitialDashboardData()
    }

    val state = rememberPullToRefreshState()

    if (state.isRefreshing) {
        LaunchedEffect(Unit) {
            viewModel.fetchInitialDashboardData(forceUpdate = true)
            state.endRefresh()
        }
    }

    Box(
        modifier = Modifier
            .nestedScroll(state.nestedScrollConnection)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            TrendingCarousel(trendingMovies)
            CategoriesChip(genres)
            ContinueWatchingCarousel(nowPlayingMovies, onMovieDetailClicked)
            NewReleaseCarousel(upcomingMovies)
            RecommendedCarousel()
        }

        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = state,
        )
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun TrendingCarousel(trendingMovies: State<List<MovieDao>>) {
    Log.d("DashboardScreen", "TrendingCarousel")
    val pagerState = rememberPagerState(pageCount = {
        trendingMovies.value.size
    })

    val blackBottomGradient = Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color.Black),
        startY = 600f
    )

    // Trending Movies Poster
    Box {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.padding(
                bottom = 24.dp
            )
        ) {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(trendingMovies.value[it].posterPath)
                        .build(),
                    placeholder = painterResource(R.drawable.trending_placeholder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.667f),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = "Dashboard Main Images"
                )
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(blackBottomGradient)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play",
                    )
                    Text(
                        text = "Watch Now",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Black,
                    )
                }
            }
        }

        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.LightGray else Color.DarkGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun CategoriesChip(genres: State<List<GenreDao>>) {
    Log.d("DashboardScreen", "CategoriesChip")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        genres.value.forEach {
            SuggestionChip(
                onClick = { },
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(50),
                label = {
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.labelMedium
                    )
                },
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
private fun ContinueWatchingCarousel(
    nowPlayingMovies: State<List<MovieDao>>,
    onMovieDetailClicked: (String) -> Unit
) {
    Log.d("DashboardScreen", "ContinueWatchingCarousel")
    Text(
        text = "Continue Watching",
        style = MaterialTheme.typography.labelLarge,
        fontSize = 20.sp,
        modifier = Modifier.padding(
            top = 16.dp,
            bottom = 8.dp,
            start = 8.dp
        )
    )

    // Continue Watching Section
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        nowPlayingMovies.value.forEach {
            Column(modifier = Modifier.width(IntrinsicSize.Max)) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(it.posterPath)
                        .build(),
                    placeholder = painterResource(R.drawable.trending_placeholder),
                    modifier = Modifier
                        .width(160.dp)
                        .clickable { onMovieDetailClicked(it.id.toString()) },
                    contentDescription = "Now Playing Images",
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.DarkGray)
                ) {
                    Text(
                        text = "S4:E2 . 9m left",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(8.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        tint = Color.White,
                        contentDescription = "Play",
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))
        }
    }

    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun NewReleaseCarousel(upcomingMovies: State<List<MovieDao>>) {
    Log.d("DashboardScreen", "NewReleaseCarousel")
    Row(
        modifier = Modifier
            .padding(start = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "NEW",
            style = MaterialTheme.typography.labelSmall,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .background(Color.Yellow)
                .padding(4.dp),
            letterSpacing = 0.15.sp
        )
        Text(
            text = "Releases",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(start = 8.dp),
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "See all",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(end = 8.dp),
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_right_arrow),
            contentDescription = "Play",
            modifier = Modifier.padding(end = 16.dp),
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    // New or Latest Releases
    Row(
        modifier = Modifier
            .padding(start = 8.dp)
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        upcomingMovies.value.forEach {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(it.posterPath)
                    .build(),
                placeholder = painterResource(R.drawable.trending_placeholder),
                modifier = Modifier
                    .width(100.dp),
                contentScale = ContentScale.FillWidth,
                contentDescription = "Upcoming Movies"
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }

    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun RecommendedCarousel() {
    Log.d("DashboardScreen", "RecommendedCarousel")
    Row(
        modifier = Modifier
            .padding(start = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Recommended For You",
            style = MaterialTheme.typography.labelLarge,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "See all",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(end = 8.dp),
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_right_arrow),
            contentDescription = "Play",
            modifier = Modifier.padding(end = 16.dp),
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    // Recommendations
    Row(
        modifier = Modifier
            .padding(start = 8.dp)
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        val images = listOf(
            R.drawable.protrait_4,
            R.drawable.protrait_5,
            R.drawable.protrait_1,
            R.drawable.protrait_3,
            R.drawable.protrait_4,
        )
        images.forEach {
            Image(
                painter = painterResource(id = it),
                modifier = Modifier.width(160.dp),
                contentDescription = "Recommended Images"
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }

    Spacer(modifier = Modifier.height(16.dp))
}




