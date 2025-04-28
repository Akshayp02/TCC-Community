package com.thecodingcult.truecommunity.community.presentation.Components

/**
# Documentation: InputRow.kt

## Overview
This file contains the implementation of the `InputRow` composable, which is responsible for rendering the input area in a chat interface. It includes features like text input, media options, emoji picker, reply preview, and message scheduling.

## Key Components

### 1. InputRow Composable
- **Purpose**: Provides a user interface for composing and sending messages in a chat.
- **Parameters**:
- `messageText: TextFieldValue`: The current text in the input field.
- `onMessageTextChange: (TextFieldValue) -> Unit`: Callback to handle changes in the input text.
- `onSendMessage: () -> Unit`: Callback triggered when the user sends a message.
- `replyingTo: String?`: The message being replied to, if any.
- `onCancelReply: () -> Unit`: Callback triggered when the user cancels a reply.
- **Features**:
- Text input with placeholder and keyboard actions.
- Reply preview banner for replying to specific messages.
- Media options for uploading images, videos, or files.
- Emoji picker for adding emojis to the message.
- Timer options for scheduling messages.
- Camera options for capturing or selecting media.

### 2. IconButtonWithToggle Composable
- **Purpose**: Renders an icon button with toggle functionality.
- **Parameters**:
- `icon: Int`: The resource ID of the icon to display.
- `contentDescription: String`: Description of the icon for accessibility.
- `toggleState: Boolean`: The current toggle state of the button.
- `onClick: () -> Unit`: Callback triggered when the button is clicked.

### 3. MediaOptionsBox
- **Purpose**: Displays a UI for selecting media files (e.g., images, videos).
- **Usage**: Triggered when the media button is clicked.

### 4. EmojiPicker
- **Purpose**: Displays a UI for selecting emojis.
- **Usage**: Triggered when the emoji button is clicked.

### 5. CaptureOrPickMedia
- **Purpose**: Provides options to capture or pick media from the device.
- **Parameters**:
- `onMediaSelected: (Uri?) -> Unit`: Callback triggered when media is selected.
- `onDismiss: () -> Unit`: Callback triggered when the media picker is dismissed.

### 6. Utility Functions
#### showToastAndLog
- **Purpose**: Displays a toast message and logs the URI of an uploaded image.
- **Parameters**:
- `context: Context`: The application context.
- `uri: Uri`: The URI of the uploaded image.

## Features Summary
- **Text Input**: Allows users to type messages with a placeholder and send action.
- **Reply Preview**: Displays a banner for replying to specific messages.
- **Media Options**: Enables users to upload images, videos, or files.
- **Emoji Picker**: Allows users to add emojis to their messages.
- **Message Scheduling**: Provides options to schedule messages for later.
- **Camera Options**: Allows users to capture or select media directly.

## How to Use
1. **Integrate InputRow**:
- Pass the required parameters (e.g., `messageText`, `onMessageTextChange`, `onSendMessage`) to render the input row.
2. **Handle Media and Emoji Options**:
- Implement callbacks for media selection and emoji addition.
3. **Reply Functionality**:
- Use the `replyingTo` parameter to display the reply preview banner.

## Dependencies
- **Jetpack Compose**: For building the UI components.
- **ActivityResultContracts**: For handling media selection and capture.

 **/
