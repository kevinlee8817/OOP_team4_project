package com.example.aborea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aborea.common.*
import com.example.aborea.pages.goal.*
import com.example.aborea.pages.home.*
import com.example.aborea.pages.statistics.*
import com.example.aborea.pages.statistics.compose.Fruits
import com.example.aborea.pages.store.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val status = remember { mutableStateOf(Fruits()) }
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
                    Statistics1(navController, status)
                }
                composable("statistics2") {
                    Statistics2(navController, status)
                }
                composable("store1") {
                    StoreScreen(navController, status.value)
                }
                //composable("store2") {
                //    Store2(navController)
                //}
            }
        }
    }
}