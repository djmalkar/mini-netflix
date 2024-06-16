package com.dipesh.mininetflix.screens.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dipesh.mininetflix.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DashboardScreen() {

    val state = rememberPullToRefreshState()

    Box(
        modifier = Modifier.nestedScroll(state.nestedScrollConnection)
            .background(Color.Black)
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            val dashboardMainImages = listOf (
                R.drawable.one_isto_one,
                R.drawable.one_isto_one_2,
                R.drawable.one_isto_one_3,
                R.drawable.one_isto_one_4,
                R.drawable.one_isto_one_5,
                R.drawable.one_isto_one,
                R.drawable.one_isto_one_2,
                R.drawable.one_isto_one_3,
                R.drawable.one_isto_one_4,
                R.drawable.one_isto_one_5
            )

            val pagerState = rememberPagerState(pageCount = {
                dashboardMainImages.size
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
                        Image(
                            painter = painterResource(id = dashboardMainImages[it]),
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier.fillMaxWidth(),
                            contentDescription = "Dashboard Main Images"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {  },
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
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(blackBottomGradient)
                )
                Row(
                    Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(pagerState.pageCount) { iteration ->
                        val color = if (pagerState.currentPage == iteration) Color.LightGray else Color.DarkGray
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

            // Categories in Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
                    .horizontalScroll(rememberScrollState())
            ) {
                val categories = listOf (
                    "Sports", "Movies", "TV Shows", "Documentaries", "Comedy", "Drama", "Action", "Adventure"
                )
                categories.forEach {
                    SuggestionChip(
                        onClick = {  },
                        border = BorderStroke(1.dp, Color.White),
                        shape = RoundedCornerShape(50),
                        label = { Text(
                            text = it,
                            color = Color.White,
                            style = MaterialTheme.typography.labelMedium
                        ) },
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            Text(
                text = "Continue Watching",
                color = Color.White,
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
                val images = listOf (
                    R.drawable.rectangle_1,
                    R.drawable.rectangle_2,
                    R.drawable.rectangle_3,
                    R.drawable.rectangle_4,
                    R.drawable.rectangle_5,
                    R.drawable.rectangle_1,
                    R.drawable.rectangle_2,
                    R.drawable.rectangle_3,
                    R.drawable.rectangle_4,
                    R.drawable.rectangle_5
                )
                images.forEach {
                    Column(modifier = Modifier.width(IntrinsicSize.Max)) {
                        Image(
                            painter = painterResource(id = it),
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier.size(
                                width = 250.dp,
                                height = 140.dp
                            ),
                            contentDescription = "Main Images"
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                                .background(Color.DarkGray)
                        ) {
                            Text(
                                text = "S4:E2 . 9m left",
                                fontSize = 16.sp,
                                color = Color.White,
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

            Row(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "NEW",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(Color.Yellow)
                        .padding(horizontal = 4.dp),
                    fontSize = 10.sp
                )
                Text(
                    text = "Releases",
                    color = Color.White,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "See all",
                    color = Color.White,
                    modifier = Modifier.padding(end = 8.dp),
                    fontSize = 12.sp
                )
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    tint = Color.White,
                    modifier = Modifier.padding(end = 16.dp),
                    contentDescription = "Play",
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
                val images = listOf (
                    R.drawable.protrait_1,
                    R.drawable.protrait_3,
                    R.drawable.protrait_4,
                    R.drawable.protrait_5,
                    R.drawable.protrait_1,
                    R.drawable.protrait_3,
                    R.drawable.protrait_4,
                    R.drawable.protrait_5
                )
                images.forEach {
                    Image(
                        painter = painterResource(id = it),
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxWidth(),
                        contentDescription = "Portrait Images"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Recommended For You",
                    color = Color.White,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "See all",
                    color = Color.White,
                    modifier = Modifier.padding(end = 8.dp),
                    fontSize = 12.sp
                )
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    tint = Color.White,
                    modifier = Modifier.padding(end = 16.dp),
                    contentDescription = "Play",
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
                val images = listOf (
                    R.drawable.protrait_4,
                    R.drawable.protrait_5,
                    R.drawable.protrait_1,
                    R.drawable.protrait_3,
                    R.drawable.protrait_4,
                    R.drawable.protrait_5,
                    R.drawable.protrait_1,
                    R.drawable.protrait_3,
                )
                images.forEach {
                    Image(
                        painter = painterResource(id = it),
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxWidth(),
                        contentDescription = "Recommended Images"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

        }

        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = state,
        )
    }
}