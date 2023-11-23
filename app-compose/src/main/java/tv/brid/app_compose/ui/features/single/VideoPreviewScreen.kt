package tv.brid.app_compose.ui.features.single

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import tv.brid.app_compose.MainNavGraph
import tv.brid.app_compose.ui.common.ScreenState
import tv.brid.app_compose.ui.common.components.ErrorScreen
import tv.brid.app_compose.ui.common.components.LoadingScreen
import tv.brid.app_compose.ui.common.components.VideoPlayerAppBar
import tv.brid.app_compose.ui.common.navigation.route
import tv.brid.app_compose.ui.theme.VideoPlayerTheme

fun NavGraphBuilder.addVideoPreviewDestination() {
    composable(route = MainNavGraph.VIDEO_PREVIEW.route) { navBackStackEntry ->
        val viewModel = hiltViewModel<VideoPreviewViewModel>()
        val videoId = navBackStackEntry.arguments?.getString(MainNavGraph.VideoDetailsArgs.ID.name)
        viewModel.initVideo(videoId)
        VideoPreviewScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoPreviewScreen() {
    val viewModel = hiltViewModel<VideoPreviewViewModel>()
    val state = viewModel.state.collectAsState().value
    Scaffold(
        topBar = {
            VideoPlayerAppBar(
                title = "Video Details (Compose)",
                navigateUp = viewModel::onBackArrowClicked,
            )
        }
    ) {
        when (state) {
            ScreenState.Error -> ErrorScreen(Modifier.padding(it))
            ScreenState.Loading -> LoadingScreen(Modifier.padding(it))
            is ScreenState.Success -> Content(Modifier.padding(it), state.data)
        }
    }
}

@Composable
fun Content(modifier: Modifier, state: VideoPreviewScreenState) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        VideoScreen(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            state.videoUrl,
            state.data.title
        )
        Text(
            modifier = Modifier.padding(16.dp),
            text = state.data.title,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 32.dp, end = 16.dp, bottom = 16.dp),
            text = state.data.description,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun VideoPreviewPreview() {
    VideoPlayerTheme {
        VideoPreviewScreen()
    }
}