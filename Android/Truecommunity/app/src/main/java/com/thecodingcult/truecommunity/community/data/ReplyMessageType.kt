package com.thecodingcult.truecommunity.community.data

enum class MessageType {
    TEXT,
    IMAGE,
    VIDEO,
    AUDIO,
    DOCUMENT,
    POLL,
    UNSUPPORTED
}
data class ReplyMessageType(
    val content: String,
    val senderName: String,
    val type: MessageType
)