package com.dipesh.mininetflix.screens.detail.movie

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dipesh.mininetflix.R

@Composable
fun EpisodesTab() {
    Column {
        for (i in 0 until 5) {
            SingleEpisodeDescription()
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun SingleEpisodeDescription() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row {
            Image(
                modifier = Modifier
                    .width(120.dp),
                painter = painterResource(id = R.drawable.landscape),
                contentDescription = "thumbnail"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "1. Pilot",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "34 min Thursday, Jun 6",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Netflix is an American subscription video on-demand Internet streaming service. The service primarily distributes original and acquired films and television shows from various genres, and it is available internationally in multiple languages.",
            style = MaterialTheme.typography.bodySmall,
            maxLines = 2,
        )
    }
}