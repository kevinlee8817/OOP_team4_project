package com.example.aborea

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aborea.ui.theme.AboreaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "fruits") {
                composable("fruits") {
                    PageFruits()
                }
                composable("fruitsStats") {
                    PageFruitsStats()
                }
            }
        }
    }
}

//김가은
@Composable
fun PageHome() {

}

@Composable
fun PageTreeStats() {

}

//심현솔
@Composable
fun PageGoal() {

}

@Composable
fun PageSetGoal() {

}

//이견우
@Composable
fun PageFruits() {
    SetBackground()
}

@Composable
fun PageFruitsStats() {

}

//김동현
@Composable
fun PageStore() {

}

@Composable
fun SetBackground() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFFEEFFDB) )
    )
}

@Preview(showBackground = true)
@Composable
fun BackPreview() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFFEEFFDB) )
    )

}

@Preview(showBackground = true)
@Composable
fun FramePreview(cardWidth: Float, ) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(16.dp)
            .fillMaxHeight(0.4f),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFBCADA0)
        )
    ) {
        Column() { }
    }
}