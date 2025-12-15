package com.example.aborea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aborea.common.*
import com.example.aborea.pages.goal.*
import com.example.aborea.pages.home.*
import com.example.aborea.pages.statistics.*
import com.example.aborea.pages.store.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home1") {
                composable("home1") {
                    Home1(navController)
                }
                composable("home2") {
                    Home2(navController)
                }
                composable("goal1") {
                    Goal1(navController)
                }
                composable("goal2") {
                    Goal2(navController)
                }
                composable("statistics1") {
                    Statistics1(navController)
                }
                composable("statistics2") {
                    Statistics2(navController)
                }
                composable("store1") {
                    StoreScreen(navController)
                }
                /*
                store1 (파일명 StoreScreen) 에서 바로 store2 = 상세화면(StoreDetailScreen)으로
                넘어가는 구조라 MainActivity 에서 연결하지 않고 주석처리했습니다

                composable("store2") {
                    StoreDetailScreen()
                }       */

            }
        }
    }
}