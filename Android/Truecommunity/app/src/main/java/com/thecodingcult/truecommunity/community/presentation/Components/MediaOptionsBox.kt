package com.thecodingcult.truecommunity.community.presentation.Components


import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thecodingcult.truecommunity.R

@Composable
fun MediaOptionsBox(onDismiss: () -> Unit) {
    val context = LocalContext.current
    var showPollDialog by remember { mutableStateOf(false) }
    var showEventDialog by remember { mutableStateOf(false) }
    // Gallery Picker
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            Toast.makeText(context, "Selected: $it", Toast.LENGTH_SHORT).show()
            onDismiss() // Dismiss dialog on success
        }
    }

    // Document Picker
    val documentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            Toast.makeText(context, "Document: $it", Toast.LENGTH_SHORT).show()
            onDismiss() // Dismiss dialog on success
        }
    }

    // Contact Picker
    val contactLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickContact()
    ) { uri: Uri? ->
        uri?.let {
            Toast.makeText(context, "Contact Selected: $it", Toast.LENGTH_SHORT).show()
            onDismiss() // Dismiss dialog on success
        }
    }

    // Audio Picker
    val audioLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            Toast.makeText(context, "Audio Selected: $it", Toast.LENGTH_SHORT).show()
            onDismiss() // Dismiss dialog on success
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(138.dp)
            .padding(horizontal = 10.dp)
            .shadow(
                elevation = 8.dp,
                spotColor = Color(0xF7C91111),
                ambientColor = Color(0x6B030303)
            )
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(14.dp)
            )
            .pointerInput(Unit) {
                detectTapGestures(onTap = { onDismiss() })
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                OptionItem(R.drawable.gallery, "Gallery") {
                    galleryLauncher.launch("image/*") // Open gallery
                }
                OptionItem(R.drawable.documents, "Document") {
                    documentLauncher.launch(arrayOf("*/*")) // Open document picker
                }
                OptionItem(R.drawable.location, "Location") {
                    context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                    onDismiss() // Dismiss after opening location settings
                }
                OptionItem(R.drawable.contact, "Contact") {
                    contactLauncher.launch(null)
                }
                OptionItem(R.drawable.poll, "Poll") {
                    showPollDialog = true
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                OptionItem(R.drawable.audio, "Audio") {
                    audioLauncher.launch("audio/*") // Open audio picker
                }
                OptionItem(R.drawable.events, "Events") {
                    showEventDialog = false
                }
                Spacer(modifier = Modifier.size(32.dp)) // Placeholder for future options
                Spacer(modifier = Modifier.size(32.dp)) // Placeholder for future options
                Spacer(modifier = Modifier.size(32.dp)) // Placeholder for future options
            }
        }

        if (showPollDialog) {
            CreatePollDialog(showDialog = showPollDialog, onDismiss = { showPollDialog = false })
        }

        if (showEventDialog) {
            //CreateEventDialog(showDialog = showEventDialog, onDismiss = { showEventDialog = false })
        }
    }
}

@Composable
fun OptionItem(iconRes: Int, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            modifier = Modifier.size(32.dp)
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}



