package com.example.aborea.pages.statistics.compose

class Fruits {
    val fruitForHarvest = arrayListOf<String>()
    val fruitContainer = arrayListOf<Int>()
    val fruitNum = arrayListOf<Int>()
    val fruitMap = mapOf(0 to "redfruit", 1 to "bluefruit", 2 to "yellowfruit", 3 to "purplefruit")
    init {
        for(i in 0 until 20)
            fruitForHarvest.add("redfruit")
        for(i in 0 until 4)
            fruitContainer.add(0)
        for(i in 0 until 4)
            fruitNum.add(0)
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
                    fruitContainer[cnt] += 1
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
                    fruitContainer[cnt] += 1
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
                    fruitContainer[cnt] += 1
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
                    fruitContainer[cnt] += 1
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
                    fruitContainer[cnt] += 1
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