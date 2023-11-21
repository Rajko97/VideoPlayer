package tv.brid.data.network.api

import retrofit2.http.GET
import retrofit2.http.Query
import tv.brid.data.network.YoutubeService
import tv.brid.data.network.entities.SearchResponseRaw

interface YoutubeApi {

    @GET("/youtube/v3/search")
    suspend fun getVideos(
        @Query("key") apiKey: String = YoutubeService.API_KEY,
        @Query("maxResults") maxResults: Int = 20,
        @Query("pageToken") pageToken: String? = null,
        @Query("type") type: String = "video",
        @Query("part") part: String = "snippet"
    ): SearchResponseRaw
}