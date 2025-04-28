package com.thecodingcult.truecommunity.community.presentation.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thecodingcult.truecommunity.community.data.Message
import com.thecodingcult.truecommunity.core.presentation.ui.theme.AppTypography
import com.thecodingcult.truecommunity.core.presentation.ui.theme.ChatBackgroundColor
import com.thecodingcult.truecommunity.core.presentation.ui.theme.buttonGrayshade3Bg

@Composable
fun ChatBubble(message: Message) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val maxWidth = screenWidth * 0.75f // ✅ Max width constraint
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 2.dp, top = 2.dp, start = 12.dp, end = 12.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = if (message.isSentByUser) Arrangement.End else Arrangement.Start
    ) {
        // FOR RECEIVED MESSAGES (OTHER USERS)


        Column {
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .widthIn(max = maxWidth)
                    .background(
                        if (message.isSentByUser) ChatBackgroundColor else buttonGrayshade3Bg,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Column {

                    // ✅ Message text
                    Text(
                        text = message.text,
                        style = AppTypography.bodyLarge,
                        textAlign = TextAlign.Start,

                        )

                }
            }
        }

        // FOR SENT MESSAGES (CURRENT USER)

    }
}