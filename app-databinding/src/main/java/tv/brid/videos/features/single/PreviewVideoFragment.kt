package tv.brid.videos.features.single

import tv.brid.videos.R
import tv.brid.videos.base.BaseFragment
import tv.brid.videos.databinding.FragmentPreviewVideoBinding

class PreviewVideoFragment : BaseFragment<FragmentPreviewVideoBinding, PreviewVideoViewModel>() {

    override fun provideViewModelClass() = PreviewVideoViewModel::class.java

    override fun provideLayoutId() = R.layout.fragment_preview_video
}