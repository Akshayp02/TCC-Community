package com.thecodingcult.truecommunity.community.presentation.Components


import android.content.Context
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.net.Uri
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.thecodingcult.truecommunity.R
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun AudioPlayer(audioUri: Uri) {
    val context = LocalContext.current
    val exoPlayer = remember { ExoPlayer.Builder(context).build() }

    var isPlaying by remember { mutableStateOf(false) }
    var progress by remember { mutableStateOf(0f) }
    var duration by remember { mutableStateOf(0L) }
    var currentTime by remember { mutableStateOf(0L) }
    var isLoading by remember { mutableStateOf(true) }

    val audioManager = remember { context.getSystemService(Context.AUDIO_SERVICE) as AudioManager }
    val focusRequest = remember {
        AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
            .setOnAudioFocusChangeListener { focusChange ->
                if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    isPlaying = false
                    exoPlayer.pause()
                }
            }.build()
    }

    DisposableEffect(exoPlayer) {
        onDispose {
            exoPlayer.release()
            audioManager.abandonAudioFocusRequest(focusRequest)
        }
    }

    LaunchedEffect(audioUri) {
        try {
            val mediaItem = MediaItem.fromUri(audioUri)
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()

            exoPlayer.addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(state: Int) {
                    when (state) {
                        Player.STATE_READY -> {
                            isLoading = false
                            duration = exoPlayer.duration
                        }

                        Player.STATE_BUFFERING -> isLoading = true
                        Player.STATE_ENDED -> {
                            isPlaying = false
                            exoPlayer.playWhenReady = false
                            exoPlayer.seekTo(0)
                        }
                    }
                }

                override fun onIsPlayingChanged(isPlayingNow: Boolean) {
                    isPlaying = isPlayingNow
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    LaunchedEffect(isPlaying) {
        while (isPlaying) {
            currentTime = exoPlayer.currentPosition
            progress = if (duration > 0) (currentTime.toFloat() / duration) else 0f
            delay(500)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent, shape = RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                IconButton(
                    onClick = {
                        if (!isPlaying) {
                            val focusResult = audioManager.requestAudioFocus(focusRequest)
                            if (focusResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                                exoPlayer.playWhenReady = true
                                isPlaying = true
                            }
                        } else {
                            exoPlayer.playWhenReady = false
                            isPlaying = false
                        }
                    },
                    modifier = Modifier
                        .size(40.dp)
                        .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
                ) {
                    Icon(
                        painter = painterResource(if (isPlaying) R.drawable.pause else R.drawable.play),
                        contentDescription = "Play/Pause",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(27.dp)
            ) {

                // ✅ Audio Visualizer added here
                if (isPlaying) {
                    Spacer(modifier = Modifier.height(8.dp))
                    AudioVisualizer(isPlaying = isPlaying)
                }


                /**
                //                Slider(
                //                    value = progress,
                //                    onValueChange = {
                //                        progress = it
                //                        exoPlayer.seekTo((it * duration).toLong())
                //                    },
                //                    modifier = Modifier.fillMaxWidth()
                //                )
                Text(
                text = formatTime(currentTime) + " / " + formatTime(duration),
                style = AppTypography.bodySmall,
                textAlign = TextAlign.End,
                modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp, top = 15.dp)
                )

                 **/
            }
        }


    }
}

@Composable
fun AudioVisualizer(isPlaying: Boolean, barCount: Int = 20) {
    val animatedHeights = remember {
        List(barCount) { Animatable(initialValue = 10f) }
    }

    LaunchedEffect(isPlaying) {
        while (isPlaying) {
            animatedHeights.forEach { anim ->
                val newHeight = Random.nextFloat() * 60f + 10f // Always at least 10.dp
                anim.animateTo(
                    targetValue = newHeight,
                    animationSpec = tween(durationMillis = 300)
                )
            }
            delay(300)
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp), // total max height
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        animatedHeights.forEach { height ->
            val barHeight = height.value.coerceAtLeast(10f) // ensure min height
            val color = if (barHeight > 50f) Color.White else Color(0xFFFF9800)

            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(barHeight.dp)
                    .background(color, shape = RoundedCornerShape(2.dp))
            )
        }
    }
}

fun formatTime(timeInMillis: Long): String {
    val totalSeconds = timeInMillis / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}


//@Composable
//fun AudioPlayer(audioUri: Uri) {
//    val context = LocalContext.current
//    val exoPlayer = remember { ExoPlayer.Builder(context).build() }
//
//    var isPlaying by remember { mutableStateOf(false) }
//    var progress by remember { mutableStateOf(0f) }
//    var duration by remember { mutableStateOf(0L) }
//    var currentTime by remember { mutableStateOf(0L) }
//    var isLoading by remember { mutableStateOf(true) } // Track loading state
//
//    // Request audio focus
//    val audioManager = remember { context.getSystemService(Context.AUDIO_SERVICE) as AudioManager }
//    val focusRequest = remember {
//        AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
//            .setOnAudioFocusChangeListener { focusChange ->
//                if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
//                    isPlaying = false
//                    exoPlayer.pause()
//                }
//            }.build()
//    }
//
//    // Handle ExoPlayer lifecycle
//    DisposableEffect(exoPlayer) {
//        onDispose {
//            exoPlayer.release()
//            audioManager.abandonAudioFocusRequest(focusRequest)
//        }
//    }
//
//    // Prepare and Load MediaItem
//    LaunchedEffect(audioUri) {
//        try {
//            val mediaItem = MediaItem.fromUri(audioUri)
//            exoPlayer.setMediaItem(mediaItem)
//            exoPlayer.prepare()
//
//           exoPlayer.addListener(object : Player.Listener {
//               override fun onPlaybackStateChanged(state: Int) {
//                   when (state) {
//                       Player.STATE_READY -> {
//                           isLoading = false
//                           duration = exoPlayer.duration
//                       }
//
//                       Player.STATE_BUFFERING -> isLoading = true
//
//                       Player.STATE_ENDED -> {
//                           isPlaying = false
//                           exoPlayer.playWhenReady = false // Ensure playback stops
//                           exoPlayer.seekTo(0) // Reset the player to the beginning
//                       }
//                   }
//               }
//
//               override fun onIsPlayingChanged(isPlayingNow: Boolean) {
//                   isPlaying = isPlayingNow
//               }
//           })
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    // Track progress
//    LaunchedEffect(isPlaying) {
//        while (isPlaying) {
//            currentTime = exoPlayer.currentPosition
//            progress = if (duration > 0) (currentTime.toFloat() / duration) else 0f
//            kotlinx.coroutines.delay(500)
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color.Transparent, shape = RoundedCornerShape(10.dp))
//            .padding(12.dp)
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            // Loading Indicator
//            if (isLoading) {
//                CircularProgressIndicator(
//                    color = MaterialTheme.colorScheme.primary,
//                    modifier = Modifier.size(24.dp)
//                )
//            } else {
//                // Play / Pause Button
//                IconButton(
//                    onClick = {
//                        if (!isPlaying) {
//                            val focusResult = audioManager.requestAudioFocus(focusRequest)
//                            if (focusResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
//                                exoPlayer.playWhenReady = true
//                                isPlaying = true
//                            }
//                        } else {
//                            exoPlayer.playWhenReady = false
//                            isPlaying = false
//                        }
//                    },
//                    modifier = Modifier
//                        .size(40.dp)
//                        .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
//                ) {
//                    Icon(
//                        painter = painterResource(if (isPlaying) R.drawable.pause else R.drawable.play),
//                        contentDescription = "Play/Pause",
//                        tint = Color.White,
//                        modifier = Modifier.size(24.dp)
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.width(8.dp))
//
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(27.dp)
//            ) {
//                // Seek Bar
//                // Slider block
//                Slider(
//                    value = progress,
//                    onValueChange = {
//                        progress = it
//                        exoPlayer.seekTo((it * duration).toLong())
//                    },
//                    modifier = Modifier.fillMaxWidth()
//                )
//
//
////                Slider(
////                    value = progress,
////                    onValueChange = {
////                        progress = it
////                        exoPlayer.seekTo((it * duration).toLong())
////                    },
////                    modifier = Modifier.fillMaxWidth()
////                )
//                Text(
//                    text = formatTime(currentTime) + " / " + formatTime(duration),
//                    style = AppTypography.bodySmall,
//                    textAlign = TextAlign.End,
//                    modifier = Modifier
//                        .align(Alignment.CenterEnd)
//                        .padding(end = 8.dp, top = 15.dp)
//                )
//            }
//
//        }
//    }
//}
//
//
//@Composable
//fun AudioVisualizer(isPlaying: Boolean, barCount: Int = 20) {
//    val animatedHeights = remember {
//        List(barCount) { Animatable(initialValue = 10f) }
//    }
//
//    LaunchedEffect(isPlaying) {
//        while (isPlaying) {
//            animatedHeights.forEach { anim ->
//                val newHeight = Random.nextFloat() * 100f + 10f // Simulate audio level
//                anim.animateTo(
//                    targetValue = newHeight,
//                    animationSpec = tween(durationMillis = 200)
//                )
//            }
//            delay(200)
//        }
//    }
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(50.dp),
//        verticalAlignment = Alignment.Bottom,
//        horizontalArrangement = Arrangement.SpaceEvenly
//    ) {
//        animatedHeights.forEach { height ->
//            val color = if (height.value > 60f) Color.White else Color(0xFFFF9800) // Orange
//            Box(
//                modifier = Modifier
//                    .width(4.dp)
//                    .height(height.value.dp)
//                    .background(color, shape = RoundedCornerShape(2.dp))
//            )
//        }
//    }
//}
//
//
//fun formatTime(timeInMillis: Long): String {
//    val totalSeconds = timeInMillis / 1000
//    val minutes = totalSeconds / 60
//    val seconds = totalSeconds % 60
//    return String.format("%02d:%02d", minutes, seconds)
//}