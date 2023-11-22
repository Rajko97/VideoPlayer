package tv.brid.videos.features.single

import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import tv.brid.videos.R
import tv.brid.videos.base.BaseFragment
import tv.brid.videos.databinding.FragmentPreviewVideoBinding

@AndroidEntryPoint
class PreviewVideoFragment : BaseFragment<FragmentPreviewVideoBinding, PreviewVideoViewModel>() {

    private val args: PreviewVideoFragmentArgs by navArgs()

    override fun provideViewModelClass() = PreviewVideoViewModel::class.java

    override fun provideLayoutId() = R.layout.fragment_preview_video

    override fun setupUi() {
        viewModel.initVideo(args.videoId)
    }

    override fun subscribeObservers() {
        viewModel.videoLive.observe(this) {
            binding.txtTest.text = it.toString()
        }
    }
}