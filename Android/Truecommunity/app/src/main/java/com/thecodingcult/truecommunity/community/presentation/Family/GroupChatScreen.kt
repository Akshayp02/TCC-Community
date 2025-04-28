package com.thecodingcult.truecommunity.community.presentation.Family

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.thecodingcult.truecommunity.R
import com.thecodingcult.truecommunity.community.data.HeaderData
import com.thecodingcult.truecommunity.community.presentation.Components.ChatHeaderBar
import com.thecodingcult.truecommunity.community.presentation.Components.ChatScreen

@Composable
fun GroupChatScreen(navController: NavHostController, title: String) {
    val headerData = HeaderData(
        painter = painterResource(id = R.drawable.family),
        title = title,
        groupInfo = "2 more peoples online",
        imageLast = Icons.Default.MoreVert
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .width(430.dp)
                .height(80.dp),

            contentAlignment = Alignment.Center
        ) {
            ChatHeaderBar(headerData, navController)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(
                        topStart = 30.dp,
                        topEnd = 30.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
        ){
            ChatScreen()
        }
    }
}

@Preview
@Composable
fun previewrr() {
    val navController = rememberNavController()
    GroupChatScreen(navController, title = "Family")
}