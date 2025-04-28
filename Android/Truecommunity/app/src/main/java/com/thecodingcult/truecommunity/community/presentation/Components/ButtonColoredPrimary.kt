package com.thecodingcult.truecommunity.community.presentation.Components

import androidx.compose.runtime.Composable


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Composable function that displays a primary  button with text.
 *
 * @param text The text to display inside the button.
 * @param onClick The callback to be invoked when the button is clicked.
 */

@Composable
fun ButtonColoredPrimary(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(168.dp)
            .height(38.dp)
            .background(color = Color(0xFFF84C10), shape = RoundedCornerShape(size = 6.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(26.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}