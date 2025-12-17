
package com.example.aborea.pages.home.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aborea.pages.goal.GoalState
import com.example.aborea.pages.statistics.Statistics2State
import kotlinx.coroutines.delay
@Composable
fun HomeScreen(
    navController: NavController
) {
    var isRunning by HomeTimerState.isRunning
    var elapsedSeconds by HomeTimerState.totalSeconds
    val stage = HomeTimerState.treeStage.value

    //라이프 사이클 오너?
    val lifecycleOwner = LocalLifecycleOwner.current

    val activeGoal = GoalState.goalList.find {it.isActive}

    val goalName = activeGoal?.name?:"기본"
    val goalTreeName = activeGoal?.treeName?:"참나무"

    //활성화 카테고리 받아오기
    fun getActiveCategoryIndex(): Int{
        val label = GoalState.goalList
            .find { it.isActive }
            ?.categoryLabel
            ?:"기본"

        return when (label) {
            "공부" -> 0
            "운동" -> 1
            "업무" -> 2
            "취미" -> 3
            else -> 0
        }
    }



    //앱 이탈 감지
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_STOP -> {
                    if (HomeTimerState.isRunning.value) {
                        HomeTimerState.exitCount += 1
                        HomeTimerState.exitStartSecond =
                            HomeTimerState.totalSeconds.value
                    }
                }

                Lifecycle.Event.ON_START -> {
                    val start = HomeTimerState.exitStartSecond
                    if (start != -1) {
                        val diff =
                            HomeTimerState.totalSeconds.value - start
                        if (diff > 0) {
                            HomeTimerState.exitSeconds += diff
                        }
                        HomeTimerState.exitStartSecond = -1
                    }
                }

                else -> {}
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(isRunning) {
        while (isRunning) {
            delay(1000L)
            HomeTimerState.totalSeconds.value++

            val categoryIndex = getActiveCategoryIndex()
            Statistics2State.timeByCategory[categoryIndex] += 1

        //tnwjd
        if(HomeTimerState.totalSeconds.value - HomeTimerState.lastCheckpoint >= 1800){
            HomeTimerState.lastCheckpoint = HomeTimerState.totalSeconds.value

            val stageNow = HomeTimerState.treeStage.value

            if(HomeTimerState.isWithered()){
                HomeTimerState.isRunning.value = false
                HomeTimerState.resetForNewTree()
                break
            }
            //시듦 판정
            else if (HomeTimerState.canGrow(stageNow)){
                    HomeTimerState.treeStage.value++
                    //나무 완성 -> 1그루 up
                    if (HomeTimerState.treeStage.value >= 4){

                        HomeTimerState.isRunning.value = false
                        //초기화
                        HomeTimerState.resetForNewTree()
                        break
                    }
            }
        }
    }
    }
    fun formattedTime(): String {
        val h = elapsedSeconds / 3600
        val m = (elapsedSeconds % 3600) / 60
        val s = elapsedSeconds % 60
        return String.format("%02d : %02d : %02d", h, m, s) //tnwjd
    }




    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
        ) {
            if (!isRunning) {
                buttonStatistics()
            }
        }
        val activeCategoryIndex = getActiveCategoryIndex()

        val todayFocusSeconds = Statistics2State.timeByCategory[activeCategoryIndex]
        focusTime(todayFocusSeconds / 60)
        goalCategory(goalName)
        showTree(treeName = goalTreeName, stage = stage )

        progressBar(
            percent = (elapsedSeconds * 100) / (120 * 60),
            treeName = "${goalTreeName}"
        )

        // 하단 타이머 영역
        if (!isRunning) {
            timerON(
                timeText = formattedTime(),
                onStartClick = { HomeTimerState.isRunning.value = true },
                onStopClick = {}
            )
        } else {
            RunningTimer(
                timeText = formattedTime(),
                onStopClick = {
                    //현재 활성 카테고리
                    val categoryIndex = getActiveCategoryIndex()

                    //누적 '후' 종료
                    HomeTimerState.isRunning.value = false

                    HomeTimerState.resetForNewTree()

                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GoalPreview() {
    HomeScreen(rememberNavController())
}