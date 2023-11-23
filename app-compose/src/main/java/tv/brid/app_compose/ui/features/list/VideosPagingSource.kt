package tv.brid.app_compose.ui.features.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import tv.brid.app_compose.ui.features.VideoView
import tv.brid.app_compose.ui.features.toVideoView
import tv.brid.domain.usecases.GetVideos

class VideosPagingSource(
    private val getVideos: GetVideos
) : PagingSource<String, VideoView>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, VideoView> {
        return try {
            val nextPageNumber = params.key
            val response = getVideos.execute(nextPageNumber)

            val data = response.data

            LoadResult.Page(
                data = data.map { it.toVideoView() },
                prevKey = response.prevPageToken,
                nextKey = response.nextPageToken
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, VideoView>): String? = null
}