package com.example.aborea.pages.statistics.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aborea.common.OwnglyphText

@Composable
fun BarWithText(status: Fruits) {
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
            ProgressBar(status, 0xFFEA6751, 0)
            ProgressBar(status, 0xFF4B90C0, 1)
            ProgressBar(status, 0xFFFECE54, 2)
            ProgressBar(status, 0xFF9D6297, 3)
        }
    }
}