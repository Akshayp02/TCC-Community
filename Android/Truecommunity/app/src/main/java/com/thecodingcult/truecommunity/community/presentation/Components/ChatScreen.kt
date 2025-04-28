package com.thecodingcult.truecommunity.community.presentation.Components
/*
# Documentation: ChatScreen.kt

## Overview
This file implements the `ChatScreen` composable, which serves as the main UI for a group chat interface. It includes features like displaying messages, typing indicators, reply functionality, and an input area for sending messages.

## Key Components

### 1. ChatScreen Composable
- **Purpose**: Renders the entire chat screen, including the message list, typing indicators, and input area.
- **Features**:
  - Displays a list of messages using the `GroupChatBubble` composable.
  - Shows a typing indicator for active users.
  - Allows users to reply to specific messages.
  - Provides an input area for composing and sending messages.

### 2. State Management
- **`messages`**: A `MutableStateList` that holds all the messages in the chat.
- **`messageText`**: A `MutableState` that tracks the current text in the input field.
- **`replyingTo`**: A `MutableState` that holds the text of the message being replied to.
- **`selectedMessages`**: A `MutableStateList` for tracking selected messages (if multi-select is supported).

### 3. Features
#### Message List
- Displays messages in reverse order (newest at the bottom).
- Uses `GroupChatBubble` to render individual messages.
- Shows sender information for the first message or when the sender changes.

#### Typing Indicator
- Displays a list of users currently typing in the chat.

#### Reply Functionality
- Allows users to reply to specific messages.
- Displays a reply banner in the input area when replying.

#### Input Area
- Includes a text field for composing messages.
- Supports sending messages and clearing the input field after sending.
- Displays a reply banner when replying to a message.

### 4. Utility Functions
#### TypingIndicator
- **Purpose**: Displays a list of users currently typing in the chat.
- **Usage**: Rendered at the top of the message list if there are active typing users.

#### GroupChatBubble
- **Purpose**: Renders individual chat messages with support for text, media, and reply previews.
- **Usage**: Used within the `LazyColumn` to display each message.

#### InputRow
- **Purpose**: Provides the input area for composing and sending messages.
- **Usage**: Rendered at the bottom of the screen, with support for reply banners and message sending.

## How to Use
1. **Integrate ChatScreen**:
   - Call the `ChatScreen` composable to render the chat interface.
2. **Customize Messages**:
   - Modify the `messages` list to include your own message data.
3. **Handle Replies**:
   - Use the `replyingTo` state to manage reply functionality.

## Dependencies
- **Jetpack Compose**: For building the UI components.
- **Kotlin Coroutines**: For managing asynchronous tasks (e.g., sending messages).
- **Material3**: For styling and theming.

*/
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.thecodingcult.truecommunity.R
import com.thecodingcult.truecommunity.community.data.GroupMassage
import com.thecodingcult.truecommunity.community.data.ReplyMessageType
import com.thecodingcult.truecommunity.community.models.User
import com.thecodingcult.truecommunity.core.presentation.ui.theme.AppTypography
import kotlinx.coroutines.launch


