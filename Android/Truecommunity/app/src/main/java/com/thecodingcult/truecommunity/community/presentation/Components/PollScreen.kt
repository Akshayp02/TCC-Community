package com.thecodingcult.truecommunity.community.presentation.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thecodingcult.truecommunity.R
import com.thecodingcult.truecommunity.core.presentation.ui.theme.AppTypography
import com.thecodingcult.truecommunity.core.presentation.ui.theme.colorSecondary

// TODO: Implement PollScreen composable here with functions PollScreen and PollOptionRow

@Composable
fun PollScreen(title : String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
           .padding(3.dp)

    ) {
        // Main Column
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Creator name (e.g., "Mike Mazowski")
            Text(
                text = title,
                color = colorSecondary, // Or any orange color
                style = AppTypography.titleMedium,
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Poll question (e.g., "Should we welcome Ashish To our community?")
            Text(
                text = "Should we welcome Ashish\nTo our community?",
                style = AppTypography.bodyLarge,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Option 1 Row: Circle + "Yes" + progress + Avatars + count
            PollOptionRow(
                optionText = "Yes",
                voteCount = 3,
                progressFraction = 0.6f,  // 60% progress example
                avatarResourceIds = listOf(
                    R.drawable.myimg,
                    R.drawable.otheruserprofile
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Option 2 Row: Circle + "No" + progress + Avatars + count
            PollOptionRow(
                optionText = "No",
                voteCount = 2,
                progressFraction = 0.4f,  // 40% progress example
                avatarResourceIds = listOf(
                    R.drawable.myimg,
                    R.drawable.otheruserprofile
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // "View Votes" in orange
            Text(
                text = "View Votes",
                color = colorSecondary, // Same orange color
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        // Handle "View Votes" click here
                    },
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun PollOptionRow(
    optionText: String,
    voteCount: Int,
    progressFraction: Float,
    avatarResourceIds: List<Int>
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Circle or RadioButton
        Box(
            modifier = Modifier
                .size(20.dp)
                .border(2.dp, Color.Gray, CircleShape)
                .clickable {
                    // Handle selecting this option
                }
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Option text (e.g., "Yes" or "No")
        Text(
            text = optionText,
            color = Color(0xFF1C1C1E),
            fontSize = 16.sp,
            modifier = Modifier.weight(1f) // so the progress bar can expand in the row
        )

        // Horizontal progress bar
        Box(
            modifier = Modifier
                .height(4.dp)
                .width(100.dp) // fixed width for the sample
                .clip(RoundedCornerShape(2.dp))
                .background(Color.LightGray)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(progressFraction)
                    .background(colorSecondary)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Small avatars
        Row {
            avatarResourceIds.forEach { resId ->
                Image(
                    painter = painterResource(id = resId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.White, CircleShape)
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Vote count
        Text(
            text = voteCount.toString(),
            color = Color(0xFF1C1C1E),
            fontSize = 14.sp
        )
    }
}
