package com.thecodingcult.truecommunity.community.presentation.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.thecodingcult.truecommunity.community.data.ChatMessage
import com.thecodingcult.truecommunity.core.presentation.ui.theme.AppTypography

@Composable
fun MassageFormatRow(message: ChatMessage, modifier: Modifier = Modifier) {
    val painter = painterResource(id = message.image)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = message.title, style = MaterialTheme.typography.bodyLarge)
            Text(text = message.description, style = AppTypography.bodySmall)
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(text = message.time, style = AppTypography.bodySmall)
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 12.dp))
                    .border(
                        width = 0.5.dp,
                        color = Color(0x4D4F5E7B),
                        shape = RoundedCornerShape(size = 12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${message.messageNumber} ",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}