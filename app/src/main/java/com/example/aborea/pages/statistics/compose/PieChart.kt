package com.example.aborea.pages.statistics.compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PieChart(status: Fruits) {
    val total = status.fruitContainer.sum().toFloat()
    var startAngle = 0f
    var cnt = 0

    Canvas(
        modifier = Modifier
            .size(250.dp)
            .offset(y = -20.dp)
    ) {
        for(i in status.fruitContainer) {
            if (total == 0f) break

            val sweepAngle = (i / total) * 360f

            drawArc(
                color = when (cnt) {
                    0 -> Color(0xFFEA6751)
                    1 -> Color(0xFF5796C0)
                    2 -> Color(0xFFFECE54)
                    else -> Color(0xFF9D6297)
                },
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = true
            )

            startAngle += sweepAngle
            cnt += 1
        }
    }
}