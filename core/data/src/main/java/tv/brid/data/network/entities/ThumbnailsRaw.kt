package tv.brid.data.network.entities

import com.google.gson.annotations.SerializedName

data class ThumbnailsRaw(
    @SerializedName("medium")
    val thumbnail: ThumbnailRaw
)