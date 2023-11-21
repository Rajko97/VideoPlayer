package tv.brid.data.repositories

import tv.brid.data.network.api.YoutubeApi
import tv.brid.data.network.entities.toSearchResponse
import tv.brid.domain.VideosRepository
import javax.inject.Inject

class YoutubeRepositoryImpl @Inject constructor(
    private val youtubeApi: YoutubeApi
) : VideosRepository {

    override suspend fun getVideos() = youtubeApi.getVideos().toSearchResponse()
}
