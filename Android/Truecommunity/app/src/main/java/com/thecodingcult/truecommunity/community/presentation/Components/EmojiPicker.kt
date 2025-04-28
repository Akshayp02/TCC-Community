package com.thecodingcult.truecommunity.community.presentation.Components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thecodingcult.truecommunity.community.data.remote.EmojiApi
import kotlinx.coroutines.launch

@Composable
fun EmojiPicker(onEmojisSelected: (List<String>) -> Unit) {
    val scope = rememberCoroutineScope()
    var emojiList by remember { mutableStateOf<List<String>>(emptyList()) }
    var selectedEmojis by remember { mutableStateOf<List<String>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    // Fetch emojis
    LaunchedEffect(Unit) {
        scope.launch {
            try {
                emojiList = EmojiApi.api.getEmojis().map { it.character }
            } catch (e: Exception) {
                Log.e("EmojiPicker", "Error fetching emojis", e)
            } finally {
                isLoading = false
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp) // Approximate keyboard height
            .padding(8.dp)
    ) {
        if (isLoading) {
            // Loading State
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {

            // Emoji Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(7), // 7 Columns
                modifier = Modifier.fillMaxSize()
            ) {
                items(emojiList) { emoji ->
                    val isSelected = selectedEmojis.contains(emoji)

                    Text(
                        text = emoji,
                        fontSize = 24.sp, // Set emoji size
                        modifier = Modifier
                            .size(36.dp) // Spacing for selection
                            .background(if (isSelected) Color.Gray else Color.Transparent)
                            .clickable {
                                selectedEmojis = if (isSelected) {
                                    selectedEmojis - emoji
                                } else {
                                    selectedEmojis + emoji
                                }
                                onEmojisSelected(selectedEmojis)
                            }
                            .padding(4.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}


