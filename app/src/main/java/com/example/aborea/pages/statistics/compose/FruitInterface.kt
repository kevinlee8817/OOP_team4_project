package com.example.aborea.pages.statistics.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FruitInterface(status: Fruits) {
    FruitButton(status, 19, 190, 60)
    FruitButton(status, 18, 125, 70)
    FruitButton(status, 17, 170, 95)
    FruitButton(status, 16, 90, 100)
    FruitButton(status, 15, 220, 100)
    FruitButton(status, 14, 50, 130)
    FruitButton(status, 13, 130, 130)
    FruitButton(status, 12, 170, 150)
    FruitButton(status, 11, 230, 150)
    FruitButton(status, 10, 100, 160)
    FruitButton(status, 9, 50, 180)
    FruitButton(status, 8, 150, 200)
    FruitButton(status, 7, 200, 200)
    FruitButton(status, 6, 250, 200)
    FruitButton(status, 5, 100, 210)
    FruitButton(status, 4, 50, 230)
    FruitButton(status, 3, 150, 250)
    FruitButton(status, 2, 200, 250)
    FruitButton(status, 1, 250, 250)
    FruitButton(status, 0, 100, 260)
}