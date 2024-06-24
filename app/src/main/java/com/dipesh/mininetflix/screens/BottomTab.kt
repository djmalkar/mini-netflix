package com.dipesh.mininetflix.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomTab(val icon: ImageVector?, var title: String) {
    data object Home : BottomTab(Icons.Outlined.Home, "Home")
    data object Channel : BottomTab(Icons.Outlined.Star, "Channel")
    data object Search : BottomTab(Icons.Outlined.Search, "Search")
    data object You : BottomTab(Icons.Outlined.Person, "You")
    data object More : BottomTab(Icons.Outlined.Menu, "More")
}