package com.dipesh.mininetflix.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomTab(val icon: ImageVector?, var title: String) {
    data object Home : BottomTab(Icons.Rounded.Home, "Home")
    data object Channel : BottomTab(Icons.Rounded.AddCircle, "Channel")
    data object Search : BottomTab(Icons.Rounded.Search, "Search")
    data object You : BottomTab(Icons.Rounded.Person, "You")
    data object More : BottomTab(Icons.Rounded.Menu, "More")
}