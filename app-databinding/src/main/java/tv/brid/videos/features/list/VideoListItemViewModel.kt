package tv.brid.videos.features.list

import androidx.lifecycle.ViewModel
import tv.brid.videos.features.VideoView

class VideoListItemViewModel(
    val item: VideoView,
    private val onItemClicked: (String) -> Unit
) : ViewModel() {

    fun onItemClicked() {
        onItemClicked.invoke(item.title)
    }
}