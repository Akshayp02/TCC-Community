package com.thecodingcult.truecommunity.community.presentation.Components

import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.DialogProperties
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.thecodingcult.truecommunity.R

@Composable
fun VideoPlayer(videoUri: Uri) {
    val context = LocalContext.current
    val exoPlayer = remember { ExoPlayer.Builder(context).build() }

    var isLoading by remember { mutableStateOf(true) }
    var isPlaying by remember { mutableStateOf(false) }
    var videoSize by remember { mutableStateOf(Pair(16, 9)) }
    var isFullScreen by remember { mutableStateOf(false) }

    // Prepare the video
    LaunchedEffect(videoUri) {
        val mediaItem = MediaItem.fromUri(videoUri)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = false

        exoPlayer.addListener(object : Player.Listener {
            @OptIn(UnstableApi::class)
            override fun onPlaybackStateChanged(state: Int) {
                when (state) {
                    Player.STATE_READY -> {
                        isLoading = false
                        exoPlayer.videoFormat?.let { format ->
                            videoSize = Pair(format.width, format.height)
                        }
                    }

                    Player.STATE_ENDED -> {
                        isPlaying = false
                        exoPlayer.seekTo(0)
                        exoPlayer.playWhenReady = false
                    }
                }
            }
        })
    }

    val aspectRatio = videoSize.first.toFloat() / videoSize.second.toFloat()

    // Normal Embedded View
    if (!isFullScreen) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(aspectRatio)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                VideoContent(
                    exoPlayer = exoPlayer,
                    isPlaying = isPlaying,
                    isFullScreen = false,
                    onPlayToggle = {
                        isPlaying = !isPlaying
                        if (exoPlayer.playbackState == Player.STATE_ENDED) exoPlayer.seekTo(0)
                        exoPlayer.playWhenReady = isPlaying
                    },
                    onToggleFullscreen = {
                        isFullScreen = true
                    }
                )
            }
        }
    }

    // Fullscreen Dialog View
    if (isFullScreen) {
        androidx.compose.ui.window.Dialog(
            onDismissRequest = { isFullScreen = false },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                decorFitsSystemWindows = false
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                VideoContent(
                    exoPlayer = exoPlayer,
                    isPlaying = isPlaying,
                    isFullScreen = true,
                    modifier = Modifier.fillMaxSize(),
                    onPlayToggle = {
                        isPlaying = !isPlaying
                        if (exoPlayer.playbackState == Player.STATE_ENDED) exoPlayer.seekTo(0)
                        exoPlayer.playWhenReady = isPlaying
                    },
                    onToggleFullscreen = {
                        isFullScreen = false
                    }
                )
            }
        }
    }
}

@Composable
fun VideoContent(
    exoPlayer: ExoPlayer,
    isPlaying: Boolean,
    isFullScreen: Boolean,
    modifier: Modifier = Modifier,
    onPlayToggle: () -> Unit,
    onToggleFullscreen: () -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .then(if (!isFullScreen) Modifier.clip(RoundedCornerShape(12.dp)) else Modifier)
            .background(Color.Black)
    ) {
        AndroidView(
            factory = {
                PlayerView(context).apply {
                    player = exoPlayer
                    useController = false
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        // Play/Pause button
        Image(
            painter = painterResource(id = if (isPlaying) R.drawable.pause else R.drawable.play),
            contentDescription = if (isPlaying) "Pause Video" else "Play Video",
            modifier = Modifier
                .size(36.dp)
                .align(Alignment.BottomStart)
                .offset(x = 10.dp, y = (-10).dp)
                .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
                .clickable { onPlayToggle() }
        )

        // Fullscreen toggle button
        Image(
            painter = painterResource(id = if (!isFullScreen) R.drawable.zoom_in_icon else R.drawable.zoom_out_icon),
            contentDescription = if (!isFullScreen) "Full screen" else "Exit full screen",
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.BottomEnd)
                .offset(x = (-10).dp, y = (-10).dp)
                .clickable { onToggleFullscreen() }
        )
    }
}



