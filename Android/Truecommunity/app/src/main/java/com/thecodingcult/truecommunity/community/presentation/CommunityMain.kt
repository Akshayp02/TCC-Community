package com.thecodingcult.truecommunity.community.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.thecodingcult.truecommunity.R
import com.thecodingcult.truecommunity.community.data.ChatMessage
import com.thecodingcult.truecommunity.community.presentation.Components.MassageFormatRow
import com.thecodingcult.truecommunity.core.presentation.ui.theme.TextHeadingColor
import com.thecodingcult.truecommunity.navigation.Routes

@Composable
fun CommunityMain(navController: NavHostController) {
    val chatMessages = listOf(
        ChatMessage(
            image = R.drawable.marieclairekorea,
            "Family Community",
            "Let's catch up later.",
            "11:45 AM",
            1
        ),
        ChatMessage(
            image = R.drawable.businessprofile,
            "Business Group 3",
            "Meeting at 3 PM.",
            "10:30 AM",
            3
        ),
        ChatMessage(
            image = R.drawable.otheruserprofile,
            "Classmate Group 1",
            "Can you send the report?",
            "9:15 AM",
            1
        ),

        ChatMessage(
            image = R.drawable.myimg,
            "Neighbour Group 2",
            "See you soon.",
            "7:45 AM",
            2
        ),
    )

    Column(
        Modifier
            .fillMaxWidth()
            .height(783.dp)
            .background(
                color = MaterialTheme.colorScheme.background,
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
                .fillMaxSize()
                .padding(5.dp)
        ) {
            // TODO : add this member request in lazy column with wrapContentSize
            MemberRequestDialog()
            CommunityNavTab(navController)
            Text(
                text = "Frequently Used",
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

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
            ) {
                items(chatMessages) { message ->
                    MassageFormatRow(
                        message = message,
                        modifier = Modifier.clickable {
                            when (message.title) {
                                "Family Community" -> navController.navigate(
                                    Routes.GroupsChatScreen.createRoute(
                                        message.title
                                    )
                                )

                                "Business Group 3" -> navController.navigate(
                                    Routes.BusinessGroupChatScreen.createRoute(
                                        message.title
                                    )
                                )

                                "Classmate Group 1" -> navController.navigate(
                                    Routes.ClassMateGroupChatScreen.createRoute(
                                        message.title
                                    )
                                )

                                "Neighbour Group 2" -> navController.navigate(
                                    Routes.NeighboursGroupChatScreen.createRoute(
                                        message.title
                                    )
                                )
                            }

                        }
                    )
                }
            }
        }
    }
}