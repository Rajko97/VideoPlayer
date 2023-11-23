package tv.brid.data.network.entities

import com.google.gson.annotations.SerializedName
import tv.brid.data.db.entities.VideoEntity
import tv.brid.domain.models.Video

data class VideoRaw(
    @SerializedName("id")
    val videoId: VideoIdRaw,
    @SerializedName("snippet")
    val snippet: SnippetRaw
)

fun VideoRaw.toVideoEntity() =
    VideoEntity(
        id = videoId.id,
        title = snippet.title,
        desc = snippet.description,
        thumbnail = snippet.thumbnails.thumbnail.url,
    )

fun VideoRaw.toVideo() =
    Video(
        id = videoId.id,
        title = snippet.title,
        description = snippet.description,
        thumbnailUrl = snippet.thumbnails.thumbnail.url,
        sourceUrl = null
    )