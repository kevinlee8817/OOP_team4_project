package com.example.aborea.pages.store

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.example.aborea.pages.statistics.compose.Fruits


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
    fun buyItem(item: StoreItem, fruits: Fruits?) {
        // 이미 샀으면 패스
        if (item.isPurchased) return

        // 1. 포인트(돈)로 사는 경우 (기존 로직)
        if (item.currencyType == CurrencyType.POINT) {
            val currentPoint = _myTreePoint.value
            if (currentPoint >= item.price) {
                val newPoint = currentPoint - item.price
                _myTreePoint.value = newPoint
                storage.saveMyPoint(newPoint)
                storage.addPurchasedId(item.id)
                refreshData()
            }
        }
        // 2. 열매로 사는 경우
        else if (fruits != null) {
            // 어떤 열매인지 확인 (0:빨강, 1:파랑, 2:노랑, 3:보라)
            val fruitIndex = when(item.currencyType) {
                CurrencyType.FRUIT_RED -> 0
                CurrencyType.FRUIT_BLUE -> 1
                CurrencyType.FRUIT_YELLOW -> 2
                CurrencyType.FRUIT_PURPLE -> 3
                else -> -1
            }

            if (fruitIndex != -1) {
                // 열매 개수 확인
                val currentFruitCount = fruits.fruitContainer[fruitIndex]

                // 열매 충분하면
                if (currentFruitCount >= item.price) {
                    // 아이템 구매 후 Fruits 에서 열매 개수 차감
                    fruits.fruitContainer[fruitIndex] = currentFruitCount - item.price

                    // 구매한 품목은 장기기억
                    storage.addPurchasedId(item.id)
                    refreshData()
                }
            }
        }
    }



    fun addFocusSeconds(deltaSeconds: Int) {
        if (deltaSeconds <= 0) return

        // pending 초(아직 포인트로 환산 안 된 잔여 초) 누적
        val pending = storage.getPendingFocusSeconds() + deltaSeconds

        /* 3600초(1시간)당 1포인트
        val earnedPoint = pending / 3600
        val remainder = pending % 3600
        */

        /* 60초 당 1 포인트
        일단 시연용으로 60초 당 1포인트씩 주는 걸로 환율 조정했습니다
         */
        val earnedPoint = pending / 60
        val remainder = pending % 60




        // 남은 초는 저장해서 다음에 이어서 누적
        storage.savePendingFocusSeconds(remainder)

        // 포인트가 생겼으면 포인트 업데이트 + 저장
        if (earnedPoint > 0) {
            val newPoint = storage.getMyPoint() + earnedPoint
            _myTreePoint.value = newPoint
            storage.saveMyPoint(newPoint)
        }
    }



    // '열매' 리스트 받아서 그걸로도 결제할 수 있는 구조 만들기


}
