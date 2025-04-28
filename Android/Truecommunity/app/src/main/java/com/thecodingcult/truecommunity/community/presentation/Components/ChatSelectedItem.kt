package com.thecodingcult.truecommunity.community.presentation.Components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thecodingcult.truecommunity.R
import com.thecodingcult.truecommunity.core.presentation.ui.theme.ColorPrimary
import com.thecodingcult.truecommunity.core.presentation.ui.theme.PoppinsFont

@Composable
fun ChatSelectedItem(
    backgroundColor: Color,
    selectedCount: Int? = null,
    showBackButton: Boolean,
    showCopyIcon: Boolean,
    showPinIcon: Boolean,
    showDeleteIcon: Boolean,
    showShareIcon: Boolean,
    showMoreOptions: Boolean,
    onBackClick: () -> Unit = {},
    onCopyClick: () -> Unit = {},
    onPinClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    onMoreOptionsClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(backgroundColor)
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Start Row: Back button + Selected count
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (showBackButton) {
                IconImage(
                    iconRes = R.drawable.backbutton,
                    clickedIconRes = R.drawable.backbutton, // Provide clicked icon resource
                    contentDesc = "Back",
                    onClick = onBackClick
                )
            }

            selectedCount?.let {
                Text(
                    text = it.toString(),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = PoppinsFont,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                )
            }
        }

        // End Row: Action icons
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (showCopyIcon) {
                IconImage(
                    iconRes = R.drawable.copyicon,
                    clickedIconRes = R.drawable.copyicon, // Provide clicked icon resource
                    contentDesc = "Copy",
                    onClick = onCopyClick
                )
            }

            if (showPinIcon) {
                IconImage(
                    iconRes = R.drawable.pin,
                    clickedIconRes = R.drawable.pin, // Provide clicked icon resource
                    contentDesc = "Pin",
                    onClick = onPinClick
                )
            }

            if (showDeleteIcon) {
                IconImage(
                    iconRes = R.drawable.deleteicon,
                    clickedIconRes = R.drawable.deleteicon, // Provide clicked icon resource
                    contentDesc = "Delete",
                    onClick = onDeleteClick
                )
            }

            if (showShareIcon) {
                IconImage(
                    iconRes = R.drawable.share,
                    clickedIconRes = R.drawable.share, // Provide clicked icon resource
                    contentDesc = "Share",
                    onClick = onShareClick
                )
            }

            if (showMoreOptions) {
                IconImage(
                    iconRes = R.drawable.more_options,
                    clickedIconRes = R.drawable.more_options, // Provide clicked icon resource
                    contentDesc = "More Options",
                    onClick = onMoreOptionsClick
                )
            }
        }
    }
}

@Composable
fun IconImage(
    @DrawableRes iconRes: Int,
    @DrawableRes clickedIconRes: Int,
    contentDesc: String,
    onClick: () -> Unit,
    iconSize: Dp = 24.dp,
    initialTint: Color = Color.White
) {
    var tint by remember { mutableStateOf(initialTint) }
    var currentIconRes by remember { mutableStateOf(iconRes) }

    Box(
        modifier = Modifier
            .size(iconSize)
            .clickable {
                tint = ColorPrimary
                currentIconRes = if (currentIconRes == iconRes) clickedIconRes else iconRes
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = currentIconRes),
            contentDescription = contentDesc,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}