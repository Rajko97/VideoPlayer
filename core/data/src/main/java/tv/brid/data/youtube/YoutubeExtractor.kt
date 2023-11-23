package tv.brid.data.youtube

import com.maxrave.kotlinyoutubeextractor.State
import com.maxrave.kotlinyoutubeextractor.YTExtractor
import com.maxrave.kotlinyoutubeextractor.bestQuality
import com.maxrave.kotlinyoutubeextractor.getVideoOnly
import javax.inject.Inject

class YoutubeExtractor @Inject constructor(
    private val ytExtractor: YTExtractor
) {

    suspend fun getVideoUrl(videoId: String): String? {
        ytExtractor.extract(videoId)
        if (ytExtractor.state == State.SUCCESS) {
            return ytExtractor.getYTFiles()?.getVideoOnly()?.bestQuality()?.url
        }
        return null
    }
}