package tv.brid.app_compose.ui.features.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import tv.brid.app_compose.ui.common.navigation.Navigator
import tv.brid.app_compose.ui.features.VideoView
import tv.brid.domain.usecases.GetVideos
import javax.inject.Inject

@HiltViewModel
class VideoListViewModel @Inject constructor(
    getVideos: GetVideos,
    private val navigator: Navigator
) : ViewModel() {

    val flow: Flow<PagingData<VideoView>> = Pager(
        PagingConfig(
            initialLoadSize = 20,
            pageSize = 20
        )
    ) {
        VideosPagingSource(getVideos)
    }.flow
        .cachedIn(viewModelScope)

    fun onVideoClicked(id: String) {
        navigator.navigateToVideoDetails(id)
    }
}