package com.example.newsapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import com.example.navigation.AppComposeNavigator
import com.example.navigation.LocalComposeNavigator
import com.example.navigation.NewsScreen
import com.example.newsapi.ui.NewsMain
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val composeNavigator: AppComposeNavigator<NewsScreen> by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CompositionLocalProvider(
                LocalComposeNavigator provides composeNavigator
            ) {
                NewsMain(composeNavigator = composeNavigator)
            }
        }
    }
}