package com.example.aborea.pages.store

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update



// ViewModel 사용

class StoreViewModel(application: Application) : AndroidViewModel(application) {

    // StoreStorage (저장소) 연결
    private val storage = StoreStorage(application)

    // 내 나무 포인트
    private val _myTreePoint = MutableStateFlow(storage.getMyPoint())
    val myTreePoint = _myTreePoint.asStateFlow()

    // 화면에 보여줄 아이템 리스트
    private val _displayItems = MutableStateFlow<List<StoreItem>>(emptyList())
    val displayItems = _displayItems.asStateFlow()

    init {
        // 뷰모델 시작할 때 데이터 불러오기
        refreshData()
    }







    // 저장된 데이터 불러와서 리스트 갱신하기
    private fun refreshData() {
        val purchasedIds = storage.getPurchasedIds()
        // 이미 구매한 품목들 id 가져오기

        // 기본 리스트랑 비교해서 '산 것' 체크
        val newList = defaultItemList.map { item ->
            if (purchasedIds.contains(item.id.toString())) {
                item.copy(isPurchased = true) // 불변 객체 복사
            } else {
                item
            }
        }
        _displayItems.value = newList
    }







    // 아이템 구매 기능 함수
    fun buyItem(item: StoreItem) {
        val currentPoint = _myTreePoint.value

        // 구매 가능 조건 ( 포인트 충분 && 안 산거 )
        if (currentPoint >= item.price && !item.isPurchased) {

            // 포인트 차감
            val newPoint = currentPoint - item.price

            // 값 업데이트 & 저장
            _myTreePoint.value = newPoint
            storage.saveMyPoint(newPoint)
            storage.addPurchasedId(item.id)

            // 리스트 새로고침 (회색 표시 위해)
            refreshData()
        }
    }




// 이건 나중에 받아서 넣을 것들


    fun TimeToTreePoint(minutes: Int) {

        val earnedPoint = minutes / 60

        if (earnedPoint > 0) {
            val newPoint = _myTreePoint.value + earnedPoint

            _myTreePoint.value = newPoint
            storage.saveMyPoint(newPoint)
        }
    }


    // '열매' 리스트 받아서 그걸로도 결제할 수 있는 구조 만들기


}
