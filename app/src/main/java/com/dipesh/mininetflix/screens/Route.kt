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

    open val navCommand = routeName
}