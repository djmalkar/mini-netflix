package com.dipesh.mininetflix.screens.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dipesh.mininetflix.R

@OptIn(ExperimentalMaterial3Api::class)
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                ) {
                    val images = listOf (
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
                    images.forEach {
                        Image(
                            painter = painterResource(id = it),
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier.fillMaxWidth(),
                            contentDescription = "Main Images"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }

                ElevatedButton(
                    onClick = {  },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play",
                        tint = Color.Unspecified
                    )
                    Text(
                        text = "Watch Now",
                        color = Color.Black
                    )
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
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

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "NEW",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(Color.Yellow)
                        .padding(2.dp),
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
        }

        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = state,
        )
    }
}