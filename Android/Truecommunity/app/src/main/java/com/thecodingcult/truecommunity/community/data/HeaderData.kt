package com.thecodingcult.truecommunity.community.data

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class HeaderData(
    val painter: Painter,
    val title: String,
    val groupInfo: String,
    val imageLast: ImageVector
)