package com.ch8n.thatsmine.base.components

import androidx.compose.foundation.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp


@Composable
fun TextBlackShadowed36(text: String, shadowColor: Color) {
    Text(
        text = text,
        style = TextStyle(
            color = Color.Black,
            fontSize = 36.sp,
            shadow = Shadow(
                color = shadowColor,
                blurRadius = 5f
            )
        )
    )
}

