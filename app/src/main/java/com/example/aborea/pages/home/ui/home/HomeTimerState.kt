package com.example.aborea.pages.home.ui.home

import androidx.compose.runtime.mutableStateOf
import com.example.aborea.pages.goal.GoalState

object HomeTimerState{

    //타이머 상태--------------------------------
    var totalSeconds =mutableStateOf(0)
    var isRunning = mutableStateOf(false)
    //세션 체크
    var lastCheckpoint = 0

    //성장 단계
    var treeStage = mutableStateOf(0)

    //검증 변수----------------------------------
    var exitCount = 0
    var exitSeconds = 0
    var forceCloseCount = 0

    //이탈 시작 시간
    var exitStartSecond = -1



    //타이머 초기화
    fun resetForNewTree(){
        treeStage.value = 0
        totalSeconds.value = 0
        exitCount = 0
        exitSeconds = 0
        forceCloseCount = 0
        lastCheckpoint = 0
    }

    //검증 함수
    fun canGrow(stage: Int): Boolean {
        return when (stage){
            0 -> HomeTimerState.exitCount <= 3 &&
                    HomeTimerState.exitSeconds <= 15 * 60 &&
                    HomeTimerState.forceCloseCount <= 2
            1 -> HomeTimerState.exitCount <= 3 &&
                    HomeTimerState.exitSeconds <= 10 * 60 &&
                    HomeTimerState.forceCloseCount <= 1
            2 -> HomeTimerState.exitCount <= 2 &&
                    HomeTimerState.exitSeconds <= 10 * 60 &&
                    HomeTimerState.forceCloseCount <= 1
            3 -> HomeTimerState.exitCount <= 1 &&
                    HomeTimerState.exitSeconds <= 5 * 60 &&
                    HomeTimerState.forceCloseCount <= 0
            else -> false
        }
    }

    //시든 나무 이벤트
    fun isWithered(): Boolean {
        return HomeTimerState.exitCount >= 5 ||
                HomeTimerState.exitSeconds >= 20 * 60 ||
                HomeTimerState.forceCloseCount >= 5
    }

}