package com.myapplication

import MainView
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import data.local.DatabaseDriverFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SetColorStatusBar()
            MainView()
        }
    }
}

@Composable
private fun SetColorStatusBar() {
    val systemUiController = rememberSystemUiController()
    val isDarkMode = isSystemInDarkTheme()

    DisposableEffect(systemUiController, isDarkMode) {
        // Update all of the system bar colors to be transparent, and use
        // dark icons if we're in light theme
        systemUiController.setSystemBarsColor(
            color = if (isDarkMode) Color(0xFF1E1C1C) else Color.White,
            darkIcons = !isDarkMode
        )

        // setStatusBarColor() and setNavigationBarColor() also exist

        onDispose {}
    }
}