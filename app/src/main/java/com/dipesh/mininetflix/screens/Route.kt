package com.dipesh.mininetflix.screens

sealed class Route(val routeName: String) {
    data object HomeTab: Route("homeTab")
    data object ChannelTab: Route("channelTab")
    data object SearchTab: Route("searchTab")
    data object YouTab: Route("youTab")
    data object MoreTab: Route("moreTab")

    data object MainDashboardFragment: Route("mainDashboardFragment")
    data object ChannelFragment: Route("channelFragment")
    data object SearchFragment: Route("searchFragment")
    data object YouFragment: Route("youFragment")
    data object MoreFragment: Route("moreFragment")

    data class MovieDetailScreen(val movieId: String = ""): Route("movieDetail/{movieId}") {
        override val navCommand: String
            get() = routeName
                .replace("{movieId}", movieId)
    }

    open val navCommand = routeName
}