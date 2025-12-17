package com.example.aborea.pages.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aborea.common.NavBar
import com.example.aborea.common.OwnglyphText
import com.example.aborea.common.SetBackground
import com.example.aborea.pages.home.Home1
import com.example.aborea.pages.statistics.compose.FruitTree
import com.example.aborea.pages.statistics.compose.Fruits
import com.example.aborea.pages.statistics.compose.GoalStats
import com.example.aborea.pages.statistics.compose.SubNavBar

@Composable
fun Statistics1(navController: NavController, status: MutableState<Fruits>) {
    SetBackground()
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.offset(y = 100.dp)
        ) {
            SubNavBar(navController)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FruitTree(status.value)
        OwnglyphText(text = "목표 현황", size = 40, offSetX = -100, offSetY = 0, color = 0xFF6A6A6A)
        GoalStats(status.value)
        NavBar(navController)
    }
}