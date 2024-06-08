package com.dipesh.mininetflix.screens

import androidx.navigation.NavHostController
import com.dipesh.mininetflix.common.base64.Base64EncodeDecode.decodeFromBase64
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ScreensNavigator {

    private val scope = CoroutineScope(Dispatchers.Main.immediate)

    private lateinit var parentNavController: NavHostController
    private lateinit var nestedNavController: NavHostController

    private var nestedNavControllerObserveJob: Job? = null
    private var parentNavControllerObserveJob: Job? = null

    val currentBottomTab = MutableStateFlow<BottomTab?>(null)
    val currentRoute = MutableStateFlow<Route?>(null)
    val isRootRoute = MutableStateFlow(false)

    fun setParentNavController(navController: NavHostController) {
        parentNavController = navController

        parentNavControllerObserveJob?.cancel()
        parentNavControllerObserveJob = scope.launch {
            navController.currentBackStackEntryFlow.map { backStackEntry ->
                val bottomTab = when (val routeName = backStackEntry.destination.route) {
                    Route.HomeTab.routeName -> BottomTab.Home
                    Route.ChannelTab.routeName -> BottomTab.Channel
                    Route.SearchTab.routeName -> BottomTab.Search
                    Route.YouTab.routeName -> BottomTab.You
                    Route.MoreTab.routeName -> BottomTab.More
                    null -> null
                    else -> throw RuntimeException("unsupported bottom tab: $routeName")
                }
                currentBottomTab.value = bottomTab
            }.collect()
        }
    }

    fun setNestedNavController(navController: NavHostController) {
        nestedNavController = navController

        nestedNavControllerObserveJob?.cancel()
        nestedNavControllerObserveJob = scope.launch {
            navController.currentBackStackEntryFlow.map { backStackEntry ->
                val route = when (val routeName = backStackEntry.destination.route) {
                    Route.HomeTab.routeName -> Route.HomeTab
                    Route.ChannelTab.routeName -> Route.ChannelTab
                    Route.SearchTab.routeName -> Route.SearchTab
                    Route.YouTab.routeName -> Route.YouTab
                    Route.MoreTab.routeName -> Route.MoreTab
                    Route.HomeFragment.routeName -> Route.HomeFragment
                    Route.ChannelFragment.routeName -> Route.ChannelFragment
                    Route.SearchFragment.routeName -> Route.SearchFragment
                    Route.YouFragment.routeName -> Route.YouFragment
                    Route.MoreFragment.routeName -> Route.MoreFragment
                    null -> null
                    else -> throw RuntimeException("unsupported route: $routeName")
                }
                currentRoute.value = route
                isRootRoute.value = route == Route.YouTab
            }.collect()
        }

    }

    fun navigateBack() {
        if (!nestedNavController.popBackStack()) {
            parentNavController.popBackStack()
        }
    }

    fun toTab(bottomTab: BottomTab) {
        val route = when(bottomTab) {
            BottomTab.Home -> Route.HomeTab
            BottomTab.Channel -> Route.ChannelTab
            BottomTab.Search -> Route.SearchTab
            BottomTab.You -> Route.YouTab
            BottomTab.More -> Route.MoreTab
        }
        parentNavController.navigate(route.routeName) {
            parentNavController.graph.startDestinationRoute?.let { startRoute ->
                popUpTo(startRoute) {
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun toRoute(route: Route) {
        nestedNavController.navigate(route.navCommand)
    }


    companion object {
        val BOTTOM_TABS = listOf(BottomTab.Home, BottomTab.Channel, BottomTab.Search, BottomTab.You, BottomTab.More)
    }


}