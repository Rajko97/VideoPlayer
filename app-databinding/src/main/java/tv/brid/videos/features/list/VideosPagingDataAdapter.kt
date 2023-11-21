package tv.brid.videos.features.list

import androidx.recyclerview.widget.DiffUtil
import tv.brid.domain.models.VideoData
import tv.brid.videos.R
import tv.brid.videos.base.BasePagingDataAdapter
import javax.inject.Inject

class VideosPagingDataAdapter @Inject constructor() :
    BasePagingDataAdapter<VideoData>(VideoComparator) {

    lateinit var onItemClicked: (String) -> Unit

    override fun provideLayoutId(position: Int) = R.layout.item_video

    override fun provideViewModel(position: Int) =
        VideoListItemViewModel(getItem(position)!!, onItemClicked)

    companion object {
        object VideoComparator : DiffUtil.ItemCallback<VideoData>() {
            override fun areItemsTheSame(oldItem: VideoData, newItem: VideoData): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: VideoData, newItem: VideoData): Boolean {
                return oldItem.thumbnail == newItem.thumbnail
            }
        }
    }
}