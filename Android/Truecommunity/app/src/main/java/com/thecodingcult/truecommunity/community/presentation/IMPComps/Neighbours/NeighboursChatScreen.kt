package com.thecodingcult.truecommunity.community.presentation.IMPComps.Neighbours

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
fun NeighboursChatScreen() {
    val configuration = LocalConfiguration.current
    val messages = remember { mutableStateListOf<GroupMassage>() }
    var messageText by remember { mutableStateOf(TextFieldValue()) }
    var showEmojiKeyboard by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
//    val selectedMessages = remember { mutableStateListOf<String>() }
//    var replyingTo by remember { mutableStateOf<String?>(null) } // Updated type

    val selectedMessages = remember { mutableStateListOf<GroupMassage>() }
    // This holds the message being replied to
    var replyingTo by remember { mutableStateOf<ReplyMessageType?>(null) }

    // Add dummy messages
    LaunchedEffect(Unit) {
        messages.add(
            GroupMassage(
                text = "next Society meeting is on 10th of this month",
                senderName = "Rajpal Yadav",
                isSentByUser = false,
                imageUrls = listOf(
                    Uri.parse("https://images.unsplash.com/photo-1497032628192-86f99bcd76bc?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%253D%253D"),
                    Uri.parse("https://images.unsplash.com/photo-1522071820081-009f0129c71c?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%253D%253D"),
                    Uri.parse("https://images.unsplash.com/photo-1552581234-26160f608093?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%253D%253D"),
                    Uri.parse("https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%253D%253D"),
                )
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
    }
}