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