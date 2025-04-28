package com.thecodingcult.truecommunity.community.presentation.Components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.thecodingcult.truecommunity.R
import com.thecodingcult.truecommunity.core.presentation.ui.theme.RobotoBoldFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePollDialog(showDialog: Boolean, onDismiss: () -> Unit) {
    if (showDialog) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .wrapContentHeight()
                    .background(
                        MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(20.dp)
                    )
            ) {
                Column {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 20.dp)
                    ) {
                        // Title
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {

                            Image(
                                painter = painterResource(id = R.drawable.poll),
                                contentDescription = "Create Poll",
                                modifier = Modifier
                                    .size(18.dp)
                                    .graphicsLayer(rotationZ = 90f),
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Create Poll",
                                style = TextStyle(
                                    fontSize = 17.sp,
                                    fontFamily = RobotoBoldFont,
                                    fontWeight = FontWeight(500),
                                    color = MaterialTheme.colorScheme.primary
                                ),

                                )
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        // Question Input
                        var question by remember { mutableStateOf(TextFieldValue()) }
                        OutlinedTextField(
                            value = question,
                            onValueChange = { question = it },
                            label = { Text("Ask a question") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary
                            )
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Options Input
                        var options by remember {
                            mutableStateOf(listOf(TextFieldValue(), TextFieldValue()))
                        }
                        options.forEachIndexed { index, textFieldValue ->
                            OutlinedTextField(
                                value = textFieldValue,
                                onValueChange = { newValue ->
                                    options = options.toMutableList().apply {
                                        this[index] = newValue
                                    }
                                },
                                label = { Text("Option ${index + 1}") },
                                modifier = Modifier.fillMaxWidth(),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = MaterialTheme.colorScheme.primary
                                )
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        // Add More Options Button
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable { options = options + TextFieldValue("") }
                                .padding(vertical = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add Option",
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "Add More Options",
                                color = Color.Gray,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }

                        // Allow Multiple Answers Toggle
                        var allowMultiple by remember { mutableStateOf(true) }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Allow multiple answers",
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Switch(
                                checked = allowMultiple,
                                onCheckedChange = { allowMultiple = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = MaterialTheme.colorScheme.background
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))


                    }

                    // Bottom Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Cancel",
                            style = TextStyle(
                                fontSize = 17.sp,
                                fontWeight = FontWeight(500),
                                color = MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier
                                .padding(start = 35.dp)
                                .clickable { onDismiss() }
                        )

                        Box(
                            modifier = Modifier
                                .width(172.dp)
                                .height(50.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(
                                        topStart = 100.dp,
                                        topEnd = 0.dp,
                                        bottomStart = 0.dp,
                                        bottomEnd = 10.dp
                                    )
                                )
                                .clickable { /* Handle create poll */ },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Create a Poll",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color.White
                                )
                            )
                        }
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCreatePollDialog() {
    CreatePollDialog(showDialog = true, onDismiss = {})
}