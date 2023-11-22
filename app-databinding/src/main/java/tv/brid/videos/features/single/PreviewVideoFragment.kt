package tv.brid.videos.features.single

import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import tv.brid.videos.R
import tv.brid.videos.base.BaseFragment
import tv.brid.videos.databinding.FragmentPreviewVideoBinding
import javax.inject.Inject

@AndroidEntryPoint
class PreviewVideoFragment : BaseFragment<FragmentPreviewVideoBinding, PreviewVideoViewModel>() {

    private val args: PreviewVideoFragmentArgs by navArgs()

    @Inject
    lateinit var exoMediaPlayer: ExoPlayer

    override fun provideViewModelClass() = PreviewVideoViewModel::class.java

    override fun provideLayoutId() = R.layout.fragment_preview_video

    override fun setupUi() {
        viewModel.initVideo(args.videoId)
        binding.playerView.player = exoMediaPlayer
    }

    override fun subscribeObservers() {
        viewModel.videoLive.observe(this) {
            val mediaItem = MediaItem.fromUri(it.sourceUrl)
            exoMediaPlayer.setMediaItem(mediaItem)
            exoMediaPlayer.prepare()
            exoMediaPlayer.play()
        }
    }

    override fun onStop() {
        super.onStop()
        exoMediaPlayer.release()
    }
}