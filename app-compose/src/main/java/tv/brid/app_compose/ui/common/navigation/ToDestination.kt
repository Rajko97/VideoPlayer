package tv.brid.app_compose.ui.common.navigation

import androidx.navigation.NavOptionsBuilder

class ToDestination(
    private val destination: Destination,
    private val args: List<String>? = null,
    val navOptions: NavOptionsBuilder.() -> Unit = {}
) : NavigationAction {

    val route: String
        get() = destination.name + (args?.let { destination.routeArgs(it) } ?: "")
}