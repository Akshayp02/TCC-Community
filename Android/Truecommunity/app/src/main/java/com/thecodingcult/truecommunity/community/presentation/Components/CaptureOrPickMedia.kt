package com.thecodingcult.truecommunity.community.presentation.Components

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thecodingcult.truecommunity.R
import com.thecodingcult.truecommunity.core.presentation.ui.theme.PoppinsFont

@Composable
fun CaptureOrPickMedia(
    onDismiss: () -> Unit,
    onMediaSelected: (Uri?) -> Unit
) {
    val context = LocalContext.current

    // State for storing the selected media URI
    var mediaUri by remember { mutableStateOf<Uri?>(null) }

    // 1.Camera permission
    // 2. Capture Image

        val bitmap = remember { mutableStateOf<Bitmap?>(null) }

        val cameraLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicturePreview()
        ) { result: Bitmap? ->
            bitmap.value = result
            onDismiss()
        }

        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                cameraLauncher.launch()
            }
        }

    // Gallery Picker Launcher
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            mediaUri = uri
            onMediaSelected(uri)
        }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
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
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Capture Photo Button
                Button(

                    onClick =
                    {
                        // Request camera permission
                        permissionLauncher.launch(android.Manifest.permission.CAMERA)


                    }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.camera),
                        tint = Color.White,
                        contentDescription = "Capture Photo"
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        "Capture Photo", style = TextStyle(
                            fontSize = 10.sp,
                            fontFamily = PoppinsFont,
                            fontWeight = FontWeight(500),
                            color = Color.White,

                            )
                    )
                }

                // Handle the captured photo and upload it to the network
                val cameraLauncher =
                    rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
                        if (success) {
                            mediaUri?.let { uri ->
                                // Upload the captured photo to the network
                                onMediaSelected(uri)
                            }
                        }
                    }


                Spacer(modifier = Modifier.width(16.dp))

                // Pick Photo/Video Button
                Button(onClick = { galleryLauncher.launch("*/*") }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.image_24),
                        contentDescription = "Pick Media",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        "Photo/Video", style = TextStyle(
                            fontSize = 10.sp,
                            fontFamily = PoppinsFont,
                            fontWeight = FontWeight(500),
                            color = Color.White,

                            )
                    )
                }
            }

        }
    }
}
