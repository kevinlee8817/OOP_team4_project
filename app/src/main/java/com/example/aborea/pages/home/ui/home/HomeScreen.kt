
package com.example.aborea.pages.home.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aborea.pages.home.Home1
import com.example.aborea.pages.home.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel()
) {
    val isRunning by viewModel.isRunning.collectAsState()
    val elapsedSeconds by viewModel.elapsedSeconds.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
        ) {
            if (!isRunning) {
                buttonStatistics()
            }
        }
        focusTime(elapsedSeconds / 60)
        goalCategory()
        showTree()

        progressBar(
            percent = (elapsedSeconds * 100) / (120 * 60),
            treeName = "참나무"
        )

        // 하단 타이머 영역
        if (!isRunning) {
            timerON(
                timeText = viewModel.formattedTime(),
                onStartClick = viewModel::startTimer,
                onStopClick = {}
            )
        } else {
            RunningTimer(
                timeText = viewModel.formattedTime(),
                onStopClick = viewModel::stopTimer
            )
        }
    }
}
