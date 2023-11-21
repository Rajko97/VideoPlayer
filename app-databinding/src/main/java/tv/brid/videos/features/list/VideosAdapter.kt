package tv.brid.videos.features.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import tv.brid.domain.models.VideoData
import tv.brid.videos.R

class VideosAdapter(diffCallback: DiffUtil.ItemCallback<VideoData>) :
    PagingDataAdapter<VideoData, VideosAdapter.VideoViewHolder>(diffCallback) {

    companion object {
        object VideoComparator : DiffUtil.ItemCallback<VideoData>() {
            override fun areItemsTheSame(oldItem: VideoData, newItem: VideoData): Boolean {
                // Id is unique.
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: VideoData, newItem: VideoData): Boolean {
                return oldItem.thumbnail == newItem.thumbnail
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val item = getItem(position)
        // Note that item can be null. ViewHolder must support binding a
        // null item as a placeholder.
        holder.bind(item)
    }

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.txt_title)
        private val thumbnail = itemView.findViewById<ImageView>(R.id.img_thumbnail)

        fun bind(data: VideoData?) {
            title.text = data?.title
            thumbnail.load(data?.thumbnail)
        }
    }
}