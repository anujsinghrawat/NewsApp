package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.ui.news_screen.NewsScreen
import com.example.newsapp.ui.news_screen.NewsScreenViewModel
import com.example.newsapp.ui.theme.NewsAPpTheme
import com.example.newsapp.util.NavGraphSetup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAPpTheme {
                val navController = rememberNavController()
                NavGraphSetup(navController = navController)

            }
        }
    }
}

