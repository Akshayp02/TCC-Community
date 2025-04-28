package com.thecodingcult.truecommunity.community.presentation.animations

import android.app.Activity
import android.content.pm.PackageManager
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.thecodingcult.truecommunity.R
import com.thecodingcult.truecommunity.community.domain.VoiceRecorderHelper
import java.io.File

/**
The code defines a Composable function `VoiceRecorderButton` that creates a button for recording voice messages.
 **/
@Composable
fun VoiceRecorderButton(
    isRecording: MutableState<Boolean>,
    messageText: TextFieldValue,
    onVoiceMessageRecorded: (File) -> Unit
) {
    val context = LocalContext.current
    val activity = context as? Activity

    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = if (isRecording.value) 1.5f else 1f, //50% larger during recording.
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val micIcon = if (messageText.text.isEmpty()) R.drawable.mic else R.drawable.sendmsgicon

    Image(
        painter = painterResource(id = micIcon),
        contentDescription = if (messageText.text.isEmpty()) "Voice Input" else "Send Message",
        modifier = Modifier
            .size(42.dp)
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .pointerInput(messageText.text) {
                detectTapGestures(
                    onPress = {
                        if (messageText.text.isEmpty()) {
                            activity?.let {
                                if (ActivityCompat.checkSelfPermission(
                                        it,
                                        android.Manifest.permission.RECORD_AUDIO
                                    ) != PackageManager.PERMISSION_GRANTED
                                ) {
                                    ActivityCompat.requestPermissions(
                                        it,
                                        arrayOf(android.Manifest.permission.RECORD_AUDIO),
                                        1
                                    )
                                } else {
                                    isRecording.value = true
                                    VoiceRecorderHelper.startRecording(context)
                                    try {
                                        tryAwaitRelease() // wait till release
                                        val audioFile = VoiceRecorderHelper.stopRecording()
                                        audioFile?.let { file ->
                                            onVoiceMessageRecorded(file)
                                        }
                                    } finally {
                                        isRecording.value = false
                                    }
                                }
                            }
                        }
                    }
                )
            }
    )
}
