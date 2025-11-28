package com.example.aborea.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun NavBar(navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 100.dp)
            .offset(x = 0.dp, y = 30.dp),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFBCADA0)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Row {
                Button(onClick = {navController.navigate("home1")}) {
                    Text("홈")
                }
                Button(onClick = {navController.navigate("goal1")}) {
                    Text("목표")
                }
                Button(onClick = {navController.navigate("statistics1")}) {
                    Text("통계")
                }
                Button(onClick = {navController.navigate("store1")}) {
                    Text("상점")
                }
            }
        }
    }
}