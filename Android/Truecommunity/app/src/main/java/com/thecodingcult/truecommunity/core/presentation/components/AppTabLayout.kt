package com.thecodingcult.truecommunity.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.thecodingcult.truecommunity.R
import com.thecodingcult.truecommunity.community.presentation.CommunityMain
import com.thecodingcult.truecommunity.core.data.TabItem
import com.thecodingcult.truecommunity.core.presentation.ui.theme.PoppinsFont
import com.thecodingcult.truecommunity.core.presentation.ui.theme.getOrangeGradientBackgroundColor

@Composable
fun AppTabLayout(navController: NavHostController) {
    val tabItems = listOf(
        TabItem("Contacts", painterResource(id = R.drawable.contact)),
        TabItem("Community", painterResource(id = R.drawable.community)),
        TabItem("Videos", painterResource(id = R.drawable.video)),
        TabItem("Network", painterResource(id = R.drawable.network)),
        TabItem("Nearby", painterResource(id = R.drawable.nearby)),
    )

    var selectedTabIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(getOrangeGradientBackgroundColor()),
    ) {
        // Top Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .background(color = Color.Transparent)
                .padding(vertical= 13.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(start = 20.dp, end = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // Profile Image
                Image(
                    painter = painterResource(id = R.drawable.businessprofile),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,  // Ensures uniform scaling
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)  // Ensures circular crop if needed
                        .clickable { }
                )
                Spacer(modifier = Modifier.width(8.dp))

                // User Info Column
                Column {
                    Text(
                        text = "Hi, Akshay",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(1.dp))
                    Row {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Location",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp) // Increased size for better balance
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Jombang, East Java",
                            fontSize = 12.sp,
                            color = Color.White.copy(0.8f)
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(2f)) // Push icons to the right

                // Icons Section (All Icons are now of size 24.dp)
                listOf(
                    R.drawable.search to "Search",
                    R.drawable.notification to "Notifications",
                    R.drawable.bank to "Bank",
                ).forEach { (icon, description) ->
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = description,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)  // Increased size for consistency
                        )
                    }
                }

                // More options Icon
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More options",
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }


        Column(
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
                )
                .fillMaxWidth()
                .fillMaxHeight()
                .offset(y = (-45).dp)
        ) {
            // Tab Layout
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .padding(horizontal = 10.dp, vertical = 10.dp)
                    .shadow(
                    elevation = 8.dp,
                    spotColor = Color(0xF7C91111),
                    ambientColor = Color(0x6B030303)
                )
                    .clip(RoundedCornerShape(15.dp))
                    .background(
                        color = MaterialTheme.colorScheme.background, // Use surface color for better contrast
                        shape = RoundedCornerShape(12.dp)
                    ), contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    tabItems.forEachIndexed { index, tabItem ->
                        Column(modifier = Modifier
                            .clickable { selectedTabIndex = index }
                            .padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                painter = tabItem.icon,
                                contentDescription = tabItem.title,
                                tint = if (selectedTabIndex == index) MaterialTheme.colorScheme.primary else Color.Gray,
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(1.dp)
                            )
                            Text(
                                text = tabItem.title,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                style = TextStyle(
                                    fontSize = 10.sp,
                                    fontFamily = PoppinsFont,
                                    fontWeight = FontWeight(500),
                                    color = if (selectedTabIndex == index) MaterialTheme.colorScheme.primary else Color.Gray
                                )
                            )
                        }
                    }
                }
            }

            // Content Area
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
            ) {

                when (selectedTabIndex) {
                    1 -> CommunityMain(navController)
                }
//                if (isLoading) {
//                    HandshakeLoader(modifier = Modifier.align(Alignment.Center))
//                } else {
//                    when (selectedTabIndex) {
//                        1 -> CommunityMain(navController)
//                    }
//                }
            }
        }
    }
}
