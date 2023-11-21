package tv.brid.data.network.entities

import com.google.gson.annotations.SerializedName
import tv.brid.domain.models.SearchResponse

data class SearchResponseRaw(
    @SerializedName("nextPageToken")
    val nextPageToken: String?,
    @SerializedName("prevPageToken")
    val prevPageToken: String?,
)

fun SearchResponseRaw.toSearchResponse() =
    SearchResponse(
        nextPageToken = nextPageToken,
        prevPageToken = prevPageToken
    )