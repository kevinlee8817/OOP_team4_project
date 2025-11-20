package com.example.aborea

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.foundation.layout.offset
import androidx.compose.ui.text.font.Font
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {

                }
                composable("treeStats") {

                }
                composable("goal") {

                }
                composable("setGoal") {

                }
                composable("fruits") {
                    PageFruits()
                }
                composable("fruitsStats") {
                    PageFruitsStats()
                }
                composable("store") {

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
        NavBar()
    }
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

@Composable
fun OwnglyphText(text: String, size: Int, offSetX: Int, offSetY: Int, color: Long) {
    Text(
        modifier = Modifier
            .offset(x = offSetX.dp, y = offSetY.dp),
        text = text,
        color = Color(color),
        fontSize = size.sp,
        fontFamily = FontFamily(Font(R.font.ownglyph))
    )
}

@Composable
fun BasicFrame(cardWidth: Float, cardHeight: Float, offSetX: Int, offSetY: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth(cardWidth)
            .fillMaxHeight(cardHeight)
            .offset(x = offSetX.dp, y = offSetY.dp),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFBCADA0)
        )
    ) {

    }
}

@Composable
fun NavBar() {
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

    }
}

@Composable
fun FruitTree() {
    Image(
        modifier = Modifier
            .size(450.dp),
        painter = painterResource(id = R.drawable.fruittree),
        contentDescription = "fruitTree",
        contentScale = ContentScale.Crop
    )
}