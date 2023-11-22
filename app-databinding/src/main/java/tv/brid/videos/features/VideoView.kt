package tv.brid.videos.features

import tv.brid.domain.models.Video

data class VideoView(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val thumbnailUrl: String = "",
    val sourceUrl: String = ""
)

fun Video.toVideoView() = VideoView(
    id = id,
    title = title,
    description = description,
    thumbnailUrl = thumbnailUrl,
    sourceUrl = sourceUrl
)