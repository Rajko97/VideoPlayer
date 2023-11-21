package tv.brid.data.repositories

import tv.brid.domain.VideosRepository
import tv.brid.domain.models.SearchResponse
import tv.brid.domain.models.VideoData
import javax.inject.Inject

class FakeVideoRepositoryImpl @Inject constructor(

) : VideosRepository {

    private val fakeDataSource = listOf<SearchResponse>(
        SearchResponse(null, null, listOf(
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
        ))
    )

    override suspend fun getVideos(pageToken: String?): SearchResponse {
            return fakeDataSource[0]
    }
}