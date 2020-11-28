package com.ch8n.thatsmine.ui.splash

import androidx.compose.foundation.Text
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    SplashView()
}


@Composable
fun SplashView() {
    Text(text = "That's Mine")
}

