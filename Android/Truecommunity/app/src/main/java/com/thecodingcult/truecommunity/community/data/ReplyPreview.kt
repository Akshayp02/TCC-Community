package com.thecodingcult.truecommunity.community.data

data class ReplyPreview(
    val previewText: String,
    val previewType: PreviewType
)

enum class PreviewType {
    TEXT, IMAGE, VIDEO, AUDIO, DOCUMENT, POLL, UNKNOWN
}