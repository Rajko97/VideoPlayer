package tv.brid.videos.features.single

import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import tv.brid.videos.R
import tv.brid.videos.base.BaseFragment
import tv.brid.videos.databinding.FragmentPreviewVideoBinding
import tv.brid.videos.features.VideoView
import javax.inject.Inject

@AndroidEntryPoint
class PreviewVideoFragment : BaseFragment<FragmentPreviewVideoBinding, PreviewVideoViewModel>() {

    private val args: PreviewVideoFragmentArgs by navArgs()

    @Inject
    lateinit var exoMediaPlayer: ExoPlayer

    override fun provideViewModelClass() = PreviewVideoViewModel::class.java

    override fun provideLayoutId() = R.layout.fragment_preview_video

    @OptIn(UnstableApi::class)
    override fun setupUi() {
        viewModel.initVideo(args.videoId)
        binding.playerView.apply {
            player = exoMediaPlayer
            setShowFastForwardButton(false)
            setShowRewindButton(false)
            setShowPreviousButton(false)
        }
    }

    override fun subscribeObservers() {
        viewModel.videoLive.observe(this) { onVideoRetrived(it) }
    }

    private fun onVideoRetrived(video: VideoView) {
        exoMediaPlayer.setMediaItem(MediaItem.fromUri(video.sourceUrl))
        exoMediaPlayer.prepare()
        exoMediaPlayer.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        exoMediaPlayer.release()
    }
}