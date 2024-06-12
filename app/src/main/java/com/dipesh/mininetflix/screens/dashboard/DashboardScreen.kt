package com.dipesh.mininetflix.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dipesh.mininetflix.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {

    val state = rememberPullToRefreshState()

    Box(Modifier.nestedScroll(state.nestedScrollConnection)) {

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


        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = state,
        )
    }
}