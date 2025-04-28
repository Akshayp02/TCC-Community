package com.thecodingcult.truecommunity.community.data

import android.net.Uri


data class GroupMassage(
    val text: String = "",
    val senderName: String,
    val isSentByUser: Boolean,
    val isPoll: Boolean = false,
    val imageUrls: List<Uri> = emptyList(),
    val videoUrl: Uri? = null,
    val audioUrl: Uri? = null,
    val documentUrl: Uri? = null,
    val replyMessageText: String? = null,
    val replyMessageType: MessageType? = null,
    val type: MessageType = MessageType.TEXT
)

fun getReplyMessageContent(message: GroupMassage): Pair<String, String>? {
    return if (message.replyMessageType == MessageType.TEXT &&
        message.replyMessageText != null &&
        message.replyMessageText.isNotEmpty()
    ) {
        Pair(message.replyMessageText, message.senderName)
    } else {
        null
    }
}