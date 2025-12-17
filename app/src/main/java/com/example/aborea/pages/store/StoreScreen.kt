package com.example.aborea.pages.store

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aborea.R

import com.example.aborea.common.SetBackground
import com.example.aborea.common.NavBar
import com.example.aborea.common.OwnglyphText

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.activity.compose.BackHandler

import com.example.aborea.pages.statistics.compose.Fruits


// 폰트
val customFont = FontFamily(Font(R.font.ownglyph))

@Composable
fun StoreScreen(
    navController: NavController,
    fruits: Fruits,
    viewModel: StoreViewModel = viewModel()     // 뷰모델 연결
) {








    // 뷰모델 데이터 실시간 업데이트
    val currentPoint by viewModel.myTreePoint.collectAsState()
    val items by viewModel.displayItems.collectAsState()

    // 클릭된 아이템을 저장하는 상태 변수 (null이면 목록 화면, 있으면 상세 화면)
    var selectedItem by remember { mutableStateOf<StoreItem?>(null) }

    /** if - else 로직으로 처리
     * 어차피 상품은 선택됐다 / 안됐다 둘 뿐이라서,
     * if - else 문으로 로직 구성하는 게
     * 좀 더 단순하고 직관적일 것 같아서 그렇게 했습니다.
     *
     * 선택(터치)된 상품 ->
     * StoreDetailScreen 으로 넘어감
     *
     * else 부분 ->
     * '일반적인 스토어 화면'
     *
     * 처음부터 내비게이션 없이 if - else 문으로 구조 짜다 보니,
     * StoreDetailScreen 에서 안드로이드 시스템 백버튼 누르면
     * 앱이 꺼지는 문제가 생길 수도 있어서
     * ( OS 입장에선 페이지 하나에만 머물러 있어서 그런 것 같음 )
     * 어쩔 수 없이 백핸들러 사용해서 처리했습니다.
     */

    // 선택된 아이템 있으면 StoreDetailScreen 으로 넘어감
    if (selectedItem != null) {

        BackHandler {
            selectedItem = null
        }

        val myBalance = when (selectedItem!!.currencyType) {
            CurrencyType.POINT -> currentPoint
            CurrencyType.FRUIT_RED -> fruits.fruitContainer[0]
            CurrencyType.FRUIT_BLUE -> fruits.fruitContainer[1]
            CurrencyType.FRUIT_YELLOW -> fruits.fruitContainer[2]
            CurrencyType.FRUIT_PURPLE -> fruits.fruitContainer[3]
        }

        StoreDetailScreen(
            item = selectedItem!!,
            balance = myBalance, // 포인트 대신 열매 개수가 넘어감
            onBackClick = { selectedItem = null },
            // viewModel에 fruits 객체도 같이 넘김
            onBuyClick = { viewModel.buyItem(selectedItem!!, fruits) }
        )
    }







    // 여기가 일반적인 스토어 화면 ( else 로 처리 )

    else {

        SetBackground()
        Box(modifier = Modifier.fillMaxSize()) {

            // 스크롤 가능 영역
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                // 상단 헤더
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    OwnglyphText(
                        text = "스토어",
                        size = 28,
                        offSetX = 0,
                        offSetY = 0,
                        color = 0xFF333333L
                    )

                    OwnglyphText(
                        text = "🌳 $currentPoint",
                        size = 24,
                        offSetX = 0,
                        offSetY = 0,
                        color = 0xFF333333L
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))








                // 아이템 리스트
                Box(modifier = Modifier.weight(1f)) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(bottom = 120.dp)
                    ) {
                        items(items.size) { index ->
                            val item = items[index]
                            StoreItemCard(
                                item = item,
                                onClick = { selectedItem = item }
                            )
                        }
                    }
                }
            }







            // 네비게이션 바
            Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                NavBar(navController = navController)
            }
        }
    }
}





//----------------------------------------------------------------------------

// 개별 상품 카드
@Composable
fun StoreItemCard(
    item: StoreItem,
    onClick: () -> Unit
) {


    // 화폐 아이콘 결정
    val currencyIcon = when(item.currencyType) {
        CurrencyType.POINT -> "🌳"
        CurrencyType.FRUIT_RED -> "🍎"
        CurrencyType.FRUIT_BLUE -> "🫐"
        CurrencyType.FRUIT_YELLOW -> "🍋"
        CurrencyType.FRUIT_PURPLE -> "🍇"
    }



    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.8f)
            .clip(RoundedCornerShape(20.dp))
            .clickable(enabled = !item.isPurchased) { onClick() }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // 이미지 박스
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    if (item.image != null) {
                        Image(
                            painter = painterResource(id = item.image),
                            contentDescription = item.name,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            contentScale = ContentScale.Fit
                        )
                    } else {
                        Spacer(modifier = Modifier.fillMaxSize())
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // 이름 및 가격
                Text(
                    text = "${item.name} : $currencyIcon ${item.price}",  // 화폐마다 아이콘 다르게
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontFamily = customFont
                )
            }

            // 구매 완료 표시
            if (item.isPurchased) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray.copy(alpha = 0.5f)), // 회색으로 비활성화
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "보유중",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontFamily = customFont
                    )
                }
            }
        }
    }
}


//----------------------------------------------------------------------------

// 미리보기
@Preview(showBackground = true)
@Composable
fun StoreScreenPreview() {
    MaterialTheme {
        val dummyNavController = rememberNavController()

        val dummyFruits = Fruits()

        StoreScreen(
            navController = dummyNavController,
            fruits = dummyFruits
        )
    }
}