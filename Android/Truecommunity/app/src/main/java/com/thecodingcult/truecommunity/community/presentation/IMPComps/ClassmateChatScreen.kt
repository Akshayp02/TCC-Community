package com.thecodingcult.truecommunity.community.presentation.IMPComps

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.thecodingcult.truecommunity.community.data.GroupMassage
import com.thecodingcult.truecommunity.community.data.ReplyMessageType
import com.thecodingcult.truecommunity.community.presentation.Components.GroupChatBubble
import com.thecodingcult.truecommunity.community.presentation.Components.InputRow
import kotlinx.coroutines.launch


@Composable
fun ClassmateChatScreen() {
    val configuration = LocalConfiguration.current
    val messages = remember { mutableStateListOf<GroupMassage>() }
    var messageText by remember { mutableStateOf(TextFieldValue()) }
    var showEmojiKeyboard by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val selectedMessages = remember { mutableStateListOf<GroupMassage>() }
    var replyingTo by remember { mutableStateOf<ReplyMessageType?>(null) }

    // Add dummy messages
    LaunchedEffect(Unit) {
        messages.add(
            GroupMassage(
                text = "These are some beautiful travel destinations! 🌍✨",
                senderName = "Raj Sir",
                isSentByUser = false,
                imageUrls = listOf(
                    Uri.parse("https://images.unsplash.com/photo-1512343879784-a960bf40e7f2?q=80&w=1374&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
                    Uri.parse("https://images.unsplash.com/photo-1558960214-f4283a743867?q=80&w=1474&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
                    Uri.parse("https://images.unsplash.com/photo-1625505826533-5c80aca7d157?q=80&w=1469&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
                    Uri.parse("https://plus.unsplash.com/premium_photo-1697729733902-f8c92710db07?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
                )
            )
        )

        messages.add(
            GroupMassage(
                text = "what you think gys where to go??",
                senderName = "Raj Sir",
                isSentByUser = true,
                isPoll = false
            )
        )

        messages.add(
            GroupMassage(
                text = "",
                senderName = "Raj Sir",
                isSentByUser = false,
                isPoll = true
            )
        )
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            reverseLayout = true, modifier = Modifier.weight(1f)
        ) {
            items(messages.size) { index ->
                val message = messages[index]
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
        }

        // Parent Row to hold input Row and icon
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

        if (showEmojiKeyboard) {
            Text("Emoji Keyboard")
        }
    }
}