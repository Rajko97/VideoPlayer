package tv.brid.data.repositories

import tv.brid.domain.VideosRepository
import tv.brid.domain.models.SearchResponse
import tv.brid.domain.models.Video
import javax.inject.Inject

class FakeVideoRepositoryImpl @Inject constructor(

) : VideosRepository {

    private val listOfImages = listOf(
        "https://www.kasandbox.org/programming-images/avatars/spunky-sam.png",
        "https://www.kasandbox.org/programming-images/avatars/purple-pi.png",
        "https://www.kasandbox.org/programming-images/avatars/purple-pi-teal.png",
        "https://www.kasandbox.org/programming-images/avatars/purple-pi-pink.png",
        "https://www.kasandbox.org/programming-images/avatars/primosaur-ultimate.png",
        "https://www.kasandbox.org/programming-images/avatars/primosaur-tree.png",
        "https://www.kasandbox.org/programming-images/avatars/primosaur-sapling.png",
        "https://www.kasandbox.org/programming-images/avatars/orange-juice-squid.png",
        "https://www.kasandbox.org/programming-images/avatars/old-spice-man.png",
        "https://www.kasandbox.org/programming-images/avatars/old-spice-man-blue.png"
    )

    private val listOfVideos = listOf(
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4"
    )

    override suspend fun getVideos(pageToken: String?): SearchResponse {
        val arrOfVideos = ArrayList<Video>()
        var nextPageToken: String? = null
        var prevPageToken: String? = null
        var page: Int = 1

        when (pageToken) {
            "1" -> {
                nextPageToken = "2"
                page = 0
            }

            "2" -> {
                prevPageToken = "1"
                page = 1
            }

            else -> {
                nextPageToken = "2"
                page = 0
            }
        }

        for (i in 1 + page * 20..20 + page * 20) {
            arrOfVideos.add(
                Video(
                    "Page$i",
                    "Title $i",
                    "Lorem Ipsum...",
                    listOfImages.get(i % listOfImages.size),
                    listOfVideos.get(i % listOfVideos.size)
                )
            )
        }

        return SearchResponse(nextPageToken, prevPageToken, arrOfVideos)
    }

    override suspend fun getVideo(id: String): Video {
        return listOf(getVideos("1"), getVideos("2")).flatMap { it.data }.find { it.title == id }
            ?: Video("Err", "", "", "","")
    }
}