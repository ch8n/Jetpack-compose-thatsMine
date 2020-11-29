package com.ch8n.thatsmine.ui.splash

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.res.loadVectorResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.ui.tooling.preview.Preview
import com.ch8n.thatsmine.R
import com.ch8n.thatsmine.base.theme.ThatsMineTheme

@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    SplashView()
}


@Composable
fun SplashView() {
    ThatsMineTheme {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            //TODO how to show images???
            DrawableImage()
        }

    }
}

@Composable
fun DrawableImage() {
    val placeholder = loadVectorResource(id = R.drawable.ic_alter).resource.resource
    Image(asset = placeholder!!)
}