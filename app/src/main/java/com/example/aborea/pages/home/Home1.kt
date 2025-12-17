package com.example.aborea.pages.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aborea.common.*
import com.example.aborea.pages.home.ui.home.HomeScreen
import com.example.aborea.pages.home.ui.home.buttonStatistics
import com.example.aborea.pages.home.ui.home.focusTime
import com.example.aborea.pages.home.ui.home.goalCategory
import com.example.aborea.pages.home.ui.home.progressBar
import com.example.aborea.pages.home.ui.home.showTree
import com.example.aborea.pages.home.ui.home.timerON

import com.example.aborea.pages.store.TimeToTreePointEffect // 이 부분 임포트했습니다 from store


@Composable
fun Home1(navController: NavController) {
    SetBackground()
    Column(
        modifier = Modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ){

        TimeToTreePointEffect()  // 여기 함수 하나 추가했습니다 from store

        //실제 홈화면
        HomeScreen(navController=navController)

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