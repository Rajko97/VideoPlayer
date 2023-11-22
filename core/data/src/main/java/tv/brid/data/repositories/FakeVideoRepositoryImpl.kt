package tv.brid.data.repositories

import tv.brid.domain.VideosRepository
import tv.brid.domain.models.SearchResponse
import tv.brid.domain.models.VideoData
import javax.inject.Inject

class FakeVideoRepositoryImpl @Inject constructor(

) : VideosRepository {

    private val fakeDataSource = listOf<SearchResponse>(
        SearchResponse("1", null, listOf(
            VideoData("Title 1", "https://www.kasandbox.org/programming-images/avatars/spunky-sam.png"),
            VideoData("Title 2", "https://www.kasandbox.org/programming-images/avatars/purple-pi.png"),
            VideoData("Title 3", "https://www.kasandbox.org/programming-images/avatars/purple-pi-teal.png"),
            VideoData("Title 4", "https://www.kasandbox.org/programming-images/avatars/purple-pi-pink.png"),
            VideoData("Title 5", "https://www.kasandbox.org/programming-images/avatars/primosaur-ultimate.png"),
            VideoData("Title 6", "https://www.kasandbox.org/programming-images/avatars/primosaur-tree.png"),
            VideoData("Title 7", "https://www.kasandbox.org/programming-images/avatars/primosaur-sapling.png"),
            VideoData("Title 8", "https://www.kasandbox.org/programming-images/avatars/orange-juice-squid.png"),
            VideoData("Title 9", "https://www.kasandbox.org/programming-images/avatars/old-spice-man.png"),
            VideoData("Title 10", "https://www.kasandbox.org/programming-images/avatars/old-spice-man-blue.png")
        )),
        SearchResponse("2", "1", listOf(
            VideoData("Title 11", "https://www.kasandbox.org/programming-images/avatars/spunky-sam.png"),
            VideoData("Title 12", "https://www.kasandbox.org/programming-images/avatars/purple-pi.png"),
            VideoData("Title 13", "https://www.kasandbox.org/programming-images/avatars/purple-pi-teal.png"),
            VideoData("Title 14", "https://www.kasandbox.org/programming-images/avatars/purple-pi-pink.png"),
            VideoData("Title 15", "https://www.kasandbox.org/programming-images/avatars/primosaur-ultimate.png"),
            VideoData("Title 16", "https://www.kasandbox.org/programming-images/avatars/primosaur-tree.png"),
            VideoData("Title 17", "https://www.kasandbox.org/programming-images/avatars/primosaur-sapling.png"),
            VideoData("Title 18", "https://www.kasandbox.org/programming-images/avatars/orange-juice-squid.png"),
            VideoData("Title 19", "https://www.kasandbox.org/programming-images/avatars/old-spice-man.png"),
            VideoData("Title 20", "https://www.kasandbox.org/programming-images/avatars/old-spice-man-blue.png")
        )),
        SearchResponse(null, "2", listOf(
            VideoData("Title 21", "https://www.kasandbox.org/programming-images/avatars/spunky-sam.png"),
            VideoData("Title 22", "https://www.kasandbox.org/programming-images/avatars/purple-pi.png"),
            VideoData("Title 23", "https://www.kasandbox.org/programming-images/avatars/purple-pi-teal.png"),
            VideoData("Title 24", "https://www.kasandbox.org/programming-images/avatars/purple-pi-pink.png"),
            VideoData("Title 25", "https://www.kasandbox.org/programming-images/avatars/primosaur-ultimate.png"),
            VideoData("Title 26", "https://www.kasandbox.org/programming-images/avatars/primosaur-tree.png"),
            VideoData("Title 27", "https://www.kasandbox.org/programming-images/avatars/primosaur-sapling.png"),
            VideoData("Title 28", "https://www.kasandbox.org/programming-images/avatars/orange-juice-squid.png"),
            VideoData("Title 29", "https://www.kasandbox.org/programming-images/avatars/old-spice-man.png"),
            VideoData("Title 30", "https://www.kasandbox.org/programming-images/avatars/old-spice-man-blue.png")
        ))

    )

    override suspend fun getVideos(pageToken: String?): SearchResponse {
        return when(pageToken) {
            "1" -> fakeDataSource[1]
            "2" -> fakeDataSource[2]
            else -> fakeDataSource[0]
        }
    }

    override suspend fun getVideo(id: String): VideoData {
        return fakeDataSource.flatMap { it.data }.find { it.title == id } ?: VideoData("Err", "")
    }
}