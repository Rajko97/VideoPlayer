package tv.brid.data.network.entities

import com.google.gson.annotations.SerializedName
import tv.brid.data.db.entities.VideoEntity

data class VideoDataRaw(
    @SerializedName("snippet")
    val snippet: SnippetRaw
)

fun VideoDataRaw.toVideoEntity() =
    VideoEntity(
        id = snippet.title,
        title = snippet.title,
        desc = "",
        thumbnail = snippet.thumbnails.thumbnail.url
    )