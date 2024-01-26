package tv.brid.app_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import tv.brid.app_compose.ui.common.navigation.NavigationAction
import tv.brid.app_compose.ui.common.navigation.Navigator
import tv.brid.app_compose.ui.common.navigation.ToDestination
import tv.brid.app_compose.ui.features.list.addVideoListDestination
import tv.brid.app_compose.ui.features.single.addVideoPreviewDestination
import tv.brid.app_compose.ui.theme.VideoPlayerTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VideoPlayerTheme {
                VideoPlayerApp(navigator)
            }
        }
    }
}

@Composable
fun VideoPlayerApp(navigator: Navigator) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        val navController = rememberNavController()

        fun onNavigationAction(action: NavigationAction) {
            when (action) {
                is ToDestination -> navController.navigate(action.route, action.navOptions)
                NavigationAction.GoBack -> {
                    if (navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
                        navController.popBackStack()
                    }
                }
            }
        }

        LaunchedEffect(Unit) {
            navigator.sharedFlow.onEach { onNavigationAction(it) }.launchIn(this)
        }

        NavHost(
            navController = navController,
            startDestination = MainNavGraph.startDestination,
        ) {
            addVideoListDestination()
            addVideoPreviewDestination()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VideoPlayerTheme {
        VideoPlayerApp(Navigator())
    }
}