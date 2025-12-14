package com.example.aborea.pages.store

import android.content.Context
import com.example.aborea.R

// [1] 데이터 껍데기 (DTO)
data class StoreItem(
    val id: Int,
    val name: String,
    val price: Int,
    val image: Int? = null,
    val isPurchased: Boolean = false
)

// [2] 기본 데이터 리스트 (앱 처음 켰을 때 기준)
val defaultItemList = listOf(
    StoreItem(1, "대나무", 300, R.drawable.store_bamboo),
    StoreItem(2, "단풍나무", 300, R.drawable.store_maple),
    StoreItem(3, "흐릿나무", 300, R.drawable.store_justtree, isPurchased = true), // 기본 지급
    StoreItem(4, "별나무", 300, R.drawable.store_startree),
    StoreItem(5, "벚꽃나무", 300, R.drawable.store_cherryblossom),
    StoreItem(6, "달나무", 500, R.drawable.store_moontree),
    StoreItem(7, "반디나무", 400, R.drawable.store_fireflytree),
    StoreItem(8, "사과나무", 400, R.drawable.store_appletree),
    StoreItem(9, "소나무", 350, R.drawable.store_pine),
    StoreItem(10, "버드나무", 350, R.drawable.store_willow),
    StoreItem(11, "자작나무", 350, R.drawable.store_birch),
    StoreItem(12, "참나무", 350, R.drawable.store_oak)
)

// [3] 데이터 저장소 (내부 저장소 관리자)
// -> 데이터를 영구적으로 저장하고 불러오는 역할
class StoreStorage(context: Context) {

    // "store_save_file" 이라는 이름의 파일에 저장
    private val prefs = context.getSharedPreferences("store_save_file", Context.MODE_PRIVATE)

    // --- 포인트(돈) 관리 ---

    fun getMyPoint(): Int {
        return prefs.getInt("tree_point", 0) // 없으면 0원
    }

    fun saveMyPoint(point: Int) {
        prefs.edit().putInt("tree_point", point).apply()
    }

    // --- 구매 내역 관리 ---

    // 구매한 아이템 ID 목록 가져오기 (예: "1,3,5")
    fun getPurchasedIds(): MutableSet<String> {
        return prefs.getStringSet("purchased_ids", mutableSetOf()) ?: mutableSetOf()
    }

    // 아이템 구매 기록 남기기
    fun addPurchasedId(id: Int) {
        val currentIds = getPurchasedIds()
        currentIds.add(id.toString())
        prefs.edit().putStringSet("purchased_ids", currentIds).apply()
    }
}
