package com.thecodingcult.truecommunity.community.presentation.Components

import android.net.Uri
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FullScreenImage(
    imageUris: List<Uri>,
    initialIndex: Int = 0,
    onDismiss: () -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = initialIndex
    )

    Log.d("FullScreenImage", "Initial index: $initialIndex, Image count: ${imageUris.size}")

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collectLatest { page ->
            Log.d("FullScreenImage", "Current page: $page")
        }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

                // Optional top bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                ChatSelectedItem(
                    backgroundColor = Color.Transparent,
                    selectedCount = null,
                    showBackButton = true,
                    showCopyIcon = false,
                    showPinIcon = true,
                    showDeleteIcon = true,
                    showShareIcon = true,
                    showMoreOptions = true,
                    onBackClick = onDismiss
                )


                // HorizontalPager
                HorizontalPager(
                    count = imageUris.size,
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) { page ->
                    Log.d("FullScreenImage", "Displaying image at page: $page")

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUris[page])
                            .crossfade(true)
                            .build(),
                        contentDescription = "Image $page",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = androidx.compose.ui.layout.ContentScale.Fit
                    )
                }
            }

            BackHandler(onBack = onDismiss)
        }
    }
}
