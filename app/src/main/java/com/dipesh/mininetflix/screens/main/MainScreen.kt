package com.dipesh.mininetflix.screens.main

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dipesh.mininetflix.screens.Route
import com.dipesh.mininetflix.screens.ScreensNavigator

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
) {

    val screensNavigator = remember {
        ScreensNavigator()
    }

    val currentBottomTab = screensNavigator.currentBottomTab.collectAsState()

    val currentRoute = screensNavigator.currentRoute.collectAsState()

    val isRootRoute = screensNavigator.isRootRoute.collectAsState()

    var isFavoriteQuestion by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            MyTopAppBar(
                isRootRoute = isRootRoute.value,
                onToggleFavoriteClicked = {

                },
                onBackClicked = {
                    screensNavigator.navigateBack()
                }
            )
        },
        bottomBar = {
            BottomAppBar(modifier = Modifier) {
                MyBottomTabsBar(
                    bottomTabs = ScreensNavigator.BOTTOM_TABS,
                    currentBottomTab = currentBottomTab.value,
                    onTabClicked = { bottomTab ->
                      screensNavigator.toTab(bottomTab)
                    }
                )
            }
        },
        content = { padding ->
            MainScreenContent(
                padding = padding,
                screensNavigator = screensNavigator,
            )
        }
    )

}

@Composable
private fun MainScreenContent(
    padding: PaddingValues,
    screensNavigator: ScreensNavigator,
) {
    val parentNavController = rememberNavController()
    screensNavigator.setParentNavController(parentNavController)

    Surface(
        modifier = Modifier
            .padding(padding)
            .padding(horizontal = 12.dp),
    ) {
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = parentNavController,
            enterTransition = { fadeIn(animationSpec = tween(200)) },
            exitTransition = { fadeOut(animationSpec = tween(200)) },
            startDestination = Route.HomeTab.routeName,
        ) {
            composable(route = Route.HomeTab.routeName) {
                val mainNestedNavController = rememberNavController()
                screensNavigator.setNestedNavController(mainNestedNavController)
                NavHost(navController = mainNestedNavController, startDestination = Route.HomeFragment.routeName) {
                    composable(route = Route.HomeFragment.routeName) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                                .background(
                                    color = Color.Red
                                )
                        )
                    }
                }
            }

            composable(route = Route.ChannelTab.routeName) {
                val mainNestedNavController = rememberNavController()
                screensNavigator.setNestedNavController(mainNestedNavController)
                NavHost(navController = mainNestedNavController, startDestination = Route.ChannelFragment.routeName) {
                    composable(route = Route.ChannelFragment.routeName) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                                .background(
                                    color = Color.Cyan
                                )
                        )
                    }
                }
            }

            composable(route = Route.SearchTab.routeName) {
                val mainNestedNavController = rememberNavController()
                screensNavigator.setNestedNavController(mainNestedNavController)
                NavHost(navController = mainNestedNavController, startDestination = Route.SearchFragment.routeName) {
                    composable(route = Route.SearchFragment.routeName) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                                .background(
                                    color = Color.Blue
                                )
                        )
                    }
                }
            }

            composable(route = Route.YouTab.routeName) {
                val mainNestedNavController = rememberNavController()
                screensNavigator.setNestedNavController(mainNestedNavController)
                NavHost(navController = mainNestedNavController, startDestination = Route.YouFragment.routeName) {
                    composable(route = Route.YouFragment.routeName) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                                .background(
                                    color = Color.Green
                                )
                        )
                    }
                }
            }

            composable(route = Route.MoreTab.routeName) {
                val mainNestedNavController = rememberNavController()
                screensNavigator.setNestedNavController(mainNestedNavController)
                NavHost(navController = mainNestedNavController, startDestination = Route.YouTab.routeName) {
                    composable(route = Route.YouTab.routeName) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                                .background(
                                    color = Color.Yellow
                                )
                        )
                    }
                }
            }

        }
    }
}

