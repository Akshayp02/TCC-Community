package com.thecodingcult.truecommunity.community.presentation.IMPComps.Components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.thecodingcult.truecommunity.R
import com.thecodingcult.truecommunity.community.data.GroupMassage
import com.thecodingcult.truecommunity.community.presentation.Components.PollScreen
import com.thecodingcult.truecommunity.core.presentation.ui.theme.ChatBackgroundColor
import com.thecodingcult.truecommunity.core.presentation.ui.theme.colorSecondary

@Composable
fun GroupChatWithIMG(message: GroupMassage, showSenderInfo: Boolean) {
    var selectedImage by remember { mutableStateOf<Uri?>(null) } // Full-screen image state

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, top = 8.dp, start = 12.dp),
        horizontalAlignment = if (message.isSentByUser) Alignment.End else Alignment.Start
    ) {
        Row(verticalAlignment = Alignment.Bottom) {
            if (showSenderInfo && !message.isSentByUser) {
                Image(
                    painter = painterResource(id = R.drawable.otheruserprofile),
                    contentDescription = "Other Person Profile",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            Box(
                modifier = Modifier
                    .background(
                        if (message.isSentByUser) ChatBackgroundColor else Color.White,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(12.dp)
            ) {
                Column {
                    if (showSenderInfo && !message.isSentByUser) {
                        Text(
                            text = message.senderName,
                            color = colorSecondary,
                            fontSize = 14.sp
                        )
                    }

                    // ✅ Show Poll if available
                    if (message.isPoll) {
                        PollScreen(message.senderName)
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    // ✅ Show Images with Progress Indicator
                    if (message.imageUrls.isNotEmpty()) {
                        ImageGrid(images = message.imageUrls) { clickedImage ->
                            selectedImage = clickedImage
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    // ✅ Show Text if available
                    if (message.text.isNotEmpty()) {
                        Text(text = message.text, fontSize = 16.sp)
                    }
                }
            }

            if (message.isSentByUser) {
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.myimg),
                    contentDescription = "User Profile",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }

    // Full-screen image preview when clicked
    selectedImage?.let { imageUri ->
        FullScreenImage(imageUri) {
            selectedImage = null
        }
    }
}

@Composable
fun ImageGrid(images: List<Uri>, onImageClick: (Uri) -> Unit) {
    Column {
        for (row in images.chunked(2)) { // Show images in 2 columns per row
            Row {
                for (imageUri in row) {
                    ImageWithLoader(imageUri) {
                        onImageClick(imageUri)
                    }
                }
            }
        }
    }
}

@Composable
fun ImageWithLoader(imageUri: Uri, onClick: () -> Unit) {
    var isLoading by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(4.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUri)
                .crossfade(true)
                .build(),
            contentDescription = "Chat Image",
            modifier = Modifier
                .size(100.dp)
                .clickable { onClick() },
            contentScale = ContentScale.Crop,
            onLoading = { isLoading = true },
            onSuccess = { isLoading = false },
            onError = { isLoading = false }
        )

        // ✅ Show Progress Indicator while loading
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
    }
}

@Composable
fun FullScreenImage(imageUri: Uri, onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false) // to show full screen img
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = imageUri,
                contentDescription = "Full Screen Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clickable {
                        // onDismiss()
                        // TODO: add fun to  zoom-in-out image
                    },
                contentScale = ContentScale.Fit
            )
        }
    }
}
