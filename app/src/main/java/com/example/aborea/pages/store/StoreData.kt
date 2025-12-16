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
    StoreItem(1, "참나무", 0, R.drawable.store_oak, isPurchased = true), // 기본지급
    StoreItem(2, "대나무", 300, R.drawable.store_bamboo),
    StoreItem(3, "단풍나무", 300, R.drawable.store_maple),
    StoreItem(4, "흐릿나무", 300, R.drawable.store_justtree),
    StoreItem(5, "별나무", 300, R.drawable.store_startree),
    StoreItem(6, "벚꽃나무", 300, R.drawable.store_cherryblossom),
    StoreItem(7, "달나무", 500, R.drawable.store_moontree),
    StoreItem(8, "반디나무", 400, R.drawable.store_fireflytree),
    StoreItem(9, "사과나무", 400, R.drawable.store_appletree),
    StoreItem(10, "소나무", 350, R.drawable.store_pine),
    StoreItem(11, "버드나무", 350, R.drawable.store_willow),
    StoreItem(12, "자작나무", 350, R.drawable.store_birch)
)












// 데이터 저장소 ( 영구 저장 및 관리 기능 )
/** 안드로이드 SharedPreferences 사용
 * 저장해야 할 데이터의 형태가 '포인트, 구매 목록' 같이 단순한 형태라
 * 가장 가벼운 SHaredPreferences를 사용했습니다.
 */

//-------------------------------------------------------------------------------------
class StoreStorage(context: Context) {

    // "store_save_file" 이라는 이름의 파일에 저장
    private val prefs = context.getSharedPreferences("store_save_file", Context.MODE_PRIVATE)



//-------------------------------------------------------------------------------------

    // 포인트 관리


    // 내 포인트 가져오기
    fun getMyPoint(): Int {
        val savedPoint = prefs.getInt("tree_point", 1000) // 일단 확인용으로 기본값 1000 설정
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







    // 추가됨: 보류 중인 집중 시간(초) 저장 및 불러오기
    fun getPendingFocusSeconds(): Int {
        return prefs.getInt("pending_focus_seconds", 0)
    }

    fun savePendingFocusSeconds(seconds: Int) {
        prefs.edit().putInt("pending_focus_seconds", seconds).apply()
    }



}
