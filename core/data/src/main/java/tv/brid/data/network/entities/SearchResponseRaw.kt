package tv.brid.data.network.entities

import com.google.gson.annotations.SerializedName
import tv.brid.domain.models.SearchResponse
import tv.brid.domain.models.VideoData

data class SearchResponseRaw(
    @SerializedName("nextPageToken")
    val nextPageToken: String? = null,
    @SerializedName("prevPageToken")
    val prevPageToken: String? = null,
    @SerializedName("items")
    val data: List<VideoDataRaw>
)

fun SearchResponseRaw.toSearchResponse() =
    SearchResponse(
        nextPageToken = nextPageToken,
        prevPageToken = prevPageToken,
        data = data.map {
            VideoData(
                title = it.snippet.title,
                thumbnail = it.snippet.thumbnails.thumbnail.url
            )
        }
    )