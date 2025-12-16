package com.example.aborea.pages.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aborea.common.*
import com.example.aborea.pages.home.ui.home.buttontimerstop
import com.example.aborea.pages.home.ui.home.focusTime
import com.example.aborea.pages.home.ui.home.progressBar
import com.example.aborea.pages.home.ui.home.showTree

@Composable
fun Home2(navController: NavController) {
    SetBackground()
    Column(){
        //timerstop 버튼
        buttontimerstop()
        //집중 시간 text

        //나무
        showTree()

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            //progress bar

            //누적 집중 시간
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                OwnglyphText("00 : 17 : 24", 60, 0, 0, 0xFF6A6A6A)
            }
            //하단 바
            NavBar(navController)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun m2Preview() {
    val navController = rememberNavController()
    Home2(navController = navController)
}