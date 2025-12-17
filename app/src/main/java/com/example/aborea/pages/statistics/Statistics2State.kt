package com.example.aborea.pages.statistics

object Statistics2State {

    //카테고리 별 시간
    val timeByCategory = arrayListOf(0, 0, 0, 0)

    //카테고리 별 시간 누적
    fun addTime(categoryIndex: Int, seconds: Int){
        timeByCategory[categoryIndex]+=seconds
    }

    //집중시간 총합
    fun getTotalSeconds():Int {
        return timeByCategory.sum()
    }

    //이걸 쓸 수 있을까
    fun categoryToIndex(label: String): Int {
        return when (label) {
            "공부" -> 0
            "운동" -> 1
            "업무" -> 2
            "취미" -> 3
            else -> 0
        }
    }
}