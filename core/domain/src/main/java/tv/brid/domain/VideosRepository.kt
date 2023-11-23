package tv.brid.domain

import tv.brid.domain.models.SearchResponse
import tv.brid.domain.models.Video

interface VideosRepository {

    suspend fun getVideos(pageToken: String?): SearchResponse

    suspend fun getVideo(id: String): Video?
}