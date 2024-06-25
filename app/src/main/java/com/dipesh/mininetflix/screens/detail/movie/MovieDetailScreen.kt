package com.dipesh.mininetflix.screens.detail.movie

import android.util.Log
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dipesh.mininetflix.R
import com.dipesh.mininetflix.movie.dao.MovieDao
import com.dipesh.mininetflix.screens.dashboard.DashboardViewModel


@Composable
fun MovieDetailScreen(
    viewModel: DashboardViewModel = hiltViewModel()
) {

    val nowPlayingMovies = viewModel.latestNowPlayingMovies.collectAsState()

    LaunchedEffect(Unit) {
        Log.d("MovieDetailScreen", "Fetching Initial Dashboard Data")
        viewModel.fetchInitialDashboardData()
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.height(200.dp)) {
            // Movie Showcase area
        }

        Text(
            text = "Movie Name",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        MovieTopGenres()

        ElevatedButton(
            onClick = { },
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
        ) {
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = ""
            )
            Text(
                text = "Resume",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    tint = Color.White,
                    contentDescription = "Watchlist",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Watchlist",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 26.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    tint = Color.White,
                    contentDescription = "Trailers",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Trailers",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    tint = Color.White,
                    contentDescription = "Share",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Share",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }

        Text(
            text = "Netflix is one of the world's leading entertainment services with 270 million paid " +
                    "memberships in over 190 countries enjoying TV series, films and games across a wide variety " +
                    "of genres and languages. Members can play, pause and resume watching as much as they want, anytime, " +
                    "anywhere, and can change their plans at any time.Netflix VP of Finance to Present at the " +
                    "MoffettNathanson Media, Internet & Communications Conference",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(
            modifier = Modifier
                .padding(vertical = 24.dp, horizontal = 16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Audio Available in: Hindi",
            style = MaterialTheme.typography.titleMedium
        )

        var tabIndex by remember { mutableIntStateOf(0) }

        val tabs = listOf("Episodes", "More like this", "Cast & more")

        TabRow(
            selectedTabIndex = tabIndex,
            containerColor = Color.Black,
            contentColor = Color.White,
            modifier = Modifier
                .background(Color.Black)
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    content = {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                )
            }
        }

        when (tabIndex) {
            0 -> EpisodesTab()
            1 -> MoreTab(nowPlayingMovies)
            2 -> CastTab(nowPlayingMovies)
        }
    }
}

@Composable
private fun MovieTopGenres() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "U/A 13+",
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.padding(end = 4.dp)
        )
        VerticalDivider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier.height(10.dp)
        )
        Text(
            text = "Drama",
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.Gray)
                .size(4.dp)
        )
        Text(
            text = "Showbiz",
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.Gray)
                .size(4.dp)
        )
        Text(
            text = "Comedy",
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        VerticalDivider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier.height(10.dp)
        )
        Text(
            text = "4 seasons",
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        VerticalDivider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier.height(10.dp)
        )
        Text(
            text = "2021",
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 4.dp)
        )

    }
}

@Composable
@Preview
fun PreviewMovieTopGenres() {

}