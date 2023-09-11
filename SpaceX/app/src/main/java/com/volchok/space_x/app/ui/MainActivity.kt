package com.volchok.space_x.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.volchok.space_x.library.networking.system.NetworkDelegate
import com.volchok.space_x.ui.theme.SpaceXTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val networkDelegate by inject<NetworkDelegate>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkDelegate.onCreate()

        setContent {
            SpaceXTheme {
                MainScreen()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        networkDelegate.onDestroy()
    }
}