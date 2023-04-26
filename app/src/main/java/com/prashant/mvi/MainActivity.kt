package com.prashant.mvi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.prashant.mvi.navigation.Navigator
import com.prashant.mvi.theme.MVITheme
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weakReference=WeakReference(this)
        setContent {
            val navHostController = rememberNavController()
            MVITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigator(navHostController)
                }
            }
        }
    }

    companion object {
        lateinit var weakReference: WeakReference<MainActivity>
    }

    override fun onDestroy() {
        super.onDestroy()
        weakReference.clear()
    }
}