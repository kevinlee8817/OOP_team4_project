package com.example.aborea.pages.store

import android.content.Context
import com.example.aborea.R





//-------------------------------------------------------------------------------------



// StoreItem 데이터 클래스 정의
data class StoreItem(
    val id: Int,
    val name: String,
    val price: Int,
    val image: Int? = null,
    val isPurchased: Boolean = false
)




//-------------------------------------------------------------------------------------



// 아이템 리스트 (기본 데이터)
val defaultItemList = listOf(
    StoreItem(1, "대나무", 300, R.drawable.store_bamboo),
    StoreItem(2, "단풍나무", 300, R.drawable.store_maple),
    StoreItem(3, "흐릿나무", 300, R.drawable.store_justtree),
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












// 데이터 저장소 ( 영구 저장 및 관리 기능 )
// 안드로이드 SharedPreferences 사용

//-------------------------------------------------------------------------------------
class StoreStorage(context: Context) {

    // "store_save_file" 이라는 이름의 파일에 저장
    private val prefs = context.getSharedPreferences("store_save_file", Context.MODE_PRIVATE)



//-------------------------------------------------------------------------------------

    // 포인트 관리


    // 내 포인트 가져오기
    fun getMyPoint(): Int {
        val savedPoint = prefs.getInt("tree_point", 1000)
        return savedPoint
    }


    // 내 포인트 저장하기
    fun saveMyPoint(point: Int) {
        val editor = prefs.edit() // 수정 모드
        editor.putInt("tree_point", point)
        editor.apply() // 저장 확정
    }

//-------------------------------------------------------------------------------------



    // 구매한 아이템 목록 가져오기
    fun getPurchasedIds(): MutableSet<String> {
        // null일 경우를 대비해 빈 HashSet 반환
        val savedSet = prefs.getStringSet("purchased_ids", null)
        if (savedSet == null) {
            return HashSet<String>()
        }
        return savedSet
    }




    // 아이템 구매 기록 남기기
    fun addPurchasedId(id: Int) {
        // 기존 목록 호출
        val currentIds = getPurchasedIds()

        // 새로운 Set을 만들어서 추가
        val newIds = HashSet<String>(currentIds)
        newIds.add(id.toString())

        // 다시 저장
        val editor = prefs.edit()
        editor.putStringSet("purchased_ids", newIds)
        editor.apply()
    }
}
