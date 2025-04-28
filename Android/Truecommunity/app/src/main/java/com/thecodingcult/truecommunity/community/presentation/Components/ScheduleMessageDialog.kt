package com.thecodingcult.truecommunity.community.presentation.Components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thecodingcult.truecommunity.R
import com.thecodingcult.truecommunity.core.presentation.ui.theme.RobotoBoldFont
import java.util.Calendar

@Composable
fun ScheduleMessageUI(onDismiss: () -> Unit) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    var selectedDate by remember { mutableStateOf("Select Date") }
    var selectedTime by remember { mutableStateOf("Select Time") }

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            selectedDate = "$dayOfMonth/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val timePickerDialog = TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            val period = if (hourOfDay >= 12) "PM" else "AM"
            val hour = if (hourOfDay > 12) hourOfDay - 12 else hourOfDay
            selectedTime = String.format("%02d:%02d %s", hour, minute, period)
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        false
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .wrapContentHeight()
            .shadow(
                elevation = 8.dp,
                spotColor = Color(0xF7C91111),
                ambientColor = Color(0x6B030303)
            )
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(14.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(18.dp)
            ) {
                // Header
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.events),
                        contentDescription = "Schedule Icon",
                        tint = Color(0xFFFF6A00),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Schedule Message",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF232323)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(text = "Set when to send message", fontSize = 14.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.vector560),
                        contentDescription = "image description",
                        contentScale = ContentScale.None
                    )
                }
                // Selectable Date and Time
                Column {
                    TimeRow("Set Date", selectedDate) { datePickerDialog.show() }
                    TimeRow("Set Time", selectedTime) { timePickerDialog.show() }
                }

                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.vector560),
                        contentDescription = "image description",
                        contentScale = ContentScale.None
                    )
                }

                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(5.dp)
                        .clickable { onDismiss() },
                ) {
                    Text(
                        text = "Cancel",
                        style = TextStyle(
                            fontSize = 17.sp,
                            lineHeight = 20.sp,
                            fontFamily = RobotoBoldFont,
                            fontWeight = FontWeight(500),
                            color = MaterialTheme.colorScheme.primary,
                        )
                    )
                }


            }
            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Disappearing Message Tag
                Box(
                    modifier = Modifier
                        .width(172.dp)
                        .height(48.dp)
                        .background(
                            color = Color(0xFFFFE4DB),
                            shape = RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 100.dp,
                                bottomStart = 10.dp,
                                bottomEnd = 0.dp
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Disappearing Message", color = Color(0xFFFF6A00), fontSize = 12.sp)
                }

                Button(
                    onClick = {
                        Toast.makeText(context, "Timer set successfully", Toast.LENGTH_SHORT).show()
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6A00)),
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(38.dp)
                        .padding( top = 5.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "Schedule Message",
                        style = TextStyle(
                            fontSize = 11.sp,
                            lineHeight = 20.sp,
                            fontFamily = RobotoBoldFont,
                            fontWeight = FontWeight(500),
                            color = Color(0xFFFFFFFF)
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun TimeRow(label: String, value: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
