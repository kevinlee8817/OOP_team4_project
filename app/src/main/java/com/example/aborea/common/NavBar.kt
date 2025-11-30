package com.example.aborea.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aborea.R

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
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = {navController.navigate("home1")}, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                    Image(
                        modifier = Modifier
                            .size(63.dp)
                            .offset(y = -10.dp),
                        painter = painterResource(id = R.drawable.home),
                        contentDescription = "home button"
                    )
                }
                Button(onClick = {navController.navigate("goal1")}, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                    Image(
                        modifier = Modifier
                            .size(47.dp)
                            .offset(y = -10.dp),
                        painter = painterResource(id = R.drawable.goal),
                        contentDescription = "goal button"
                    )
                }
                Button(onClick = {navController.navigate("statistics1")}, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                    Image(
                        modifier = Modifier
                            .size(60.dp)
                            .offset(y = -10.dp),
                        painter = painterResource(id = R.drawable.statistics),
                        contentDescription = "statistics button"
                    )
                }
                Button(onClick = {navController.navigate("store1")}, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                    Image(
                        modifier = Modifier
                            .size(70.dp)
                            .offset(y = -10.dp),
                        painter = painterResource(id = R.drawable.store),
                        contentDescription = "store button"
                    )
                }
            }
        }
    }
}