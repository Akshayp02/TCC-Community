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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun HeaderBar(headerData: HeaderData, navController: NavHostController) {
    val rememberedNavController = remember { navController }
    var expanded by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val isNextVersionEnabled = false // TODO change it in next version
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 10.dp)
            .background(color = MaterialTheme.colorScheme.primary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back Icon
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
                .clickable { navController.popBackStack() }
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Profile Image
        Icon(
            painter = headerData.painter,
            contentDescription = "image description",
            tint = Color.White,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Title and Group Info
        Column(
            modifier = Modifier.weight(1f) // Take up all available horizontal space
        ) {
            Text(
                text = headerData.title,
                fontSize = 14.sp,
                color = Color.White
            )
            Text(
                text = headerData.groupInfo,
                fontSize = 10.sp,
                color = Color.White
            )
        }

        // Action Icons
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp), // Increased spacing
            verticalAlignment = Alignment.CenterVertically,

            ) {
            if (isNextVersionEnabled) {
                Icon(
                    painter = painterResource(id = R.drawable.bank),
                    contentDescription = "Bank",
                    tint = Color.White,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { }
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.calendar),
                contentDescription = "Calendar",
                tint = Color.White,
                modifier = Modifier
                    .size(20.dp)
                    .clickable { }
            )
            Box {
                Icon(
                    imageVector = headerData.imageLast,
                    contentDescription = "More Options",
                    tint = Color.White,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { expanded = true }
                )

                // DropdownMenu remains the same...
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(Color.White)
                ) {
                    DropdownMenuItem(
                        text = { Text("View Campaigns", color = Color.Black) },
                        onClick = { expanded = false }
                    )
                    DropdownMenuItem(
                        text = { Text("Emergency Reserve", color = Color.Black) },
                        onClick = { expanded = false }
                    )
                    DropdownMenuItem(
                        text = { Text("Donation List", color = Color.Black) },
                        onClick = { expanded = false }
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
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
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
            containerColor = Color.White // change the color of the container
        ) {
            FundraisingBottomSheet("Ashish Reddy")
        }
    }
}