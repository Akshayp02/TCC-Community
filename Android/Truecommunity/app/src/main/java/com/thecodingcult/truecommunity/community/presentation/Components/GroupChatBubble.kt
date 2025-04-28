package com.thecodingcult.truecommunity.community.presentation.Components
/**
# Documentation: GroupChatBubble.kt

## Overview
This file contains the implementation of the `GroupChatBubble` composable, which is responsible for rendering individual chat bubbles in a group chat interface. It supports various message types (text, images, videos, audio, polls) and includes features like swipe gestures, emoji reactions, and reply previews.

## Key Components

### 1. GroupChatBubble Composable
- **Purpose**: Displays a single chat bubble with support for text, media, and interactive features.
- **Parameters**:
  - `message: GroupMassage`: The message data to display.
  - `showSenderInfo: Boolean`: Whether to show the sender's name (for received messages).
  - `isLastMessageFromUser: Boolean`: Indicates if this is the last message sent by the user.
  - `selectedMessages: SnapshotStateList<String>`: A list of selected messages for multi-select actions.
  - `onReply: (GroupMassage) -> Unit`: Callback triggered when the user swipes to reply to a message.
- **Features**:
  - Swipe gestures for replying to messages.
  - Double-tap for emoji reactions.
  - Long press to show an emoji reaction bar.
  - Reply preview for replied messages.
  - Media support for images, videos, and audio.
  - Timestamp and message status display.

### 2. ImageGrid Composable
- **Purpose**: Displays a grid of images for messages containing multiple images.
- **Parameters**:
  - `images: List<Uri>`: List of image URIs to display.
  - `onImageClick: (Uri) -> Unit`: Callback triggered when an image is clicked.
- **Features**:
  - Displays up to 3 images in a grid layout.
  - Shows an overlay with the count of remaining images if there are more than 3.

### 3. ImageWithLoader Composable
- **Purpose**: Displays an image with a loading indicator while the image is being fetched.
- **Parameters**:
  - `imageUri: Uri`: The URI of the image to display.
  - `onClick: () -> Unit`: Callback triggered when the image is clicked.
- **Features**:
  - Circular progress indicator during image loading.
  - Handles loading, success, and error states.

### 4. EmojiReactionBar
- **Purpose**: Displays a bar of emoji reactions when a message is long-pressed.
- **Usage**: Triggered within the `GroupChatBubble` composable when `showEmojiBar` is true.

### 5. FullScreenImage
- **Purpose**: Displays a full-screen image viewer for viewing images in detail.
- **Usage**: Triggered when an image in the `ImageGrid` is clicked.

## Utility Functions

### getTickIcon
- **Purpose**: Returns the appropriate tick icon resource based on the message status.
- **Parameters**:
  - `status: MessageStatus`: The status of the message (e.g., SENDING, DELIVERED, READ).
- **Returns**: The drawable resource ID for the tick icon.

### showToastAndLog
- **Purpose**: Displays a toast message and logs the URI of an uploaded image.
- **Parameters**:
  - `context: Context`: The application context.
  - `uri: Uri`: The URI of the uploaded image.

## Enum Classes

### MessageStatus
- Represents the status of a message.
- Values:
  - `SENDING`
  - `DELIVERED`
  - `READ`

## Features Summary
- **Message Types**: Supports text, images, videos, audio, and polls.
- **Gestures**: Swipe to reply, double-tap for reactions, long-press for emoji bar.
- **Media Handling**: Displays images in a grid, supports video and audio playback.
- **Reply Preview**: Shows a preview of the replied message.
- **Interactive UI**: Includes emoji reactions and full-screen image viewing.

## How to Use
1. **Integrate GroupChatBubble**:
   - Pass the `GroupMassage` object and other required parameters to render a chat bubble.
2. **Handle Gestures**:
   - Implement the `onReply` callback to handle swipe-to-reply actions.
3. **Media Support**:
   - Ensure media URIs (images, videos, audio) are properly passed to the `GroupMassage` object.

## Dependencies
- **Coil**: Used for image loading (`AsyncImage`).
- **Jetpack Compose**: For building the UI components.
**/
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.thecodingcult.truecommunity.R
import com.thecodingcult.truecommunity.community.data.GroupMassage
import com.thecodingcult.truecommunity.community.data.MessageType
import com.thecodingcult.truecommunity.community.data.getReplyMessageContent
import com.thecodingcult.truecommunity.core.presentation.ui.theme.AppTypography
import com.thecodingcult.truecommunity.core.presentation.ui.theme.ChatBackgroundColor
import com.thecodingcult.truecommunity.core.presentation.ui.theme.buttonGrayshade3Bg
import com.thecodingcult.truecommunity.core.presentation.ui.theme.colorSecondary
import kotlin.math.roundToInt



