package tv.brid.data.network.entities

import com.google.gson.annotations.SerializedName

data class VideoIdRaw(
    @SerializedName("videoId")
    val id: String
)