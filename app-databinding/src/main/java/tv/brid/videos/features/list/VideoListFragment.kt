package tv.brid.videos.features.list

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tv.brid.videos.R
import tv.brid.videos.base.BaseFragment
import tv.brid.videos.databinding.FragmentVideoListBinding
import javax.inject.Inject

@AndroidEntryPoint
class VideoListFragment : BaseFragment<FragmentVideoListBinding, VideoListViewModel>() {

    @Inject
    lateinit var pagingAdapter: VideosPagingDataAdapter

    override fun provideLayoutId(): Int = R.layout.fragment_video_list

    override fun provideViewModelClass() = VideoListViewModel::class.java

    override fun setupUi() {
        pagingAdapter.onItemClicked = { this@VideoListFragment.onItemClicked(it) }

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

    private fun onItemClicked(itemId: String) {
        findNavController().navigate(
            VideoListFragmentDirections.actionVideoListFragmentToPreviewVideoFragment(
                videoId = itemId
            )
        )
    }
}