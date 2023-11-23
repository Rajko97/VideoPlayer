package tv.brid.app_compose.ui.features.single

import tv.brid.app_compose.ui.features.VideoView

data class VideoPreviewScreenState(
    val data: VideoView,
    val videoUrl: String
)