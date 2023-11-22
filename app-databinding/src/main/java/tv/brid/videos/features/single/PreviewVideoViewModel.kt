package tv.brid.videos.features.single

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tv.brid.domain.usecases.GetVideoDetails
import tv.brid.videos.features.VideoView
import tv.brid.videos.features.toVideoView
import javax.inject.Inject

@HiltViewModel
class PreviewVideoViewModel @Inject constructor(
    private val getVideoDetails: GetVideoDetails
) : ViewModel() {

    private val _videoLive = MutableLiveData<VideoView>()
    val videoLive: LiveData<VideoView>
        get() = _videoLive

    fun initVideo(id: String) {
        viewModelScope.launch {
            _videoLive.postValue(getVideoDetails.execute(id).toVideoView())
        }
    }
}