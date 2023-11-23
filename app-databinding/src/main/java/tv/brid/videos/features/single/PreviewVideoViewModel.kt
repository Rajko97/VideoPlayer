package tv.brid.videos.features.single

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    private val _isLoadingLive = MutableLiveData<Boolean>()
    val isLoadingLive: LiveData<Boolean>
        get() = _isLoadingLive

    private val _isErrorLive = MutableLiveData<Boolean>()
    val isErrorLive: LiveData<Boolean>
        get() = _isErrorLive

    private val _videoSourceLive = MutableLiveData<String?>()
    val videoSourceLive: LiveData<String?>
        get() = _videoSourceLive

    init {
        setLoadingState()
    }

    private fun setLoadingState() {
        _isLoadingLive.value = true
        _isErrorLive.value = false
    }

    private fun setErrorState() {
        _isErrorLive.value = true
        _isLoadingLive.value = false
    }

    private fun setDataState() {
        _isErrorLive.value = false
        _isLoadingLive.value = false
    }

    fun initVideo(id: String?) {
        if (id == null) {
            setErrorState()
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            val videoDetails = getVideoDetails.execute(id)
            val sourceUrl = videoDetails?.sourceUrl
            withContext(Dispatchers.Main) {
                if (videoDetails == null) {
                    setErrorState()
                } else if (sourceUrl == null) {
                    setErrorState()
                } else {
                    setDataState()
                    _videoLive.value = videoDetails.toVideoView()
                    _videoSourceLive.value = sourceUrl
                }
            }
        }
    }
}