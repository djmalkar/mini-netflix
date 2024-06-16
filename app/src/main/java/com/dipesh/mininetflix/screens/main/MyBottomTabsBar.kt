package com.dipesh.mininetflix.screens.main

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.dipesh.mininetflix.screens.BottomTab

@Composable
fun MyBottomTabsBar(
    bottomTabs: List<BottomTab>,
    currentBottomTab: BottomTab?,
    onTabClicked: (BottomTab) -> Unit,
) {
    NavigationBar(
        containerColor = Color.DarkGray
    ) {
        bottomTabs.forEachIndexed { _, bottomTab ->
            NavigationBarItem(
                alwaysShowLabel = true,
                label = {
                    Text(
                        text = bottomTab.title,
                        fontSize = 12.sp,
                        color = Color.Gray,
                    )
                },
                icon = {
                    Icon(
                        imageVector = bottomTab.icon!!,
                        contentDescription = bottomTab.title,
                        tint = Color.Gray
                    )
                },
                selected = currentBottomTab == bottomTab,
                onClick = { onTabClicked(bottomTab) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    indicatorColor = Color.DarkGray
                )
            )
        }
    }
}
