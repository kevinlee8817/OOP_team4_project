package com.example.aborea.pages.store

import android.app.Application
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aborea.pages.home.viewmodel.HomeViewModel



// 집중 시간에 따라 나무 포인트 적립해주는 효과 ( Home 에서 받아옴 )

@Composable
fun TimeToTreePointEffect(homeViewModel: HomeViewModel) {
    val app = LocalContext.current.applicationContext as Application
    val storeViewModel: StoreViewModel =
        viewModel(factory = ViewModelProvider.AndroidViewModelFactory(app))

    val isRunning by homeViewModel.isRunning.collectAsState()
    val elapsedSeconds by homeViewModel.elapsedSeconds.collectAsState()

    var lastSeconds by remember { mutableStateOf(elapsedSeconds) }

    LaunchedEffect(isRunning) {
        lastSeconds = elapsedSeconds
    }

    LaunchedEffect(elapsedSeconds) {
        if (isRunning) {
            val delta = elapsedSeconds - lastSeconds
            if (delta > 0) storeViewModel.addFocusSeconds(delta)
            lastSeconds = elapsedSeconds
        }
    }
}





/*

Home1.kt 에서 이 부분를 아래와 같이 추가 한번만 해주실 수 있나요??

< import 부분 >
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aborea.pages.home.viewmodel.HomeViewModel
import com.example.aborea.pages.store.TimeToTreePoint

......


@Composable
fun Home1(navController: NavController) {
    SetBackground()

    val homeViewModel: HomeViewModel = viewModel()

    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TimeToTreePointEffect(homeViewModel)   <--------이거
        HomeScreen(navController = navController, viewModel = homeViewModel) <--------이거

        NavBar(navController)
    }
}

 */