package tv.brid.domain.usecases

import tv.brid.domain.VideosRepository
import tv.brid.domain.models.SearchResponse
import javax.inject.Inject

class GetVideos @Inject constructor(
    private val videosRepository: VideosRepository
) {
    suspend fun execute(pageToken: String?): SearchResponse = videosRepository.getVideos(pageToken)
}