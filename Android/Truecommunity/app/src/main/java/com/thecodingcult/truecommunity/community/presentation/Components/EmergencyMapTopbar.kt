package com.thecodingcult.truecommunity.community.presentation.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thecodingcult.truecommunity.R
import com.thecodingcult.truecommunity.core.presentation.ui.theme.ColorPrimary

@Composable
fun EmergencyMapTopbar(
    onSafeZoneClick: () -> Unit,
    onHospitalClick: () -> Unit,
    onEmergencyClick: () -> Unit,
    onPoliceStationClick: () -> Unit,
    onFireStationClick: () -> Unit,
    OnMoreOptionsClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.Transparent)
            .padding(horizontal = 8.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LazyRow(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val itemsList = listOf(
                Triple("Safe Zone", Icons.Outlined.Home, onSafeZoneClick),
                Triple("Hospital", Icons.Outlined.Add, onHospitalClick),
                Triple("Emergency", R.drawable.emergencyicon, onEmergencyClick),
                Triple("Police Station", Icons.Outlined.Home, onPoliceStationClick),
                Triple("Fire Station", Icons.Outlined.Home, onFireStationClick)
            )

            items(itemsList) { (label, icon, onClick) ->
                Spacer(modifier = Modifier.width(3.dp))
                Box(
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(36.dp)
                        .background(
                            color = if (label == "Safe Zone") ColorPrimary else Color.White,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable { onClick() }
                        .padding(horizontal = 10.dp), // Adjust padding for better spacing
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(6.dp)
                    ) {
                        if (icon is ImageVector) {
                            Icon(
                                imageVector = icon,
                                contentDescription = label,
                                tint = if (label == "Safe Zone") Color.White else ColorPrimary,
                                modifier = Modifier.size(16.dp)
                            )
                        } else {
                            Image(
                                painter = painterResource(id = icon as Int),
                                contentDescription = label,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = label,
                            color = if (label == "Safe Zone") Color.White else Color.Gray,
                            fontSize = 12.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.wrapContentWidth()
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.width(3.dp))
        Icon(
            imageVector = Icons.Outlined.MoreVert,
            contentDescription = "More",
            tint = Color.Black,
            modifier = Modifier.clickable { OnMoreOptionsClick() }
        )
    }
}