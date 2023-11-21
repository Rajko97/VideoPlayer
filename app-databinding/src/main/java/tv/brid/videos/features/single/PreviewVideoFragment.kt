package tv.brid.videos.features.single

import androidx.navigation.fragment.navArgs
import tv.brid.videos.R
import tv.brid.videos.base.BaseFragment
import tv.brid.videos.databinding.FragmentPreviewVideoBinding

class PreviewVideoFragment : BaseFragment<FragmentPreviewVideoBinding, PreviewVideoViewModel>() {

    private val args: PreviewVideoFragmentArgs by navArgs()

    override fun provideViewModelClass() = PreviewVideoViewModel::class.java

    override fun provideLayoutId() = R.layout.fragment_preview_video

    override fun setupUi() {
        binding.txtTest.text = args.videoId
    }
}