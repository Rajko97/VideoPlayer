package tv.brid.videos.features.list

import androidx.lifecycle.ViewModel
import tv.brid.domain.models.VideoData

class VideoListItemViewModel(
    val item: VideoData,
    private val onItemClicked: (String) -> Unit
) : ViewModel() {

    fun onItemClicked() {
        onItemClicked.invoke(item.title)
    }
}