package com.thecodingcult.truecommunity.community.presentation.IMPComps.Funds

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.thecodingcult.truecommunity.community.presentation.IMPComps.Funds.Components.FundraisingBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FundraisingScreen(navController: NavController) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showSheet by remember { mutableStateOf(false) }

    Scaffold(

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { showSheet = true }) {
                Text("Start Fundraising")
            }
        }

        // Modal Bottom Sheet
    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState,
            containerColor = Color.White // change the color of the container
        ) {
            FundraisingBottomSheet(userName = "Ashish Reddy")
        }
    }
    }
}
