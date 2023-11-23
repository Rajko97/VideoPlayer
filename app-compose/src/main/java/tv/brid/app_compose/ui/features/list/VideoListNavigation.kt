package tv.brid.app_compose.ui.features.list

import tv.brid.app_compose.MainNavGraph
import tv.brid.app_compose.ui.common.navigation.Navigator
import tv.brid.app_compose.ui.common.navigation.ToDestination

fun Navigator.navigateToVideoDetails(id: String) {
    navigateTo(
        ToDestination(
            MainNavGraph.VIDEO_PREVIEW,
            listOf(id)
        )
    )
}