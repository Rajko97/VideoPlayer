package tv.brid.domain

import tv.brid.domain.models.SearchResponse
import tv.brid.domain.models.VideoData

interface VideosRepository {

    suspend fun getVideos(pageToken: String?): SearchResponse

    suspend fun getVideo(id: String): VideoData
}