package com.thecodingcult.truecommunity.community.presentation.Components


import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.thecodingcult.truecommunity.R
import kotlinx.coroutines.launch

@Composable
fun Supportinputfield(
    messageText: TextFieldValue,
    onMessageTextChange: (TextFieldValue) -> Unit,
    onSendMessage: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var isClicked by remember { mutableStateOf(false) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            Toast.makeText(context, "Image uploaded", Toast.LENGTH_SHORT).show()
            Log.d("ImageUpload", "Image URI: $uri")
        }
    }

    var showMediaOptions by remember { mutableStateOf(false) }
    var showTimerOptions by remember { mutableStateOf(false) }
    var showEmojiPicker by remember { mutableStateOf(false) } // Emoji Picker Toggle
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom
    ) {
        // Show Media Options & Timer Options Above Input Field
        if (showMediaOptions) {
            MediaOptionsBox { showMediaOptions = false }
        }
        if (showTimerOptions) {
            ScheduleMessageUI { showTimerOptions = false }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .background(Color.Transparent)
                .imePadding(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFFEFEFEF), RoundedCornerShape(15.dp))
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(2.dp))
                Image(
                    painter = painterResource(id = R.drawable.smile),
                    contentDescription = "open_emoji",
                    modifier = Modifier
                        .size(22.dp)
                        .clickable {
                            showEmojiPicker = !showEmojiPicker
                            if (showEmojiPicker) {
                                keyboardController?.hide()
                            }
                        }
                )
                BasicTextField(
                    value = messageText,
                    onValueChange = {
                        onMessageTextChange(it)
                        if (showEmojiPicker) {
                            showEmojiPicker = false
                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                    keyboardActions = KeyboardActions(onSend = {
                        if (messageText.text.isNotBlank()) {
                            coroutineScope.launch {
                                onSendMessage()
                            }
                        }
                    }),
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                    decorationBox = { innerTextField ->
                        Box(modifier = Modifier.fillMaxWidth()) {
                            if (messageText.text.isEmpty()) {
                                Text(text = "Write a message...", color = Color.Gray)
                            }
                            innerTextField()
                        }
                    }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Image(
                    painter = painterResource(id = R.drawable.media),
                    contentDescription = "media",
                    modifier = Modifier
                        .size(20.dp)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = {
                                    if (showMediaOptions) {
                                        showMediaOptions = false
                                    } else {
                                        showMediaOptions = true
                                    }
                                },
                                onDoubleTap = {
                                    if (showMediaOptions) {
                                        showMediaOptions = false
                                    }
                                }
                            )
                        }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.camera),
                    contentDescription = "camera",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            launcher.launch("image/*")
                            isClicked = !isClicked
                        }
                        .background(if (isClicked) MaterialTheme.colorScheme.primary else Color.Transparent)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = if (messageText.text.isEmpty()) R.drawable.mic else R.drawable.sendmsgicon),
                contentDescription = if (messageText.text.isEmpty()) "Voice Input" else "Send Message",
                modifier = Modifier
                    .size(42.dp)
                    .clickable {
                        if (messageText.text.isNotBlank()) {
                            onSendMessage()
                        }
                    }
            )
        }

        // Show Emoji Picker Below Input Field (Keyboard Sized)
        if (showEmojiPicker) {
            EmojiPicker(
                onEmojisSelected = { selectedEmojis ->
                    val updatedMessage = messageText.text + selectedEmojis.joinToString(" ")
                    onMessageTextChange(TextFieldValue(updatedMessage))
                }
            )
        }
    }
}


