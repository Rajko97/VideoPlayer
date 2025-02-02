package tv.brid.data.network.entities

import com.google.gson.annotations.SerializedName

data class ThumbnailRaw(
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int
)