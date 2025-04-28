package com.thecodingcult.truecommunity.community.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thecodingcult.truecommunity.R
import com.thecodingcult.truecommunity.community.data.ChatMessage
import com.thecodingcult.truecommunity.community.presentation.Components.ButtonColoredPrimary
import com.thecodingcult.truecommunity.community.presentation.Components.ButtonOutlinedPrimary
import com.thecodingcult.truecommunity.community.presentation.Components.ProfileDropbox

@Composable
fun MemberRequestDialog() {
    val showDialog = remember { mutableStateOf(true) }

    if (showDialog.value) {
        val profiledata = listOf(
            ChatMessage(
                image = R.drawable.marieclairekorea,
                "Family Community",
                "",
                "12",
                2
            )
        )
        Column {
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 4.dp,
                        spotColor = Color(0x1FF84C10),
                    )
                    .padding(start = 23.dp, end = 17.dp, top = 10.dp, bottom = 10.dp)
                    .border(
                        width = 0.5.dp,
                        color = Color(0x664F5E7B),
                        shape = RoundedCornerShape(
                            topStart = 12.dp,
                            topEnd = 12.dp,
                            bottomStart = 12.dp,
                            bottomEnd = 0.dp
                        )
                    )
                    .width(390.dp)
                    .height(144.dp)
                    .background(
                        color = MaterialTheme.colors.surface,
                        shape = RoundedCornerShape(
                            topStart = 12.dp,
                            topEnd = 12.dp,
                            bottomStart = 12.dp,
                            bottomEnd = 0.dp
                        )
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        ProfileDropbox(
                            message = profiledata[0]
                        )
                    }

                    Text(
                        text = "You have invited by John doeto join Family Tree",
                        style = TextStyle(
                            fontSize = 15.sp,
                            lineHeight = 26.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Start
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                            .padding(start = 8.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val context = LocalContext.current
                        ButtonColoredPrimary(
                            text = "Accept",
                            onClick = {
                                showDialog.value = false
                                Toast.makeText(
                                    context,
                                    "Accepted",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        ButtonOutlinedPrimary(
                            text = "Review",
                            onClick = {
                                Toast.makeText(
                                    context,
                                    "Review",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.vector560),
                    contentDescription = "image description",
                    contentScale = ContentScale.None
                )
            }
        }
    }
}