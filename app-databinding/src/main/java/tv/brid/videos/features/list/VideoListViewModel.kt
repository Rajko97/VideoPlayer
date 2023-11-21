package tv.brid.videos.features.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import tv.brid.domain.usecases.GetVideos
import javax.inject.Inject

@HiltViewModel
class VideoListViewModel @Inject constructor(
    private val getVideos: GetVideos
) : ViewModel() {

    val flow = Pager(
        PagingConfig(pageSize = 20)
    ) {
        VideosPagingSource(getVideos)
    }.flow
        .cachedIn(viewModelScope)
}