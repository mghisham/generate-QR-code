package com.hm.qrcode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.hm.qrcode.ui.InputScreen
import com.hm.qrcode.ui.OutputScreen
import com.hm.qrcode.ui.theme.ScanDroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScanDroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {

    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    var inputText by rememberSaveable { mutableStateOf("") }

    Surface(modifier) {
        if (shouldShowOnboarding) {
            InputScreen(onContinueClicked = { text ->
                inputText = text.trim().takeUnless { it.isEmpty() } ?: "Empty Text"
                shouldShowOnboarding = false
            })
        } else {
            OutputScreen(inputText, onBackClicked = { shouldShowOnboarding = true })
        }
    }
}


