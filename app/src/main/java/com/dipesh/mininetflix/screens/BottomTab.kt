package com.dipesh.mininetflix.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomTab(val icon: ImageVector?, var title: String) {
    data object Main : BottomTab(Icons.Default.Home, "Home")
    data object Play : BottomTab(Icons.Default.PlayArrow, "Play")
    data object ComingSoon : BottomTab(Icons.Default.AccountCircle, "Coming Soon")
    data object Downloads : BottomTab(Icons.Default.Done, "Downloads")
}