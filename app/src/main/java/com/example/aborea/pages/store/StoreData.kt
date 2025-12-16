package com.example.aborea.pages.store
import com.example.aborea.R

// 품목들 관련 정보 따로 빼놨음


data class StoreItem(
    val id: Int,
    val name: String,
    val price: Int,
    val image: Int? = null,
    val isPurchased: Boolean = false
)


val StoreItemList = listOf(
    StoreItem(1, "대나무", 300, R.drawable.store_bamboo),
    StoreItem(2, "단풍나무", 300, image= R.drawable.store_maple),
    StoreItem(3, "흐릿나무", 300, image=R.drawable.store_justtree, isPurchased = true),
    StoreItem(4, "별나무", 300, image=R.drawable.store_startree),
    StoreItem(5, "벚꽃나무", 300, image=R.drawable.store_cherryblossom),
    StoreItem(6, "달나무", 500, image=R.drawable.store_moontree),
    StoreItem(7, "반디나무", 400, image= R.drawable.store_fireflytree),
    StoreItem(8, "사과나무", 400, image= R.drawable.store_appletree),
    StoreItem(id= 9, name= "소나무", price= 350, image= R.drawable.store_pine),
    StoreItem(id= 10, name= "버드나무", price= 350, image= R.drawable.store_willow),
    StoreItem(id= 11, name= "자작나무", price= 350, image= R.drawable.store_birch),
    StoreItem(id= 12, name= "참나무", price= 350, image= R.drawable.store_oak),

)



