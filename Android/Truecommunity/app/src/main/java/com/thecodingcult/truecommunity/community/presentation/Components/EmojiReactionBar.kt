package com.thecodingcult.truecommunity.community.presentation.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmojiReactionBar(
    modifier: Modifier = Modifier,
    onEmojiSelected: (String) -> Unit = {}
) {
    val emojis = listOf("❤️", "🔥", "👍", "👎", "😊", "😟", "🤣", "😏", "✋", "🙏") // you can add more!

    Box(
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                spotColor = Color(0xF7C91111),
                ambientColor = Color(0x6B030303)
            )
            .fillMaxWidth()
            .height(40.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(start = 8.dp, end = 8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(emojis) { emoji ->
                val isAddButton = emoji == "+"
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(
                            if (isAddButton) Color(0xFFFF6A33) else Color.Transparent
                        )
                        .clickable { onEmojiSelected(emoji) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = emoji,
                        fontSize = 20.sp,
                        color = if (isAddButton) Color.White else Color(0xFF4E586E)
                    )
                }
            }
        }
    }
}





