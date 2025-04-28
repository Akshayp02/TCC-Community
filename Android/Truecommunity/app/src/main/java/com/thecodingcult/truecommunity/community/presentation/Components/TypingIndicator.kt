package com.thecodingcult.truecommunity.community.presentation.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thecodingcult.truecommunity.community.models.User
import kotlinx.coroutines.delay

@Composable
fun TypingIndicator(typingUsers: List<User>) {
    if (typingUsers.isEmpty()) return // Don't show if no one is typing

    var currentIndex by remember { mutableStateOf(0) }

    // Change the displayed user every 1.5 seconds
    LaunchedEffect(typingUsers) {
        while (true) {
            delay(1500L)
            currentIndex = (currentIndex + 1) % typingUsers.size
        }
    }

    val currentUser = typingUsers[currentIndex]

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Profile Picture with Orange Tint
        Image(
            painter = painterResource(currentUser.profilePicture),
            contentDescription = "Typing User",
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary) // Orange Tint
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Typing Text
        Text(
            text = "${currentUser.name} is typing...",
            fontSize = 14.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.width(6.dp))

        // Animated Dots
        AnimatedTypingDots()
    }
}