@Composable
fun ChatScreen() {
    // State to hold all messages
    val messages = remember { mutableStateListOf<GroupMassage>() }
    // State to hold the content of the text field
    var messageText by remember { mutableStateOf(TextFieldValue()) }
    // State to decide whether to show the emoji keyboard (if applicable)
    var showEmojiKeyboard by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    // Selected messages state (if you support message selection)
    val selectedMessages = remember { mutableStateListOf<GroupMassage>() }
    // This holds the message being replied to
    var replyingTo by remember { mutableStateOf<ReplyMessageType?>(null) }

    // Initialize some dummy messages.
    LaunchedEffect(Unit) {
        messages.addAll(
            listOf(
                GroupMassage(
                    text = "Let's plan the trip!",
                    senderName = "Raj Sir",
                    isSentByUser = false
                ),
                GroupMassage(
                    text = "",
                    senderName = "Raj Sir",
                    isSentByUser = false,
                    isPoll = true
                ),
                GroupMassage(
                    text = "",
                    senderName = "Vishal Sir",
                    isSentByUser = false,
                    videoUrl = Uri.parse("android.resource://com.thecodingcult.truecommunity/" + R.raw.sample_video)
                ),
                GroupMassage(
                    text = "",
                    senderName = "Akshay",
                    isSentByUser = true,
                    audioUrl = Uri.parse("android.resource://com.thecodingcult.truecommunity/" + R.raw.audio)
                )
            )
        )
    }

    // Sample typing users for an indicator
    val typingUsers = listOf(
        User("1", "Pavan", R.drawable.otheruserprofile),
        User("2", "Vishal", R.drawable.otheruserprofile),
        User("3", "Dear", R.drawable.businessprofile),
        User("4", "Priya", R.drawable.myimg)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {
        LazyColumn(
            reverseLayout = true,
            modifier = Modifier.weight(1f)
        ) {
            // Typing indicator at the top
            item {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp, vertical = 4.dp)
                ) {
                    if (typingUsers.isNotEmpty()) {
                        TypingIndicator(typingUsers)
                    }
                }
            }

            // Display each message using your GroupChatBubble composable
            items(messages.size) { index ->
                val message = messages[index]
                // Show sender info if this message is the first or if the previous message came from a different sender
                val showSenderInfo =
                    index == 0 || messages[index - 1].senderName != message.senderName

                GroupChatBubble(
                    message = message,
                    showSenderInfo = showSenderInfo,
                    isLastMessageFromUser = index == 0,
                    selectedMessages = selectedMessages,
                    onReply = { selectedMessage ->
                        Log.d("ChatScreen", "Replying to message: ${selectedMessage.text}")
                        replyingTo = ReplyMessageType(
                            content = selectedMessage.text,
                            senderName = selectedMessage.senderName,
                            type = selectedMessage.type
                        )
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            // "Today" label at the bottom
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Today",
                        color = MaterialTheme.colorScheme.primary,
                        style = AppTypography.titleMedium
                    )
                }
            }
        }

        // Input area with reply banner (if replyingTo is not null)
        Box(
            modifier = Modifier
                .padding(horizontal = 6.dp, vertical = 4.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .imePadding()
        ) {
            InputRow(
                messageText = messageText,
                onMessageTextChange = { messageText = it },
                replyingTo = replyingTo,
                onCancelReply = {
                    Log.d("ChatScreen", "Reply canceled")
                    replyingTo = null
                },
                onSendMessage = {
                    if (messageText.text.isNotBlank()) {
                        coroutineScope.launch {
                            // Add the new message to the list
                            messages.add(
                                0,
                                GroupMassage(
                                    text = messageText.text,
                                    senderName = "Akshay",
                                    isSentByUser = true,
                                    replyMessageText = replyingTo?.content,
                                    replyMessageType = replyingTo?.type
                                )
                            )

                            // Clear text and reply context after sending
                            messageText = TextFieldValue()
                            replyingTo = null
                        }
                    }
                },
                onVoiceMessageRecorded = { audioFile ->
                    coroutineScope.launch {
                        Log.d("ChatScreen", "Voice message recorded: ${audioFile.absolutePath}")
                        messages.add(
                            0,
                            GroupMassage(
                                text = "",
                                senderName = "Akshay",
                                isSentByUser = true,
                                audioUrl = Uri.fromFile(audioFile),
                                replyMessageText = replyingTo?.content,
                                replyMessageType = replyingTo?.type
                            )
                        )
                        replyingTo = null
                    }
                }
            )
        }
    }
}







