package tv.brid.domain.usecases

import tv.brid.domain.VideosRepository
import tv.brid.domain.models.VideoData
import javax.inject.Inject

class GetVideoDetails @Inject constructor(
    private val videosRepository: VideosRepository
) {
    suspend fun execute(id: String): VideoData = videosRepository.getVideo(id)
}