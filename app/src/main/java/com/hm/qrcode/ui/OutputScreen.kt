package com.hm.qrcode.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.hm.qrcode.utils.generateQRCode

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