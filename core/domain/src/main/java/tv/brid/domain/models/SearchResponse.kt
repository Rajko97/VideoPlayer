package tv.brid.domain.models

data class SearchResponse(
    val nextPageToken: String?,
    val prevPageToken: String?,
    val data: List<VideoData>?
)