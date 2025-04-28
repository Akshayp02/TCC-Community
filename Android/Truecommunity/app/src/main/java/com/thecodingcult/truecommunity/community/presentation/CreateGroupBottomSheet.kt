
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thecodingcult.truecommunity.community.data.Message
import com.thecodingcult.truecommunity.community.presentation.Components.ChatBubble
import com.thecodingcult.truecommunity.community.presentation.Components.Supportinputfield
import com.thecodingcult.truecommunity.core.presentation.ui.theme.TextHeadingColor
import com.thecodingcult.truecommunity.core.presentation.ui.theme.buttonGrayshade3Bg
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGroupBottomSheet() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val bottomSheetHeight = (screenHeight * 0.7f).coerceAtMost(600.dp) // Ensures responsiveness

    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    val options = listOf(
        "Family", "Emergency", "Classmates", "Colleagues",
        "Neighbours", "Business", "Want to create new group"
    )
    var currentQuestionIndex by remember { mutableIntStateOf(-1) }
    val questions = listOf(
        "What will be your group Name?",
        "What will be your group Description?"
    )
    val answers = remember { mutableStateListOf<String>() }
    val messages = remember { mutableStateListOf<Message>() }
    var messageText by remember { mutableStateOf(TextFieldValue()) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(bottomSheetHeight)
            .padding(16.dp)
    ) {
        Text(
            text = "Hii Ashish Reddy \nWelcome To Tru Community",
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 30.sp,
                fontWeight = FontWeight(600),
                color = TextHeadingColor,
                textAlign = TextAlign.Center,
                letterSpacing = 0.2.sp
            ),
            modifier = Modifier
                .fillMaxWidth(0.9f) // Makes it responsive
                .wrapContentHeight()
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown Menu
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedCategory ?: "Select Existing Groups?",
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = "Dropdown",
                        modifier = Modifier.clickable { expanded = !expanded }
                    )
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = buttonGrayshade3Bg,
                    focusedContainerColor = buttonGrayshade3Bg,
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black,
                    cursorColor = Color.Black
                )
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(Color.White)
            ) {
                options.forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedCategory = option
                                expanded = false
                                if (option == "Want to create new group") {
                                    currentQuestionIndex = 0
                                    messages.add(
                                        Message(
                                            questions[currentQuestionIndex],
                                            isSentByUser = false
                                        )
                                    )
                                } else {
                                    currentQuestionIndex = -1
                                }
                            }
                            .padding(vertical = 8.dp, horizontal = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedCategory == option,
                            onClick = {
                                selectedCategory = option
                                expanded = false
                                if (option == "Want to create new group") {
                                    currentQuestionIndex = 0
                                    messages.add(
                                        Message(
                                            questions[currentQuestionIndex],
                                            isSentByUser = false
                                        )
                                    )
                                } else {
                                    currentQuestionIndex = -1
                                }
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = MaterialTheme.colorScheme.primary,
                                unselectedColor = Color.Gray
                            )
                        )
                        Text(
                            text = option,
                            modifier = Modifier.padding(start = 8.dp),
                            fontSize = 16.sp,
                            color = if (selectedCategory == option) MaterialTheme.colorScheme.primary else Color.Gray
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (selectedCategory == "Want to create new group") {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 38.dp)

            ) {
                // LazyColumn for Messages
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .imePadding(),
                    verticalArrangement = Arrangement.Top // Ensures messages appear from the top
                ) {
                    items(messages) { message ->
                        ChatBubble(message)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
                // Show Proceed button
                if (selectedCategory != null && (selectedCategory != "Want to create new group" || answers.size == questions.size)) {
                    Button(
                        onClick = { showDialog = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Proceed", color = Color.White)
                    }
                }
                // Input Field
                Box(
                    modifier = Modifier
                        .padding(horizontal = 1.dp, vertical = 4.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .imePadding()
                ) {
                    Supportinputfield(
                        messageText = messageText,
                        onMessageTextChange = { messageText = it },
                        onSendMessage = {
                            if (messageText.text.isNotBlank()) {
                                coroutineScope.launch {
                                    messages.add(
                                        Message(
                                            messageText.text,
                                            isSentByUser = true
                                        ),
                                    ) // Add at the end (natural chat behavior)
                                    answers.add(messageText.text) // Save user response in answers list
                                    messageText = TextFieldValue()
                                    if (currentQuestionIndex < questions.lastIndex) {
                                        currentQuestionIndex++
                                        messages.add(
                                            Message(
                                                questions[currentQuestionIndex],
                                                isSentByUser = false
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    )
                }
            }

        }

        if (selectedCategory != null && selectedCategory != "Want to create new group") {
            Button(
                onClick = { showDialog = true },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Proceed", color = Color.White)
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Group Created") },
            text = { Text("Your group has been successfully created.") },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("OK")
                }
            }
        )
    }
}
