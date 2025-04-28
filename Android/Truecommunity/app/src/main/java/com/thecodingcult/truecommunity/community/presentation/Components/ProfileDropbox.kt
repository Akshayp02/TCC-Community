package com.thecodingcult.truecommunity.community.presentation.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thecodingcult.truecommunity.community.data.ChatMessage
import com.thecodingcult.truecommunity.core.presentation.ui.theme.TextHeadingColor

/**
 * @param message The chat message containing the image, title, and time information.
 */
@Composable
fun ProfileDropbox(message: ChatMessage) {
    val painter = painterResource(id = message.image)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clickable {

                }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = message.title,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                color = TextHeadingColor
            ),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = message.time + " min ago",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}