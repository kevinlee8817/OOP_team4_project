package com.example.aborea.pages.statistics.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aborea.common.OwnglyphText

@Composable
fun BarWithText() {
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            OwnglyphText("공부", 30, 0, 0, 0xFF6A6A6A)
            OwnglyphText("운동", 30, 0, 0, 0xFF6A6A6A)
            OwnglyphText("업무", 30, 0, 0, 0xFF6A6A6A)
            OwnglyphText("취미", 30, 0, 0, 0xFF6A6A6A)
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .offset(y = 8.dp),
            verticalArrangement = Arrangement.spacedBy(17.dp)
        ) {
            ProgressBar(0.2f, 0xFFEA6751)
            ProgressBar(0.4f, 0xFF4B90C0)
            ProgressBar(0.7f, 0xFFFECE54)
            ProgressBar(0.5f, 0xFF9D6297)
        }
    }
}