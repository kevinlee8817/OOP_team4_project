package com.example.aborea.pages.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aborea.common.*
import com.example.aborea.pages.home.compose.*
import kotlin.concurrent.timer

@Composable
fun Home1(navController: NavController) {
    SetBackground()
    Column(){
        //통계 버튼
        buttonStatistics()
        //집중 시간 text
        focusTime()
        //나무
        showTree()

        //Spacer(modifier = Modifier.height(25.dp))
        //progress bar
        progressBar()
        //타이머 ON
        timerON()
        //하단 바
        NavBar(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun Home1Preview() {
    val navController = rememberNavController()
    Home1(navController = navController)
}