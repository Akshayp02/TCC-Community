package com.thecodingcult.truecommunity.community.presentation

    import androidx.compose.foundation.background
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.width
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Brush
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.unit.dp
    import androidx.navigation.NavHostController
    import com.google.accompanist.systemuicontroller.rememberSystemUiController
    import com.thecodingcult.truecommunity.R
    import com.thecodingcult.truecommunity.community.data.ChatMessage
    import com.thecodingcult.truecommunity.community.presentation.Components.ProfileDropbox

    @Composable
    fun CreateScreen(navController: NavHostController) {
        val msg = listOf(
            ChatMessage(
                 R.drawable.scanner, "John Doe", "12:00", "", 0
            )
        )

        Column {
            Box(
                modifier = Modifier
                    .width(430.dp)
                    .height(170.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFFF6935),
                                Color(0xFFF84C10)
                            )
                        )
                    )
            ) {
                ProfileDropbox(
                    message = msg[0]
                )
            }
        }
    }