package tv.brid.app_compose.ui.features.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import tv.brid.app_compose.MainNavGraph
import tv.brid.app_compose.ui.common.components.ErrorScreen
import tv.brid.app_compose.ui.common.components.LoadingScreen
import tv.brid.app_compose.ui.common.components.VideoPlayerAppBar
import tv.brid.app_compose.ui.common.navigation.route
import tv.brid.app_compose.ui.features.VideoView
import tv.brid.app_compose.ui.theme.VideoPlayerTheme

fun NavGraphBuilder.addVideoListDestination() {
    composable(route = MainNavGraph.VIDEO_LIST.route) {
        VideoListScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoListScreen() {
    val viewModel = hiltViewModel<VideoListViewModel>()
    val videos = viewModel.flow.collectAsLazyPagingItems()
    Scaffold(topBar = { VideoPlayerAppBar("Video List (Compose)") }) { innerPadding ->
        when (videos.loadState.refresh) {
            LoadState.Loading -> LoadingScreen(Modifier.padding(innerPadding))
            is LoadState.Error -> ErrorScreen(Modifier.padding(innerPadding))
            else ->
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    columns = GridCells.Fixed(2),
                ) {
                    items(videos.itemSnapshotList) { video ->
                        video?.let {
                            VideoItem(video, viewModel::onVideoClicked)
                        }
                    }
                }
        }
    }
}

@Composable
fun VideoItem(
    video: VideoView,
    onItemClicked: (String) -> Unit
) {
    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(
            topEnd = 0.dp,
            topStart = 16.dp,
            bottomEnd = 0.dp,
            bottomStart = 16.dp
        ),
    ) {
        Box(
            modifier = Modifier
                .clickable { onItemClicked.invoke(video.id) }
                .height(100.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                model = video.thumbnailUrl,
                contentDescription = video.title
            )
            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .background(
                        Brush.Companion.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black,
                            ),
                            startY = 0f,
                            endY = 120f
                        )
                    )
                    .padding(horizontal = 16.dp),
                text = video.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun VideoListScreenPreview() {
    VideoPlayerTheme {
        VideoListScreen()
    }
}