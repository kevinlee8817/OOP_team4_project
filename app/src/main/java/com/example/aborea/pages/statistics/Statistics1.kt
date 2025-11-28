package com.example.aborea.pages.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.aborea.common.*
import com.example.aborea.pages.statistics.compose.*

@Composable
fun Statistics1(navController: NavController) {
    SetBackground()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FruitTree()
        OwnglyphText(
            text = "목표 현황",
            size = 40,
            offSetX = -100,
            offSetY = 0,
            color = 0xFF6A6A6A
        )
        BasicFrame(
            0.90f,
            0.5f,
            0,
            10
        )
        NavBar(navController)
    }
}