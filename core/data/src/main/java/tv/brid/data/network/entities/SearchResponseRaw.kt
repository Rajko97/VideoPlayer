package tv.brid.data.network.entities

import com.google.gson.annotations.SerializedName
import tv.brid.domain.models.SearchResponse

data class SearchResponseRaw(
    @SerializedName("nextPageToken")
    val nextPageToken: String? = null,
    @SerializedName("prevPageToken")
    val prevPageToken: String? = null,
    @SerializedName("items")
    val data: List<VideoRaw>
)

fun SearchResponseRaw.toSearchResponse() =
    SearchResponse(
        nextPageToken = nextPageToken,
        prevPageToken = prevPageToken,
        data = data.map { it.toVideo() }
    )