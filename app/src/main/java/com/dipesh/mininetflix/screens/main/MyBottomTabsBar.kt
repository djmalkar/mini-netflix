package com.dipesh.mininetflix.screens.main

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dipesh.mininetflix.screens.BottomTab

@Composable
fun MyBottomTabsBar(
    bottomTabs: List<BottomTab>,
    currentBottomTab: BottomTab?,
    onTabClicked: (BottomTab) -> Unit,
) {
    NavigationBar(
        modifier = Modifier.height(60.dp),
        containerColor = Color(0xFF0A0808),
    ) {
        bottomTabs.forEachIndexed { _, bottomTab ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color(0xFF0A0808)
                ),
                alwaysShowLabel = true,
                label = {
                    Text(
                        text = bottomTab.title,
                        fontSize = 12.sp,
                        color = if (currentBottomTab == bottomTab) Color.White else Color.Gray,
                    )
                },
                icon = {
                    Icon(
                        imageVector = bottomTab.icon!!,
                        contentDescription = bottomTab.title,
                        tint = if (currentBottomTab == bottomTab) Color.White else Color.Gray
                    )
                },
                selected = currentBottomTab == bottomTab,
                onClick = { onTabClicked(bottomTab) }
            )
        }
    }
}
