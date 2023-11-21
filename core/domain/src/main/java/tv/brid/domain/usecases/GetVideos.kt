package tv.brid.domain.usecases

import tv.brid.domain.VideosRepository
import javax.inject.Inject

class GetVideos @Inject constructor(
    private val videosRepository: VideosRepository
) {
    suspend fun execute() = videosRepository.getVideos()
}