@Composable
fun GroupChatBubble(
    message: GroupMassage,
    showSenderInfo: Boolean,
    isLastMessageFromUser: Boolean,
    selectedMessages: SnapshotStateList<GroupMassage>,
    onReply: (GroupMassage) -> Unit,
) {
    var selectedImage by remember { mutableStateOf<Uri?>(null) }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val maxWidth = screenWidth * 0.75f

    var showEmojiBar by remember { mutableStateOf(false) }
    var liked by remember { mutableStateOf(false) }
    var selectedEmoji by remember { mutableStateOf<String?>(null) }

    val replyContent = getReplyMessageContent(message)
    val swipeThreshold = 100f
    var offsetX by remember { mutableStateOf(0f) }

    val gestureModifier = Modifier
        .offset { IntOffset(offsetX.roundToInt(), 0) }
        .pointerInput(Unit) {
            detectHorizontalDragGestures(
                onHorizontalDrag = { _, dragAmount ->
                    offsetX += dragAmount
                },
                onDragEnd = {
                    if (message.isSentByUser && offsetX < -swipeThreshold) {
                        onReply(message)
                    } else if (!message.isSentByUser && offsetX > swipeThreshold) {
                        onReply(message)
                    }
                    offsetX = 0f
                }
            )
        }
        .then(
            Modifier.pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        liked = !liked
                        selectedEmoji = if (liked) "❤️" else null
                    },
                    onLongPress = {
                        showEmojiBar = true
                    }
                )
            }
        )

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 2.dp, top = 2.dp, start = 12.dp, end = 12.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = if (message.isSentByUser) Arrangement.End else Arrangement.Start
        ) {
            Column {
                Box(
                    modifier = gestureModifier
                        .wrapContentWidth()
                        .widthIn(max = maxWidth)
                        .background(
                            if (message.isSentByUser) ChatBackgroundColor else buttonGrayshade3Bg,
                            shape = RoundedCornerShape(
                                topStart = if (!message.isSentByUser) 0.dp else 20.dp,
                                topEnd = 20.dp,
                                bottomEnd = if (message.isSentByUser) 0.dp else 20.dp,
                                bottomStart = 20.dp
                            )
                        )
                        .padding(horizontal = 14.dp, vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        // ✅ Sender Name (for received messages)
                        if (!message.isSentByUser && showSenderInfo) {
                            Text(
                                text = message.senderName,
                                color = colorSecondary,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                        }

                        // ✅ Reply Preview Banner TODO : for next version on click any chat user should scroll to the reply message


                        replyContent?.let { (replyText, replySenderName) ->
                            Box(
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .border(
                                        1.dp,
                                        androidx.compose.material3.MaterialTheme.colorScheme.primary,
                                        RoundedCornerShape(8.dp)
                                    )
                                    .clip(RoundedCornerShape(8.dp))
                                    .padding(8.dp)
                            ) {
                                Column {
                                    Text(
                                        text = replySenderName,
                                        style = AppTypography.titleMedium,
                                        fontSize = 12.sp,
                                        maxLines = 1
                                    )
                                    Row {
                                        when (message.replyMessageType) {
                                            MessageType.TEXT -> {
                                                Text(
                                                    text = replyText.take(50),
                                                    style = AppTypography.bodySmall,
                                                    color = Color.Black,
                                                    maxLines = 2
                                                )
                                            }

                                            MessageType.IMAGE -> {
                                                Image(
                                                    painter = painterResource(id = R.drawable.image_24), // Replace with your image resource
                                                    contentDescription = "Image Icon",
                                                    modifier = Modifier.size(20.dp)
                                                )
                                            }

                                            MessageType.VIDEO -> {
                                                Image(
                                                    painter = painterResource(id = R.drawable.video), // Replace with your video icon resource
                                                    contentDescription = "Video Icon",
                                                    modifier = Modifier.size(20.dp)
                                                )
                                            }

                                            MessageType.AUDIO -> {
                                                Image(
                                                    painter = painterResource(id = R.drawable.audio), // Replace with your audio icon resource
                                                    contentDescription = "Audio Icon",
                                                    modifier = Modifier.size(20.dp)
                                                )
                                            }

                                            MessageType.POLL -> {
                                                Image(
                                                    painter = painterResource(id = R.drawable.poll), // Replace with your audio icon resource
                                                    contentDescription = "Audio Icon",
                                                    modifier = Modifier.size(20.dp)
                                                )
                                            }

                                            else -> {
                                                // Default case if needed
                                                Image(
                                                    painter = painterResource(id = R.drawable.documents),
                                                    contentDescription = "Audio Icon",
                                                    modifier = Modifier.size(20.dp)
                                                )
                                            }
                                        }
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = replyText.take(50),
                                            style = AppTypography.bodySmall,
                                            color = Color.Black,
                                            maxLines = 2
                                        )
                                    }
                                }
                            }
                            //Spacer(modifier = Modifier.height(4.dp))
                        }

//                        message.replyMessageText?.let {
//                            Box(
//                                modifier = Modifier
//                                    .wrapContentWidth()
//                                    .height(25.dp)
//                                    .border(
//                                        1.dp,
//                                        androidx.compose.material3.MaterialTheme.colorScheme.primary,
//                                        RoundedCornerShape(4.dp)
//                                    )
//                                    .clip(RoundedCornerShape(4.dp))
//                                    .padding(8.dp)
//                            ) {
//                                Text(
//                                    text = "Replying to ${message.replySenderName}",
//                                    style = AppTypography.bodySmall,
//                                    color = Color.Black,
//                                    maxLines = 1
//                                )
//                                Row() {
//
//                                    ImageWithLoader(
//                                        imageUri = message.replyImageUrl,
//                                        onClick = {}
//                                    )
//
//                                    Text(
//                                        text = "${it.take(50)}",
//                                        style =  AppTypography.bodySmall,
//                                        color = Color.Black,
//                                        maxLines = 2
//                                    )
//                                }
//                            }
//                            Spacer(modifier = Modifier.height(4.dp))
//                        }

                        // ✅ Message Content
                        when {
                            message.isPoll -> PollScreen(message.senderName)
                            message.imageUrls.isNotEmpty() -> ImageGrid(images = message.imageUrls) {
                                selectedImage = it
                            }

                            message.videoUrl != null -> {
                                val videoUri = Uri.parse(message.videoUrl.toString())
                                VideoPlayer(videoUri)
                            }

                            message.audioUrl != null -> AudioPlayer(message.audioUrl)
                            message.text.isNotEmpty() -> {
                                Text(
                                    text = message.text,
                                    fontSize = 16.sp,
                                    modifier = Modifier.wrapContentWidth(),
                                    textAlign = TextAlign.Start,
                                )
                            }
                        }

                        // ✅ Timestamp + Tick
                        Row(
                            modifier = Modifier
                                .align(Alignment.End)
                                .padding(top = 4.dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "16:02", // Replace with actual time
                                fontSize = 10.sp,
                                color = Color.Gray,
                                modifier = Modifier.padding(end = 1.dp)
                            )

                            if (message.isSentByUser) {
                                Image(
                                    painter = painterResource(id = getTickIcon(MessageStatus.DELIVERED)),
                                    contentDescription = "Message Status",
                                    modifier = Modifier.size(12.dp)
                                )
                            }
                        }

                        // ✅ Emoji Reaction Display
                        Box(
                            modifier = Modifier.offset(y = 10.dp),
                            contentAlignment = Alignment.BottomStart
                        ) {
                            if (selectedEmoji != null) {
                                Text(
                                    text = selectedEmoji!!,
                                    fontSize = 16.sp,
                                    modifier = Modifier
                                        .background(Color.LightGray, CircleShape)
                                        .padding(horizontal = 8.dp, vertical = 4.dp),
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }

                    // ✅ Emoji Bar for Long Press
                    if (showEmojiBar) {
                        EmojiReactionBar(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .offset(y = (-10).dp)
                        ) {
                            selectedEmoji = if (selectedEmoji == it) null else it
                            liked = selectedEmoji != null
                            showEmojiBar = false
                        }
                    }
                }
            }
        }

        // ✅ Fullscreen Image Viewer
        selectedImage?.let {
            FullScreenImage(
                imageUris = message.imageUrls,
                initialIndex = message.imageUrls.indexOf(it),
                onDismiss = { selectedImage = null }
            )
        }
    }
}

@Composable
fun ImageGrid(
    images: List<Uri>,
    onImageClick: (Uri) -> Unit
) {
    val visibleImages = images.take(3)
    val remainingCount = images.size - 3

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        // First large image on the left
        if (visibleImages.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(4.dp)
                    .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                    //.border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
                    .clickable { onImageClick(visibleImages[0]) }
            ) {
                Image(
                    painter = rememberAsyncImagePainter(visibleImages[0]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        }

        // Right column with top and bottom images
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            for (i in 1..2) {
                if (visibleImages.size > i) {
                    val showOverlay = (i == 2 && remainingCount > 0)
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .width(85.dp)
                            .padding(4.dp)
                            .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                            .clickable { onImageClick(visibleImages[i]) }
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(visibleImages[i]),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(8.dp))
                                .graphicsLayer {
                                    alpha = if (showOverlay) 0.6f else 1f
                                }
                        )

                        if (showOverlay) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black.copy(alpha = 0.4f))
                                    .clip(RoundedCornerShape(8.dp))
                            ) {
                                Text(
                                    text = "+$remainingCount",
                                    color = Color.White,
                                    fontSize = 26.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@DrawableRes
fun getTickIcon(status: MessageStatus): Int {
    return when (status) {
        MessageStatus.SENDING -> R.drawable.singletick
        MessageStatus.DELIVERED -> R.drawable.doubletick
        MessageStatus.READ -> R.drawable.doubletick
    }
}

enum class MessageStatus {
    SENDING, DELIVERED, READ
}

@Composable
fun ImageWithLoader(imageUri: Uri, onClick: () -> Unit) {
    var isLoading by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(4.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUri)
                .crossfade(true)
                .build(),
            contentDescription = "Chat Image",
            modifier = Modifier
                .size(100.dp)
                .clickable { onClick() },
            contentScale = ContentScale.Crop,
            onLoading = { isLoading = true },
            onSuccess = { isLoading = false },
            onError = { isLoading = false }
        )

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
    }
}


