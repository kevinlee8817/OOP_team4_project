package com.example.aborea.pages.store

import android.content.Context


/* store 에서 '구매한 나무 아이템 목록' 가져오는 함수입니다

제 StoreData 파일에 있는 StoreItem 데이터 클래스를 그대로 사용하되,
이미 보유 ( 구매 ) 한 항목에 대해서만 필터링하는 함수입니다.

import com.example.aborea.pages.store.getMyTreeList
import androidx.compose.ui.platform.LocalContext

이렇게 import 하시고

밑에 함수 사용하시면 됩니다.




fun Goal1 에

val context = LocalContext.current

하시고

val myTreeList = getMyTreeList(context)
이거 추가하시면
myTreeList 에는 구매한 나무들만 들어있을 겁니다.

 */



fun getMyTreeList(context: Context): List<StoreItem> {

    // 저장소 열기
    val storage = StoreStorage(context)

    // 구매한상품에 대해서만 가져오기
    val purchasedIds = storage.getPurchasedIds()

    return defaultItemList.filter { item ->
        // 기본 지급품 ( 흐릿나무 ) or 돈주고 산거
        item.isPurchased || purchasedIds.contains(item.id.toString())
    }
}


