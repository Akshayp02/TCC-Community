package com.thecodingcult.truecommunity.community.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.thecodingcult.truecommunity.R
import com.thecodingcult.truecommunity.navigation.Routes

@Composable
fun CommunityNavTab(navController: NavHostController) {
    var selectedTab by remember { mutableStateOf(-1) }
    val tabs = listOf(
        "Family" to R.drawable.family,
        "Classmates" to R.drawable.callmastes,
        "Business" to R.drawable.businesscomunityicon,
        "Colleagues" to R.drawable.colleagues,
        "Neighbours" to R.drawable.neighbours,
        "Emergency" to R.drawable.emergencyicon
    )

    Row(
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(8.dp)
        ) {
            tabs.forEachIndexed { index, pair ->
                val (title, icon) = pair
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            selectedTab = index
                            when (index) {
                                0 -> navController.navigate(Routes.GroupsInterface.createRoute("Family Community"))
                                1 -> navController.navigate(
                                    Routes.ClassMateGroupsInterface.createRoute(
                                        "Classmate Community"
                                    )
                                )

                                2 -> navController.navigate(
                                    Routes.BusinessGroupsInterface.createRoute(
                                        "Business Community"
                                    )
                                )

                                3 -> navController.navigate(
                                    Routes.ColleaguesGroupsInterface.createRoute(
                                        "Colleagues Community"
                                    )
                                )

                                4 -> navController.navigate(
                                    Routes.NeighboursGroupsInterface.createRoute(
                                        "Neighbour Community"
                                    )
                                )

                                5 -> navController.navigate(
                                    Routes.EmergencyGroupsInterface.createRoute(
                                        "Emergency Community"
                                    )
                                )
                            }
                        }
                ) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = title,
                        tint = if (selectedTab == index) MaterialTheme.colorScheme.primary else Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = title,
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 22.sp,
                            fontWeight = FontWeight(500),
                            color = if (selectedTab == index) MaterialTheme.colorScheme.primary else Color(
                                0xFF4F5E7B
                            ),
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}