package tv.brid.app_compose.ui.features.single

import android.annotation.SuppressLint
import androidx.annotation.OptIn
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@SuppressLint("OpaqueUnitKey")
@OptIn(UnstableApi::class)
@Composable
fun VideoScreen(modifier: Modifier, url: String, title: String) {
    val visibleState = remember { mutableStateOf(true) }
    val localContext = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(localContext).build().apply {
            setMediaItem(MediaItem.fromUri(url))
            prepare()
            play()
            addListener(
                object : Player.Listener {
                    override fun onEvents(
                        player: Player,
                        events: Player.Events
                    ) {
                        super.onEvents(player, events)
                        if (player.currentPosition >= 200)
                            visibleState.value = false
                    }

                    override fun onMediaItemTransition(
                        mediaItem: MediaItem?,
                        reason: Int
                    ) {
                        super.onMediaItemTransition(
                            mediaItem,
                            reason
                        )
                        visibleState.value = true
                    }
                }
            )
        }
    }

    Box {
        DisposableEffect(
            AndroidView(
                modifier = modifier,
                factory = { context ->
                    PlayerView(context).apply {
                        player = exoPlayer
                        setShowFastForwardButton(false)
                        setShowRewindButton(false)
                        setShowPreviousButton(false)
                    }
                },
            )
        ) {
            onDispose {
                exoPlayer.release()
            }
        }
        AnimatedVisibility(
            visible = visibleState.value,
        ) {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}