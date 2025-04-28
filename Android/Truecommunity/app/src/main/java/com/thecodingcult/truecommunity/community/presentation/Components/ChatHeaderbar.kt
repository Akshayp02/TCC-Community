package com.thecodingcult.truecommunity.community.presentation.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.thecodingcult.truecommunity.R
import com.thecodingcult.truecommunity.community.data.HeaderData
import com.thecodingcult.truecommunity.community.presentation.IMPComps.Funds.Components.FundraisingBottomSheet


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatHeaderBar(headerData: HeaderData, navController: NavHostController) {
    val rememberedNavController = remember { navController }
    var expanded by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    var showDialog by remember { mutableStateOf(false) }
    var buttonColor by remember { mutableStateOf(Color.White) }

    val isNextVersionEnabled = false
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            containerColor = MaterialTheme.colorScheme.background,
            title = { Text(text = "Select Call Option") },
            text = {
                Column {
                    Button(
                        onClick = {
                            showDialog = false
                            // Handle voice call action
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Text("Voice Call", color = Color.Black)
                    }
                    Button(
                        onClick = {
                            showDialog = false
                            // Handle video call action
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Text("Video Call", color = Color.Black)
                    }
                }
            },
            confirmButton = {}
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(79.dp)
            .padding(horizontal = 1.dp, vertical = 0.dp)
            .background(color = MaterialTheme.colorScheme.primary), // Todo change coor as per the theme color
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.White,
            modifier = Modifier
                .clickable { navController.popBackStack() }
                .padding(5.dp)
                .width(24.dp)
                .height(24.dp)
        )

        Icon(
            painter = headerData.painter,
            contentDescription = "image description",
            tint = Color.White,
            modifier = Modifier
                .padding(1.dp)
                .width(50.dp)
                .height(50.dp)
                .clip(CircleShape)
        )

        Column(
            modifier = Modifier
                .padding(start = 10.dp)
        ) {
            Text(
                text = headerData.title,
                fontSize = 14.sp,
                color = Color.White

            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = headerData.groupInfo,
                    fontSize = 10.sp,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp)
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            // Set to true for the next version

            if (isNextVersionEnabled) {
                Icon(
                    painter = painterResource(id = R.drawable.bank), // Use your preferred icon
                    contentDescription = "More Options",
                    tint = Color.White,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { }
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.calendar),
                contentDescription = "More Options",
                tint = Color.White,
                modifier = Modifier
                    .size(20.dp)
                    .clickable { }
            )

            Icon(
                painter = painterResource(id = R.drawable.videocal_icon),
                contentDescription = "video call Options",
                tint = Color.White,
                modifier = Modifier
                    .size(20.dp)
                    .clickable { }
            )
            Icon(
                painter = painterResource(id = R.drawable.voicecall_icon),
                contentDescription = "call Options",
                tint = buttonColor,
                modifier = Modifier
                    .size(18.dp)
                    .clickable {
                        showDialog = false
                        buttonColor = Color.White // Orange color
                    }
            )

            Box {
                Icon(
                    imageVector = headerData.imageLast, // Use your preferred icon
                    contentDescription = "More Options",
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { expanded = true }
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(Color.White)
                ) {
                    DropdownMenuItem(
                        text = { Text("View Campaigns", color = Color.Black) },
                        onClick = {
                            expanded = false
                            //navController.navigate("view_campaigns_screen")
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Emergency Reserve", color = Color.Black) },
                        onClick = {
                            expanded = false
                            //navController.navigate("emergency_reserve_screen")
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Donation List", color = Color.Black) },
                        onClick = {
                            expanded = false
                            // navController.navigate("donation_list_screen")
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    if (isNextVersionEnabled) {
                        Button(
                            onClick = {
                                expanded = false
                                showBottomSheet = true
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Text("Start Fund Raising", color = Color.White)
                        }

                    }
                }
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .wrapContentSize()
                .imePadding()
        ) {
            FundraisingBottomSheet("Akshay ")
        }
    }
}