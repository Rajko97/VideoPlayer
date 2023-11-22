package tv.brid.data.network.entities

import com.google.gson.annotations.SerializedName

data class SnippetRaw(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnails")
    val thumbnails: ThumbnailsRaw
)