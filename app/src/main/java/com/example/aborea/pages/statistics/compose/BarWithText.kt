package com.example.aborea.pages.statistics.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.example.aborea.common.OwnglyphText

@Composable
fun BarWithText(text: String, color: Long) {
    Row {
        OwnglyphText(text, 30, 0, 0, 0xFF6A6A6A)
        ProgressBar(0.4f, color)
    }
}