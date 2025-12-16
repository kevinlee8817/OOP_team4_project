package com.example.aborea.pages.store

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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

// 폰트
val customFont = FontFamily(Font(R.font.ownglyph))

@Composable
fun StoreScreen(
    navController: NavController,
    currentBalance: Int = 400  // 일단 UI 상으로 확인하려고 만든 임시 잔고값
) {
    var selectedItem by remember { mutableStateOf<StoreItem?>(null) }

    // 화면 전환 로직
    if (selectedItem != null) {
        // 상세 화면으로 이동
        StoreDetailScreen(
            item = selectedItem!!,
            balance = currentBalance,
            onBackClick = { selectedItem = null },
            onBuyClick = {
                // 지금은 비워둠 (로직은 나중에)
            }
        )
    } else {

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
                        text = "🌳 $currentBalance",
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
                        items(StoreItemList) { item ->
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

// 개별 상품 카드
@Composable
fun StoreItemCard(
    item: StoreItem,
    onClick: () -> Unit
) {
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
                    text = "${item.name} : 🌳 ${item.price}",
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
                        .background(Color.Gray.copy(alpha = 0.5f)),
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

@Preview(showBackground = true)
@Composable
fun StoreScreenPreview() {
    MaterialTheme {
        val dummyNavController = rememberNavController()
        StoreScreen(navController = dummyNavController)
    }
}
