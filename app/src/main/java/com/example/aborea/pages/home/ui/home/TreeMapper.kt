package com.example.aborea.pages.home.ui.home

import com.example.aborea.R

object TreeMapper {
    fun getTreeRes(treeName: String, stage: Int) : Int{
        return when(treeName){
            "참나무" -> when(stage){
                0 -> R.drawable.tree_default_0
                1 -> R.drawable.tree_default_1
                2 -> R.drawable.tree_default_2
                3 -> R.drawable.tree_default_3
                else -> R.drawable.tree_default_3
            }
            "벚꽃나무" -> when(stage){
                0 -> R.drawable.tree_cherry_0
                1 -> R.drawable.tree_cherry_1
                2 -> R.drawable.tree_cherry_2
                3 -> R.drawable.tree_cherry_3
                else -> R.drawable.tree_cherry_3
            }
            "단풍나무" -> when(stage){
                0 -> R.drawable.tree_maple_0
                1 -> R.drawable.tree_maple_1
                2 -> R.drawable.tree_maple_2
                3 -> R.drawable.tree_maple_3
                else -> R.drawable.tree_maple_3
            }
            "사과나무" -> when(stage){
                0 -> R.drawable.tree_apple_0
                1 -> R.drawable.tree_apple_1
                2 -> R.drawable.tree_apple_2
                3 -> R.drawable.tree_apple_3
                else -> R.drawable.tree_apple_3
            }
            "소나무" -> when(stage){
                0 -> R.drawable.tree_pine_0
                1 -> R.drawable.tree_pine_1
                2 -> R.drawable.tree_pine_2
                3 -> R.drawable.tree_pine_3
                else -> R.drawable.tree_pine_3
            }
            "편백나무" -> when(stage){
                0 -> R.drawable.tree_hinoki_0
                1 -> R.drawable.tree_hinoki_1
                2 -> R.drawable.tree_hinoki_2
                3 -> R.drawable.tree_hinoki_3
                else -> R.drawable.tree_hinoki_3
            }
            "이끼나무" -> when(stage){
                0 -> R.drawable.tree_moss_0
                1 -> R.drawable.tree_moss_1
                2 -> R.drawable.tree_moss_2
                3 -> R.drawable.tree_moss_3
                else -> R.drawable.tree_moss_3
            }
            "별나무" -> when(stage){
                0 -> R.drawable.tree_star_0
                1 -> R.drawable.tree_star_1
                2 -> R.drawable.tree_star_2
                3 -> R.drawable.tree_star_3
                else -> R.drawable.tree_star_3
            }
            "달나무" -> when(stage){
                0 -> R.drawable.tree_moon_0
                1 -> R.drawable.tree_moon_1
                2 -> R.drawable.tree_moon_2
                3 -> R.drawable.tree_moon_3
                else -> R.drawable.tree_moon_3
            }


            else -> when(stage) {
                0 -> R.drawable.tree_default_0
                1 -> R.drawable.tree_default_1
                2 -> R.drawable.tree_default_2
                3 -> R.drawable.tree_default_3
                else -> R.drawable.tree_default_3
            }
        }
    }
}