package com.thecodingcult.truecommunity.community.presentation.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import com.example.contacts_module.composeUI.shortComponents.Scheduledialog
import com.thecodingcult.truecommunity.R

@Composable
fun ScheduledPick(
    onDismiss: () -> Unit,
    onShowTimerOptions: () -> Unit
) {
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
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            Scheduledialog(
                text = "Disappearing Message",
                iconId = R.drawable.disappearing_message,
                onClick = {
                    onShowTimerOptions()
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            Scheduledialog(
                text = "Schedule Message",
                iconId = R.drawable.schedule_icon,
                onClick = {
                    onShowTimerOptions()
                }
            )
        }
    }
}


@Composable
fun DialogController(
    onDismissAll: () -> Unit
) {
    var showTimerOptions by remember { mutableStateOf(false) }

    if (showTimerOptions) {
        ScheduleMessageUI(
            onDismiss = {
                showTimerOptions = false
            }
        )
    } else {
        ScheduledPick(
            onDismiss = onDismissAll,
            onShowTimerOptions = {
                showTimerOptions = true
            }
        )
    }
}
