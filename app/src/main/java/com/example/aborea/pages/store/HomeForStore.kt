package com.example.aborea.pages.store

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModelProvider
import android.app.Application
import com.example.aborea.pages.home.ui.home.HomeTimerState // 타이머 상태 import








@Composable
fun TimeToTreePointEffect() {
    val app = LocalContext.current.applicationContext as Application
    val storeViewModel: StoreViewModel =
        viewModel(factory = ViewModelProvider.AndroidViewModelFactory(app))

    val isRunning by HomeTimerState.isRunning
    val elapsedSeconds by HomeTimerState.totalSeconds

    var lastSeconds by remember { mutableStateOf(elapsedSeconds) }

    // 타이머 상태가 바뀔 때(켜지거나 꺼질 때) 마지막 시간 초기화
    LaunchedEffect(isRunning) {
        lastSeconds = elapsedSeconds
    }

    // 시간 흐름 감지
    LaunchedEffect(elapsedSeconds) {
        if (isRunning) {
            val delta = elapsedSeconds - lastSeconds
            // 차이만큼 포인트 적립
            if (delta > 0) storeViewModel.addFocusSeconds(delta)

            lastSeconds = elapsedSeconds
        }
    }
}





/*
< Home1 파일에 >

import com.example.aborea.pages.store.TimeToTreePointEffect <---- 이거 import 해주시고

@Composable
fun Home1(navController: NavController) {
    SetBackground()
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ){

        TimeToTreePointEffect() <----------이거 하나만 추가해주시면 됩니다

        //실제 홈화면
        HomeScreen(navController=navController)

        //하단 바
        NavBar(navController)
    }
}

 */