package tv.brid.data.repositories

import kotlinx.coroutines.flow.first
import tv.brid.data.db.dao.VideoDao
import tv.brid.data.db.entities.toVideoData
import tv.brid.data.network.api.YoutubeApi
import tv.brid.data.network.entities.toSearchResponse
import tv.brid.data.network.entities.toVideoEntity
import tv.brid.data.youtube.YoutubeExtractor
import tv.brid.domain.VideosRepository
import tv.brid.domain.models.Video
import javax.inject.Inject

class YoutubeRepositoryImpl @Inject constructor(
    private val youtubeApi: YoutubeApi,
    private val youtubeDao: VideoDao,
    private val youtubeExtractor: YoutubeExtractor
) : VideosRepository {

    override suspend fun getVideos(pageToken: String?) =
        youtubeApi.getVideos(pageToken = pageToken).also {
            youtubeDao.updateVideos(it.data.map { raw -> raw.toVideoEntity() })
        }.toSearchResponse()

    override suspend fun getVideo(id: String): Video =
        youtubeDao.get(id)
            .first()
            .toVideoData()
            .copy(sourceUrl = youtubeExtractor.getVideoUrl(id))
}
