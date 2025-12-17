package com.example.aborea.pages.home.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.aborea.pages.home.viewmodel.HomeViewModel
import com.example.aborea.pages.statistics.compose.Fruits

@Composable
fun StatsBar(time: HomeViewModel, index: Int) {
    Box(
        modifier = Modifier
            .width(20.dp)
            .height(350.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(time.timeStats[index])
                .clip(RoundedCornerShape(10.dp))
                .background(
                    color = Color(0xFFB5F065)
                )
        )
    }
}