package com.thecodingcult.truecommunity.community.presentation.Components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thecodingcult.truecommunity.R
import com.thecodingcult.truecommunity.navigation.Routes


@Composable
fun EmergencyMapComponent(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(1.dp)
            .background(
                color = MaterialTheme.colors.onBackground,
                shape = RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 30.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.emergencyheader),
            contentDescription = "Image",
            modifier = Modifier.fillMaxSize()
        )
        // Add buttons on the image screen
        EmergencyMapTopbar(
            onSafeZoneClick = {
                navController.navigate(Routes.EmergencyLocations.route)
            },
            onHospitalClick = { },
            onEmergencyClick = { },
            OnMoreOptionsClick = { },
            onPoliceStationClick = { },
            onFireStationClick = { },


        )
    }
}