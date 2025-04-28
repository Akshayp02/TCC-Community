import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.thecodingcult.truecommunity.R
import com.thecodingcult.truecommunity.community.data.EmergencyPlaces
import com.thecodingcult.truecommunity.community.presentation.Components.EmergencyMapTopbar
import com.thecodingcult.truecommunity.core.presentation.ui.theme.ColorPrimary
import com.thecodingcult.truecommunity.core.presentation.ui.theme.TextHeadingColor

@Composable
fun EmergencyLocations(navController: NavHostController) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val imageHeight = screenHeight * 0.3f

    val places = listOf(
        EmergencyPlaces("Hospital", "Timing 24×7 Open", "500m", Icons.Outlined.Add),
        EmergencyPlaces("Bunker", "Timing 5:00 AM to 6:00 PM", "500m", Icons.Outlined.Home),
        EmergencyPlaces("Hospital", "Timing 24×7 Open", "500m", Icons.Outlined.Add),
        EmergencyPlaces("Bunker", "Timing 5:00 AM to 6:00 PM", "500m", Icons.Outlined.Home),
        EmergencyPlaces("Hospital", "Timing 24×7 Open", "500m", Icons.Outlined.Add),
        EmergencyPlaces("Bunker", "Timing 5:00 AM to 6:00 PM", "500m", Icons.Outlined.Home),
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Box for map background and top bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.emergencyheader),
                contentDescription = "Emergency Map",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Top bar with filter options
            EmergencyMapTopbar(
                onSafeZoneClick = { /* TODO: Handle Safe Zone Click */ },
                onHospitalClick = { /* TODO: Handle Hospital Click */ },
                onEmergencyClick = { /* TODO: Handle Emergency Click */ },
                onPoliceStationClick = { /* TODO: Handle Police Station Click */ },
                onFireStationClick = { /* TODO: Handle Fire Station Click */ },
                OnMoreOptionsClick = { /* TODO: Handle More Options Click */ }
            )

        }

        // Title for the list section
        Text(
            text = "Emergency Locations",
            style = TextStyle(
                fontSize = 22.sp,
                lineHeight = 22.sp,

                fontWeight = FontWeight.W600,
                color = TextHeadingColor,
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier.padding(16.dp)
        )

        // List of emergency places
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
        ) {
            items(places) { place ->
                PlaceItem(place)
            }
        }
    }
}


@Composable
fun PlaceItem(place: EmergencyPlaces) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .background(Color.White, shape = RoundedCornerShape(50))
            .padding(horizontal = 12.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(Color(0xFFF8F8F8), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = place.icon,
                contentDescription = place.name,
                tint = Color(0xFFFF5722),
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Text Details
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = place.name,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color(0xFF1A1A1A)
            )
            Text(text = place.timing, fontSize = 12.sp, color = Color(0xFF707070))
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Distance
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = "Distance",
                tint = Color.Red,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = place.distance,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // View Button
        Button(
            onClick = { /* TODO Handle View Click */ },
            colors = ButtonDefaults.buttonColors(containerColor = ColorPrimary),
            shape = RoundedCornerShape(50),
            modifier = Modifier.height(32.dp)
        ) {
            Text(text = "View", color = Color.White, fontSize = 14.sp)
        }
    }
}
