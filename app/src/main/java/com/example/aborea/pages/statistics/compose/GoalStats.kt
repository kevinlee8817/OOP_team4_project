package com.example.aborea.pages.statistics.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.aborea.common.OwnglyphText

@Composable
fun GoalStats() {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.5f)
            .offset(y = 10.dp),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFBCADA0)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OwnglyphText("1", 20, 0, 0, 0xFFF6F3EE)
                OwnglyphText("2", 20, 0, 0, 0xFFF6F3EE)
                OwnglyphText("3", 20, 0, 0, 0xFFF6F3EE)
                OwnglyphText("4", 20, 0, 0, 0xFFF6F3EE)
                OwnglyphText("5", 20, 0, 0, 0xFFF6F3EE)
            }
            BarWithText("목표1", 0xFFEA6751)
            BarWithText("목표2", 0xFF4B90C0)
            BarWithText("목표3", 0xFFFECE54)
            BarWithText("목표4", 0xFF9D6297)
        }
    }
}