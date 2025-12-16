package com.example.aborea.pages.statistics.compose

import android.health.connect.datatypes.units.Percentage
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class Fruits {
    //변수 정의 및 초기화
    val fruitForHarvest = mutableStateListOf<String>()
    val fruitContainer = mutableStateListOf<Int>()
    val fruitNum = mutableStateListOf<Int>()
    val fruitMap = mapOf(0 to "redfruit", 1 to "bluefruit", 2 to "yellowfruit", 3 to "purplefruit")
    val percentage = mutableStateListOf<Float>()
    val progress = mutableStateListOf<Float>()

    init {
        for(i in 0 until 20)
            fruitForHarvest.add("empty")
        for(i in 0 until 4)
            fruitContainer.add(0)
        for(i in 0 until 4)
            fruitNum.add(0)
        for(i in 0 until 4)
            percentage.add(0f)
        for(i in 0 until 4)
            progress.add(0f)
    }

    //각 열매별 퍼센트 구하기
    fun getPercentage() {
        val total = fruitContainer.sum().toFloat()

        for(i in 0 until 4) {
            if(total != 0f) {
                percentage[i] = fruitContainer[i] / total
            } else {  }
        }
    }
    fun getProgress(timeList: ArrayList<Int>) {
        for(i in 0 until 4) {
            if(timeList[i] <= 18000){
                progress[i] = (timeList[i] / 18000).toFloat()
            } else {
                progress[i] = 1f
            }
        }
    }
    //클릭하면 수확해주는 기능
    fun harvest(i: Int) {
        when(fruitForHarvest[i]) {
            "redfruit" -> fruitContainer[0] += 1
            "bluefruit" -> fruitContainer[1] += 1
            "yellowfruit" -> fruitContainer[2] += 1
            "purplefruit" -> fruitContainer[3] += 1
            else -> { }
        }
        fruitForHarvest[i] = "empty"
    }
    //수확 가능 리스트에 열매 할당
    fun getFruits(timeList: ArrayList<Int>) {
        var cnt = 0
        for(i in timeList) {
            when {
                i in 3600 until 7200 && fruitNum[cnt] == 0 -> {
                    fruitNum[cnt] += 1
                    var idx = 0
                    for(j in fruitForHarvest) {
                        if(j == "empty") {
                            fruitForHarvest[idx] = fruitMap[idx] ?: "empty"
                            break
                        } else {
                            idx += 1
                            continue
                        }
                    }
                }
                i in 7200 until 10800 && fruitNum[cnt] == 1 -> {
                    fruitNum[cnt] += 1
                    var idx = 0
                    for(j in fruitForHarvest) {
                        if(j == "empty") {
                            fruitForHarvest[idx] = fruitMap[idx] ?: "empty"
                            break
                        } else {
                            idx += 1
                            continue
                        }
                    }
                }
                i in 10800 until 14400 && fruitNum[cnt] == 2 -> {
                    fruitNum[cnt] += 1
                    var idx = 0
                    for(j in fruitForHarvest) {
                        if(j == "empty") {
                            fruitForHarvest[idx] = fruitMap[idx] ?: "empty"
                            break
                        } else {
                            idx += 1
                            continue
                        }
                    }
                }
                i in 14400 until 18000 && fruitNum[cnt] == 3 -> {
                    fruitNum[cnt] += 1
                    var idx = 0
                    for(j in fruitForHarvest) {
                        if(j == "empty") {
                            fruitForHarvest[idx] = fruitMap[idx] ?: "empty"
                            break
                        } else {
                            idx += 1
                            continue
                        }
                    }
                }
                i >= 18000 && fruitNum[cnt] == 4 -> {
                    fruitNum[cnt] += 1
                    var idx = 0
                    for(j in fruitForHarvest) {
                        if(j == "empty") {
                            fruitForHarvest[idx] = fruitMap[idx] ?: "empty"
                            break
                        } else {
                            idx += 1
                            continue
                        }
                    }
                }
                else -> {  }
            }
        }
    }

}