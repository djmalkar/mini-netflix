package com.dipesh.mininetflix.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomTab(val icon: ImageVector?, var title: String) {
    data object Home : BottomTab(Icons.Rounded.Home, "Home")
    data object Channel : BottomTab(Icons.Rounded.Search, "Channel")
    data object Search : BottomTab(Icons.Rounded.Search, "Search")
    data object You : BottomTab(Icons.Rounded.AccountCircle, "You")
    data object More : BottomTab(Icons.Rounded.Settings, "More")
}