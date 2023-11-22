package tv.brid.data.network.entities

import com.google.gson.annotations.SerializedName
import tv.brid.data.db.entities.VideoEntity
import tv.brid.domain.models.Video

data class VideoRaw(
    @SerializedName("snippet")
    val snippet: SnippetRaw
)

fun VideoRaw.toVideoEntity() =
    VideoEntity(
        id = snippet.title,
        title = snippet.title,
        desc = "",
        thumbnail = snippet.thumbnails.thumbnail.url,
        sourceUrl = ""
    )

fun VideoRaw.toVideo() =
    Video(
        id = snippet.title,
        title = snippet.title,
        description = "",
        thumbnailUrl = snippet.thumbnails.thumbnail.url,
        sourceUrl = ""
    )