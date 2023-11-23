package tv.brid.app_compose

import androidx.navigation.NamedNavArgument
import tv.brid.app_compose.ui.common.navigation.Destination
import tv.brid.app_compose.ui.common.navigation.route
import tv.brid.app_compose.ui.common.navigation.stringArg

enum class MainNavGraph : Destination {
    VIDEO_LIST,
    VIDEO_PREVIEW {
        override val args: List<NamedNavArgument>
            get() = listOf(stringArg(VideoDetailsArgs.ID.name))
    };

    companion object {
        val startDestination: String = VIDEO_LIST.route
    }

    enum class VideoDetailsArgs { ID }
}