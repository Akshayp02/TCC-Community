package com.thecodingcult.truecommunity.community.presentation.IMPComps


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.thecodingcult.truecommunity.R
import com.thecodingcult.truecommunity.community.data.ChatMessage
import com.thecodingcult.truecommunity.community.data.HeaderData
import com.thecodingcult.truecommunity.community.presentation.Components.CreateZoomScreen
import com.thecodingcult.truecommunity.community.presentation.Components.HeaderBar
import com.thecodingcult.truecommunity.community.presentation.Components.MassageFormatRow
import com.thecodingcult.truecommunity.community.presentation.Components.OnLongpressHeader
import com.thecodingcult.truecommunity.core.presentation.ui.theme.TextHeadingColor
import com.thecodingcult.truecommunity.navigation.Routes

@Composable
fun ClassMateGroupInterface(navController: NavHostController, title: String) {
    val headerData = HeaderData(
        painterResource(id = R.drawable.callmastes),
        title = title,
        groupInfo = "2 groups",
        imageLast = Icons.Default.MoreVert
    )
    val chatMessages = listOf(
        ChatMessage(
            image = R.drawable.otheruserprofile,
            "My Classmates",
            "Pls take a look at the images.",
            "11:45 AM",
            1
        ),
        ChatMessage(
            image = R.drawable.otheruserprofile,
            "School Freinds",
            "Pls take a look at the images.",
            "10:30 AM",
            3
        ),
        ChatMessage(
            image = R.drawable.otheruserprofile,
            "10th Classmates",
            "Pls take a look at the images.",
            "9:15 AM",
            1
        ),
        ChatMessage(
            image = R.drawable.otheruserprofile,
            "12th Classmates",
            "Pls take a look at the images.",
            "8:00 AM",
            4
        ),
        ChatMessage(
            image = R.drawable.otheruserprofile,
            "Degree Classmates",
            "Pls take a look at the images.",
            "7:45 AM",
            2
        ),
        ChatMessage(
            image = R.drawable.otheruserprofile,
            "MBA Classmates",
            "Pls take a look at the images.",
            "7:00 AM",
            1
        ),
        ChatMessage(
            image = R.drawable.otheruserprofile,
            "KG Classmates",
            "Pls take a look at the images.",
            "6:30 AM",
            3
        ),
    )

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var showButton by remember { mutableStateOf(true) }
    var selectedItems by remember { mutableStateOf(setOf<ChatMessage>()) }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                showButton = index == 0
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .width(430.dp)
                .height(90.dp),
            contentAlignment = Alignment.Center
        ) {
            if (selectedItems.isEmpty()) {
                HeaderBar(headerData, navController)
            } else {
                OnLongpressHeader(selectedItems.size)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topStart = 30.dp,
                        topEnd = 30.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                CreateZoomScreen(painterResource(id = R.drawable.classmatecommunityheader))
            }
            Text(
                text = "Classmate Groups",
                modifier = Modifier
                    .width(176.dp)
                    .height(22.dp),
                style = TextStyle(
                    fontSize = 22.sp,
                    lineHeight = 22.sp,
                    fontWeight = FontWeight(600),
                    color = TextHeadingColor,
                    textAlign = TextAlign.Center
                )
            )
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 20.dp)
                ) {
                    items(chatMessages) { message ->
                        val isSelected = selectedItems.contains(message)
                        MassageFormatRow(
                            message,
                            modifier = Modifier
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onTap = {
                                            if (selectedItems.isNotEmpty()) {
                                                selectedItems = if (isSelected) {
                                                    selectedItems - message
                                                } else {
                                                    selectedItems + message
                                                }
                                            } else {
                                                navController.navigate(
                                                    Routes.ClassMateGroupChatScreen.createRoute(
                                                        message.title,
                                                    )
                                                )
                                            }
                                        },
                                        onLongPress = {
                                            selectedItems = if (isSelected) {
                                                selectedItems - message
                                            } else {
                                                selectedItems + message
                                            }
                                        }
                                    )
                                }
                                .background(if (isSelected) MaterialTheme.colorScheme.secondary else Color.Transparent)
                        )
                    }
                }
                if (showButton) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                            .align(Alignment.BottomEnd),
                        horizontalAlignment = Alignment.End,
                    ) {
                        IconButton(
                            onClick = { /* Handle click */ },
                            modifier = Modifier.size(56.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.addgrp),
                                contentDescription = "Create Group",
                                modifier = Modifier
                                    .size(68.dp)
                                    .clickable {
                                        navController.navigate(Routes.CreateGroup.route)
                                    },
                            )
                        }
                    }
                }
            }
        }
    }
}
