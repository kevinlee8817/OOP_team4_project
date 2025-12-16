package com.example.aborea.pages.statistics.compose

import androidx.benchmark.traceprocessor.Row
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aborea.common.OwnglyphText

@Composable
fun SubNavBar(navController: NavController) {
    var route = navController.currentBackStackEntry?.destination?.route
    Card(
        modifier = Modifier
            .width(300.dp)
            .height(40.dp)
            .offset(y = -30.dp),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFCFCFC)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            when(route) {
                "statistics1" -> {
                    Button(
                        onClick = {navController.navigate("statistics1")},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF8FD036)
                        )
                    ) {
                        OwnglyphText("목표 현황", 25, 0, 0, 0xFFFCFCFC)
                    }
                    Button(
                        onClick = {navController.navigate("statistics2")},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFCFCFC)
                        )
                    ) {
                        OwnglyphText("목표 통계", 25, 0, 0, 0xFF6A6A6A)
                    }
                }
                "statistics2" -> {
                    Button(
                        onClick = {navController.navigate("statistics1")},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFCFCFC)
                        )
                    ) {
                        OwnglyphText("목표 현황", 25, 0, 0, 0xFF6A6A6A)
                    }
                    Button(
                        onClick = {navController.navigate("statistics2")},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF8FD036)
                        )
                    ) {
                        OwnglyphText("목표 통계", 25, 0, 0, 0xFFFCFCFC)
                    }
                }
                else -> {  }
            }
        }
    }
}