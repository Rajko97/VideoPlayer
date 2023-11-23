package tv.brid.app_compose.ui.common.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destination {
    val name: String
    val args: List<NamedNavArgument>? get() = null
}

val Destination.route: String get() = name + routeArgs

@Suppress("SpreadOperator")
val Destination.routeArgs: String
    get() = args?.let { arguments ->
        String.format(routeArgsFormat, *arguments.map { "{${it.name}}" }.toTypedArray())
    } ?: ""

val Destination.routeArgsFormat: String
    get() = args
        ?.joinToString("&") { "${it.name}=%s" }
        ?.let { "?$it" } ?: ""

@Suppress("SpreadOperator")
fun Destination.routeArgs(args: List<String>): String {
    if (args.isEmpty()) return ""
    require(args.size == (this.args?.size ?: 0)) { "Illegal arg size" }
    return String.format(routeArgsFormat, *args.toTypedArray())
}

fun stringArg(argName: String) =
    navArgument(argName) {
        type = NavType.StringType
        nullable = false
        defaultValue = ""
    }