import android.content.Context
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
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.thecodingcult.truecommunity.R
import com.thecodingcult.truecommunity.community.data.MessageType
import com.thecodingcult.truecommunity.community.data.ReplyMessageType
import com.thecodingcult.truecommunity.community.presentation.animations.VoiceRecorderButton
import com.thecodingcult.truecommunity.core.presentation.ui.theme.AppTypography
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun InputRow(
    messageText: TextFieldValue,
    onMessageTextChange: (TextFieldValue) -> Unit,
    onSendMessage: () -> Unit,
    replyingTo: ReplyMessageType?,
    onCancelReply: () -> Unit,
    onVoiceMessageRecorded: (File) -> Unit

) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val activity = context as? android.app.Activity

    val isRecording = rememberSaveable { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { showToastAndLog(context, it) }
    }

    var showMediaOptions by remember { mutableStateOf(false) }
    var showCameraOptions by remember { mutableStateOf(false) }
    var showTimerOptions by remember { mutableStateOf(false) }
    var showEmojiPicker by remember { mutableStateOf(false) }

    val imeHeight = WindowInsets.ime.getBottom(LocalDensity.current)

    LaunchedEffect(imeHeight) {
        if (imeHeight > 0) showEmojiPicker = false
    }

    LaunchedEffect(replyingTo) {
        if (replyingTo != null) {
            delay(100) // Optional: helps ensure focus behavior is smooth
            focusRequester.requestFocus()
            keyboardController?.show()
        }
    }

    fun hideAllOptions() {
        showMediaOptions = false
        showCameraOptions = false
        showTimerOptions = false
        showEmojiPicker = false
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(onTap = { hideAllOptions() })
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .imePadding(),
            verticalArrangement = Arrangement.Bottom
        ) {
            if (showMediaOptions) {
                MediaOptionsBox { showMediaOptions = false }
            }
            if (showTimerOptions) {
                // ScheduleMessageUI { showTimerOptions = false }
                DialogController { showTimerOptions = false }
            }
            if (showCameraOptions) {
                CaptureOrPickMedia(
                    onMediaSelected = { uri -> uri?.let { showToastAndLog(context, it) } },
                    onDismiss = { showCameraOptions = false }
                )
            }

            // Reply banner TODO add onclick of replay message user should gos to the particular massage same as whatsapp

            replyingTo?.let { replyMessage ->
                if (replyMessage.content.isNotBlank()) { // Ensure the reply content is not blank
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFEEEEEE))
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Replying to: ${replyMessage.senderName}",
                                style = AppTypography.bodySmall,
                                color = Color.Gray
                            )
                            when (replyMessage.type) {
                                MessageType.TEXT -> Text(
                                    text = replyMessage.content.take(50), // Preview first 50 characters
                                    style = AppTypography.bodySmall,
                                    color = Color.Black
                                )

                                MessageType.IMAGE -> Text(
                                    text = "Image",
                                    style = AppTypography.bodySmall,
                                    color = Color.Gray
                                )

                                MessageType.VIDEO -> Text(
                                    text = "Video",
                                    style = AppTypography.bodySmall,
                                    color = Color.Gray
                                )

                                MessageType.AUDIO -> Text(
                                    text = "Audio",
                                    style = AppTypography.bodySmall,
                                    color = Color.Gray
                                )

                                else -> Text(
                                    text = "Unsupported message type",
                                    style = AppTypography.bodySmall,
                                    color = Color.Gray
                                )
                            }
                        }
                        Text(
                            text = "Close",
                            color = Color.Red,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .clickable { onCancelReply() } // Trigger the callback to close the reply
                                .padding(8.dp)
                        )
                    }
                }
            }
            // Input row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFFEFEFEF), RoundedCornerShape(15.dp))
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButtonWithToggle(
                        icon = R.drawable.smile,
                        contentDescription = "Emoji",
                        toggleState = showEmojiPicker,
                        onClick = {
                            if (showEmojiPicker) hideAllOptions() else {
                                hideAllOptions()
                                showEmojiPicker = true
                                keyboardController?.hide()
                            }
                        }
                    )

                    BasicTextField(
                        value = messageText,
                        onValueChange = {
                            onMessageTextChange(it)
                            if (showEmojiPicker) showEmojiPicker = false
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                        keyboardActions = KeyboardActions(onSend = {
                            if (messageText.text.isNotBlank()) {
                                coroutineScope.launch { onSendMessage() }
                            }
                        }),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 6.dp)
                            .focusRequester(focusRequester),
                        decorationBox = { innerTextField ->
                            Box(modifier = Modifier.fillMaxWidth()) {
                                if (messageText.text.isEmpty()) {
                                    Text(text = "Write a message...", color = Color.Gray)
                                }
                                innerTextField()
                            }
                        }
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    IconButtonWithToggle(
                        icon = R.drawable.timmer,
                        contentDescription = "Timer",
                        toggleState = showTimerOptions,
                        onClick = {
                            showTimerOptions = !showTimerOptions
                            if (!showTimerOptions) hideAllOptions() else {
                                hideAllOptions(); showTimerOptions = true
                            }
                        }
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    IconButtonWithToggle(
                        icon = R.drawable.media,
                        contentDescription = "Media",
                        toggleState = showMediaOptions,
                        onClick = {
                            showMediaOptions = !showMediaOptions
                            if (!showMediaOptions) hideAllOptions() else {
                                hideAllOptions(); showMediaOptions = true
                            }
                        }
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    IconButtonWithToggle(
                        icon = R.drawable.camera,
                        contentDescription = "Camera",
                        toggleState = showCameraOptions,
                        onClick = {
                            showCameraOptions = !showCameraOptions
                            if (!showCameraOptions) hideAllOptions() else {
                                hideAllOptions(); showCameraOptions = true
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))


                // voice recorder
                VoiceRecorderButton(
                    isRecording = isRecording,
                    messageText = messageText,
                    onVoiceMessageRecorded = onVoiceMessageRecorded
                )


            }

            if (showEmojiPicker) {
                EmojiPicker { selectedEmojis ->
                    val updated = messageText.text + selectedEmojis.joinToString(" ")
                    onMessageTextChange(TextFieldValue(updated))
                }
            }
        }
    }
}

@Composable
fun IconButtonWithToggle(
    icon: Int,
    contentDescription: String,
    toggleState: Boolean,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(id = icon),
        contentDescription = contentDescription,
        modifier = Modifier
            .size(22.dp)
            .pointerInput(toggleState) {
                detectTapGestures(
                    onPress = { onClick() },
                    onDoubleTap = { /* Optional: hide all */ }
                )
            }
    )
}

private fun showToastAndLog(context: Context, uri: Uri) {
    Toast.makeText(context, "Image uploaded", Toast.LENGTH_SHORT).show()
    Log.d("ImageUpload", "Image URI: $uri")
}





