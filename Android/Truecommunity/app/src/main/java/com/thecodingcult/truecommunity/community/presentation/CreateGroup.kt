package com.thecodingcult.truecommunity.community.presentation

import CreateGroupBottomSheet
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.thecodingcult.truecommunity.R
import com.thecodingcult.truecommunity.community.data.HeaderData
import com.thecodingcult.truecommunity.community.presentation.Components.CreateZoomScreen
import com.thecodingcult.truecommunity.community.presentation.Components.HeaderBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreateGroup(navController: NavHostController) {
    val headerData = HeaderData(
        painterResource(id = R.drawable.family),
        title = "Family Community",
        groupInfo = "2 groups",
        imageLast = Icons.Default.MoreVert
    )
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    // this is bottom sheet for the asking quetions of create group
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        ),
        sheetElevation = 8.dp,
        sheetContent = {
            CreateGroupBottomSheet()
        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primary),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .width(430.dp)
                    .height(130.dp),
                contentAlignment = Alignment.Center
            ) {
                HeaderBar(headerData, navController)
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ){
                    CreateZoomScreen(painterResource(id = R.drawable.famzoom))
                }


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 275.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                bottomSheetState.show()
                            }
                        },
                        modifier = Modifier.size(56.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.addgrp),
                            contentDescription = "Create Group",
                            modifier = Modifier.size(68.dp)
                        )
                    }
                    Text(
                        text = "Create Groups",
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
            }
        }
    }
}