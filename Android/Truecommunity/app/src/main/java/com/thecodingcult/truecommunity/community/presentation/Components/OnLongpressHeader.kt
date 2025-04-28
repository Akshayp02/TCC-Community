package com.thecodingcult.truecommunity.community.presentation.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thecodingcult.truecommunity.R
import com.thecodingcult.truecommunity.community.data.HeaderData


@Composable
fun OnLongpressHeader(selectedItems: Int) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // create a back button here
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.White,
            modifier = Modifier
                .clickable { // TODO: Implement back navigation
                }
                .padding(10.dp)
                .width(24.dp)
                .height(24.dp)
        )

        Text(
            text = selectedItems.toString(), // selected items
            fontSize = 14.sp,
            color = Color(0xFFFFFFFF)
        )

        Column(
            modifier = Modifier
                .padding(start = 10.dp)
        ) {
            Text(
                text = "",
                fontSize = 14.sp,
                color = Color(0xFFFFFFFF)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "",
                    fontSize = 10.sp,
                    color = Color(0xFFFFFFFF)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                painter = painterResource(id = R.drawable.pin),
                contentDescription = "pin Icon",
                tint = Color(0xFFFFFFFF),
                modifier = Modifier
                    .width(18.dp)
                    .height(18.dp)
                    .clickable {
                        // TODO: Implement pin functionality
                    }
            )

            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                painter = painterResource(id = R.drawable.mutenotification),
                contentDescription = "Mute Icon",
                tint = Color(0xFFFFFFFF),
                modifier = Modifier
                    .width(18.dp)
                    .height(18.dp)
                    .clickable {
                        // TODO: Implement mutenotification functionality
                    }
            )

            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More Options Icon",
                tint = Color(0xFFFFFFFF),
                modifier = Modifier
                    .width(18.dp)
                    .height(18.dp)
            )
        }
    }
}