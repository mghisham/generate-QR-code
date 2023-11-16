package com.mghisham.scandroid

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.mghisham.scandroid.ui.theme.ScanDroidTheme

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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen(
    onContinueClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "some useful description",
            modifier = Modifier
                // Set image size to 40 dp
                .size(250.dp)
        )

        var text by remember { mutableStateOf("https://mghisham.github.io/") }

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter your text") }
        )

        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = { onContinueClicked(text) }
        ) {
            Text("Continue")
        }
    }
}

@Composable
fun OutputScreen(
    inputText: String,
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Your QR code is ready!")
        Image(
            bitmap = inputText.generateQRCode.asImageBitmap(),
            contentDescription = "some useful description",
            modifier = Modifier
                // Set image size to 40 dp
                .size(350.dp)
        )
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onBackClicked
        ) {
            Text("Back")
        }
    }
}


val String.generateQRCode: Bitmap
    get() {
        val width = 150
        val height = 150
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val codeWriter = MultiFormatWriter()
        try {
            val bitMatrix =
                codeWriter.encode(this@generateQRCode, BarcodeFormat.QR_CODE, width, height)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    val color = if (bitMatrix[x, y]) Color.BLACK else Color.WHITE
                    bitmap.setPixel(x, y, color)
                }
            }
        } catch (e: WriterException) {
            Log.d("TAG", "generateQRCode: ${e.message}")

        }
        return bitmap
    }