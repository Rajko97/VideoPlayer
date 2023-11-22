package tv.brid.domain.usecases

import tv.brid.domain.VideosRepository
import tv.brid.domain.models.Video
import javax.inject.Inject

class GetVideoDetails @Inject constructor(
    private val videosRepository: VideosRepository
) {
    suspend fun execute(id: String): Video = videosRepository.getVideo(id)
}