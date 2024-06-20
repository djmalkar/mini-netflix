package com.dipesh.mininetflix.screens.main

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dipesh.mininetflix.screens.Route
import com.dipesh.mininetflix.screens.ScreensNavigator
import com.dipesh.mininetflix.screens.dashboard.DashboardScreen
import com.dipesh.mininetflix.screens.detail.movie.MovieDetailScreen

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
) {

    val screensNavigator = remember {
        ScreensNavigator()
    }

    val currentBottomTab = screensNavigator.currentBottomTab.collectAsState()

    Scaffold(
        bottomBar = {
            MyBottomTabsBar(
                bottomTabs = ScreensNavigator.BOTTOM_TABS,
                currentBottomTab = currentBottomTab.value,
                onTabClicked = { bottomTab ->
                    screensNavigator.toTab(bottomTab)
                }
            )
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
                NavHost(navController = mainNestedNavController, startDestination = Route.MainDashboardFragment.routeName) {
                    composable(route = Route.MainDashboardFragment.routeName) {
                        DashboardScreen(
                            onMovieDetailClicked = {
                                screensNavigator.toRoute(Route.MovieDetailScreen)
                            }
                        )
                    }
                    composable(route = Route.MovieDetailScreen.routeName) {
                        MovieDetailScreen()
                    }
                }
            }

            composable(route = Route.ChannelTab.routeName) {
                val mainNestedNavController = rememberNavController()
                screensNavigator.setNestedNavController(mainNestedNavController)
                NavHost(navController = mainNestedNavController, startDestination = Route.ChannelFragment.routeName) {
                    composable(route = Route.ChannelFragment.routeName) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
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
                            modifier = Modifier
                                .fillMaxSize()
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
                            modifier = Modifier
                                .fillMaxSize()
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
                            modifier = Modifier
                                .fillMaxSize()
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

@Preview
@Composable
private fun MainScreenPreview() {
    Text(
        text = "Preview Screen",
        color = Color.White
    )
}

