package tv.brid.app_compose.ui.features.single

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tv.brid.app_compose.ui.common.ScreenState
import tv.brid.app_compose.ui.common.navigation.Navigator
import tv.brid.app_compose.ui.features.toVideoView
import tv.brid.domain.usecases.GetVideoDetails
import javax.inject.Inject

@HiltViewModel
class VideoPreviewViewModel @Inject constructor(
    private val getVideoDetails: GetVideoDetails,
    private val navigator: Navigator
) : ViewModel() {

    private val _state =
        (MutableStateFlow<ScreenState<VideoPreviewScreenState>>(ScreenState.Loading))
    val state: StateFlow<ScreenState<VideoPreviewScreenState>> = _state.asStateFlow()

    fun initVideo(id: String?) {
        if (id == null) {
            _state.value = ScreenState.Error
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            val videoDetails = getVideoDetails.execute(id)
            val sourceUrl = videoDetails?.sourceUrl
            withContext(Dispatchers.Main) {
                if (videoDetails == null) {
                    _state.value = ScreenState.Error
                } else if (sourceUrl == null) {
                    _state.value = ScreenState.Error
                } else {
                    _state.value = ScreenState.Success(
                        VideoPreviewScreenState(
                            data = videoDetails.toVideoView(),
                            videoUrl = sourceUrl
                        )
                    )
                }
            }
        }
    }

    fun onBackArrowClicked() = navigator.goBack()
}