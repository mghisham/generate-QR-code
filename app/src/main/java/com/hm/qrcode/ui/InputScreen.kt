package com.hm.qrcode.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hm.qrcode.R

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
            contentDescription = "Icon",
            modifier = Modifier
                // Set image size to 40 dp
                .size(250.dp)
        )

        var text by remember { mutableStateOf("https://mghisham.github.io/") }

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter your text") },
            trailingIcon = {
                IconButton(onClick = { text = "" }) {
                    Icon(Icons.Filled.Clear, contentDescription = "Clear text")
                }
            }
        )

        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = { onContinueClicked(text) }
        ) {
            Text("Continue")
        }
    }
}