/**
//@Composable
//fun ChatScreen() {
//    // State to hold all messages
//    val messages = remember { mutableStateListOf<GroupMassage>() }
//    // State to hold the content of the text field
//    var messageText by remember { mutableStateOf(TextFieldValue()) }
//    // State to decide whether to show the emoji keyboard (if applicable)
//    var showEmojiKeyboard by remember { mutableStateOf(false) }
//    val coroutineScope = rememberCoroutineScope()
//    // Selected messages state (if you support message selection)
//    val selectedMessages = remember { mutableStateListOf<String>() }
//    // This holds the text of the message you are replying to
//    var replyingTo by remember { mutableStateOf<String?>(null) }
//
//    // Initialize some dummy messages.
//    // These messages don't use any reply information.
//    LaunchedEffect(Unit) {
//        messages.addAll(
//            listOf(
//                GroupMassage(
//                    text = "Let's plan the trip!",
//                    senderName = "Raj Sir",
//                    isSentByUser = false
//                ),
//                GroupMassage(
//                    text = "",
//                    senderName = "Raj Sir",
//                    isSentByUser = false,
//                    isPoll = true
//                ),
//                GroupMassage(
//                    text = "",
//                    senderName = "Vishal Sir",
//                    isSentByUser = false,
//                    videoUrl = Uri.parse("android.resource://com.thecodingcult.truecommunity/" + R.raw.sample_video)
//                ),
//                GroupMassage(
//                    text = "",
//                    senderName = "Akshay",
//                    isSentByUser = true,
//                    audioUrl = Uri.parse("android.resource://com.thecodingcult.truecommunity/" + R.raw.audio)
//                )
//            )
//        )
//    }
//
//    // Sample typing users for an indicator
//    val typingUsers = listOf(
//        User("1", "Pavan", R.drawable.otheruserprofile),
//        User("2", "Vishal", R.drawable.otheruserprofile),
//        User("3", "Dear", R.drawable.businessprofile),
//        User("4", "Priya", R.drawable.myimg)
//    )
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(top = 8.dp)
//    ) {
//        LazyColumn(
//            reverseLayout = true,
//            modifier = Modifier.weight(1f)
//        ) {
//            // Typing indicator at the top
//            item {
//                Box(
//                    Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 6.dp, vertical = 4.dp)
//                ) {
//                    if (typingUsers.isNotEmpty()) {
//                        TypingIndicator(typingUsers)
//                    }
//                }
//            }
//
//            // Display each message using your GroupChatBubble composable
//            items(messages.size) { index ->
//                val message = messages[index]
//                // Show sender info if this message is the first or if the previous message came from a different sender
//                val showSenderInfo =
//                    index == 0 || messages[index - 1].senderName != message.senderName
//
//                GroupChatBubble(
//                    message = message,
//                    showSenderInfo = showSenderInfo,
//                    isLastMessageFromUser = index == 0,
//                    selectedMessages = selectedMessages,
//                    onReply = { selectedMessage ->
//                        Log.d("ChatScreen", "Replying to message: ${selectedMessage.text}")
//                        replyingTo = selectedMessage?.senderName
//                    }
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//            }
//
//            // "Today" label at the bottom
//            item {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(10.dp),
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Text(
//                        text = "Today",
//                        color = MaterialTheme.colorScheme.primary,
//                        style = AppTypography.titleMedium
//                    )
//                }
//            }
//        }
//
//        // Input area with reply banner (if replyingTo is not null)
//        Box(
//            modifier = Modifier
//                .padding(horizontal = 6.dp, vertical = 4.dp)
//                .fillMaxWidth()
//                .wrapContentHeight()
//                .imePadding()
//        ) {
//            InputRow(
//                messageText = messageText,
//                onMessageTextChange = { messageText = it },
//                replyingTo = replyingTo,
//                onCancelReply = {
//                    Log.d("ChatScreen", "Reply canceled")
//                    replyingTo = null
//                },
////                onSendMessage = {
////                    if (messageText.text.isNotBlank()) {
////                        coroutineScope.launch {
////                            // Log the outgoing message along with the current reply context
////                            Log.d(
////                                "ChatScreen",
////                                "Sending message: ${messageText.text} (replyingTo: $replyingTo)"
////                            )
////                            messages.add(
////                                0,
////                                GroupMassage(
////                                    text = messageText.text,
////                                    senderName = "Akshay",
////                                    isSentByUser = true,
////                                    replyMessageText = replyingTo
////                                )
////                            )
////                            // Clear text and reply context after sending
////                            messageText = TextFieldValue()
////                            replyingTo = null
////                        }
////                    }
////                },
//                onVoiceMessageRecorded = { audioFile ->
//                    coroutineScope.launch {
//                        Log.d("ChatScreen", "Voice message recorded: ${audioFile.absolutePath}")
//                        messages.add(
//                            0,
//                            GroupMassage(
//                                text = "",
//                                senderName = "Akshay",
//                                isSentByUser = true,
//                                audioUrl = Uri.fromFile(audioFile),
//                                replyMessageText = replyingTo
//                            )
//                        )
//                        replyingTo = null
//                    }
//                }
//
//            )
//        }
//    }
//}


@Preview(showBackground = true)
@Composable
fun PreviewChatScreen() {
ChatScreen()
}

 **/