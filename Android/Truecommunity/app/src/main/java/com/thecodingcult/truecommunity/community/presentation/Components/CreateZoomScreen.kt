package com.thecodingcult.truecommunity.community.presentation.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thecodingcult.truecommunity.R


@Composable
fun CreateZoomScreen(img :Painter) {
    Box(
        modifier = Modifier
            .fillMaxSize()              //fillMaxWidth()
            .height(247.dp)
            .padding(1.dp)
            .background(
                color = Color(0xFFF9F9F9),
                shape = RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 30.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
    ) {
        Image(
            painter = img ,
            contentDescription = "Image",
            modifier = Modifier.fillMaxSize()
        )
        Icon(
            painter = painterResource(id = R.drawable.iconamoon_screen_full_light),
            contentDescription = "Icon",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp)
        )
    }
}