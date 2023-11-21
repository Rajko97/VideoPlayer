package tv.brid.domainhilt.usecae

import tv.brid.domainhilt.VideosRepositoryHilt
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetVideosHilt @Inject constructor(
    private val videosRepository: VideosRepositoryHilt
) {
    suspend fun execute() = videosRepository.getVideos()
}