package tv.brid.domain

import tv.brid.domain.models.SearchResponse

interface VideosRepository {

    suspend fun getVideos(pageToken: String?): SearchResponse
}