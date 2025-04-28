package com.thecodingcult.truecommunity.community.data

import androidx.compose.ui.graphics.painter.Painter

data class ChatMessage(
    val image: Int,
    val title: String,
    val description: String,
    val time: String,
    val messageNumber: Int
)
