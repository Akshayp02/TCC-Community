package com.thecodingcult.truecommunity.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.emoji2.bundled.BundledEmojiCompatConfig
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.thecodingcult.truecommunity.core.presentation.ui.theme.TrueCommunityTheme
import com.thecodingcult.truecommunity.core.presentation.ui.theme.statusbarColor
import com.thecodingcult.truecommunity.navigation.AppNavigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize EmojiCompat with Bundled Config
        val config = BundledEmojiCompatConfig(this)
        androidx.emoji2.text.EmojiCompat.init(config)


        setContent {

            TrueCommunityTheme {
                val navController = rememberNavController()
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = statusbarColor,
                        darkIcons = false
                    )
                }
                AppNavigation(navController)


            }

        }


    }
}