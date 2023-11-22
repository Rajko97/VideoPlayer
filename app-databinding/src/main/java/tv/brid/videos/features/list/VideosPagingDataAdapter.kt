package tv.brid.videos.features.list

import androidx.recyclerview.widget.DiffUtil
import tv.brid.videos.R
import tv.brid.videos.base.BasePagingDataAdapter
import tv.brid.videos.features.VideoView
import javax.inject.Inject

class VideosPagingDataAdapter @Inject constructor() :
    BasePagingDataAdapter<VideoView>(VideoComparator) {

    lateinit var onItemClicked: (String) -> Unit

    override fun provideLayoutId(position: Int) = R.layout.item_video

    override fun provideViewModel(position: Int) =
        VideoListItemViewModel(getItem(position)!!, onItemClicked)

    companion object {
        object VideoComparator : DiffUtil.ItemCallback<VideoView>() {
            override fun areItemsTheSame(oldItem: VideoView, newItem: VideoView): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: VideoView, newItem: VideoView): Boolean {
                return oldItem.title == newItem.title && oldItem.description == newItem.description &&
                        oldItem.thumbnailUrl == newItem.thumbnailUrl && oldItem.sourceUrl == newItem.sourceUrl
            }
        }
    }
}