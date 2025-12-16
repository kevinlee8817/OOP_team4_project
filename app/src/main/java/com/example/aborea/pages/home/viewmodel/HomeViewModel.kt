package com.example.aborea.pages.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.isActive
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {

    // 총 집중 시간 (초 단위)
    private val _elapsedSeconds = MutableStateFlow(0)
    val elapsedSeconds: StateFlow<Int> = _elapsedSeconds

    // 타이머 실행 상태
    val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning

    private var timerJob: Job? = null

    // ▶ 시작
    fun startTimer() {
        if (timerJob != null) return  // 이미 실행 중이면 무시

        _isRunning.value = true   // ⭐ 상태 전환

        timerJob = viewModelScope.launch {
            while (isActive) {
                delay(1000L)
                if(_elapsedSeconds.value != 18000){
                    _elapsedSeconds.value += 1
                }
                else{_elapsedSeconds.value = 18000}
            }
        }
    }

    // ⏹ 정지
    fun stopTimer() {
        timerJob?.cancel()
        timerJob = null

        _isRunning.value = false  // ⭐ 상태 전환
    }

    // UI용 시간 문자열
    fun formattedTime(): String {
        val total = _elapsedSeconds.value
        val h = total / 3600
        val m = (total % 3600) / 60
        val s = total % 60
        return String.format("%02d : %02d : %02d", h, m, s)
    }
}