package tv.brid.videos

import androidx.paging.PagingSource
import androidx.paging.PagingState
import tv.brid.domain.models.VideoData
import tv.brid.domain.usecases.GetVideos

class VideosPagingSource(
    private val getVideos: GetVideos
) : PagingSource<String, VideoData>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, VideoData> {
        return try {
            val nextPageNumber = params.key
            val response = getVideos.execute(nextPageNumber)

            val data = response.data

            if (data != null) {
                LoadResult.Page(
                    data = data,
                    prevKey = response.prevPageToken,
                    nextKey = response.nextPageToken
                )
            } else {
                LoadResult.Error(Exception("Data null"))
            }

        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, VideoData>): String? {
//        return state.anchorPosition?.let { anchorPosition ->
//            val anchorPage = state.closestPageToPosition(anchorPosition)
//            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//        }
        return null
    }
}