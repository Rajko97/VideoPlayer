package tv.brid.data.network.entities

import com.google.gson.annotations.SerializedName

data class VideoDataRaw(
    @SerializedName("snippet")
    val snippet: SnippetRaw
)