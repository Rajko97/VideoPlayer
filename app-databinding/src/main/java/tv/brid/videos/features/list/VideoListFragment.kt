package tv.brid.videos.features.list

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tv.brid.videos.R
import tv.brid.videos.base.BaseFragment
import tv.brid.videos.databinding.FragmentVideoListBinding

class VideoListFragment : BaseFragment<FragmentVideoListBinding, VideoListViewModel>() {

    private lateinit var pagingAdapter: VideosAdapter

    override fun provideLayoutId(): Int = R.layout.fragment_video_list

    override fun provideViewModelClass(): Class<VideoListViewModel> = VideoListViewModel::class.java

    override fun setupUi() {
        pagingAdapter = VideosAdapter(VideosAdapter.Companion.VideoComparator)

        binding.recyclerVideos.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = pagingAdapter
        }
    }

    override fun subscribeObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }
}