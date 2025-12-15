package com.example.aborea.pages.store

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aborea.R
import com.example.aborea.common.SetBackground

// 폰트 설정
val detailFont = FontFamily(Font(R.font.ownglyph))


/** StoreDetailScreen 은 view 역할만
 * StoreDetailScreen 이 가져야 하는 기능 :
 * 상품 확대된 화면 보여주기, (잔액에 따른) 구매 가능 여부 판단하기,
 * 원래 화면 ( StoreScreen ) 으로 돌아가기,
 * 구매하기 ( 클릭 시 onBuyClick 호출 ) 뿐
 * 스토어 기능 특성상 데이터 ( 잔고, 상품 보유 여부 ) 가 변하는데,
 * 이걸 페이지 두 개에서 동시에 관리하기 힘들 것 같아서
 * 차라리 'StoreDetailScreen' 은 순수하게 '화면' 역할만 하도록 하고,
 * 나머지 모든 관리는 StoreScreen 쪽에서 하도록 했습니다.
 * ( StoreDetailScreen 은 라우팅에서도 뺐습니다 )
 */


@Composable
fun StoreDetailScreen(
    item: StoreItem,
    balance: Int,
    onBackClick: () -> Unit,
    onBuyClick: () -> Unit
) {




    // 내 잔고가 가격보다 많으면 구매 가능 버튼 활성화 -> 구매 가능 여부를 여기서 확인
    val canAfford = balance >= item.price

    Box(modifier = Modifier.fillMaxSize()) {

        SetBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {




            // 상단 바
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {


                // 돌아가기 버튼 ( StoreScreen 으로 돌아가짐 )
                Text(
                    text = "⬅ 돌아가기",
                    fontSize = 24.sp,
                    fontFamily = detailFont,
                    color = Color(0xFF333333),
                    modifier = Modifier.clickable { onBackClick() }
                )


                // 잔고 보여주는 텍스트
                Text(
                    text = "🌳 $balance",
                    fontSize = 24.sp,
                    fontFamily = detailFont,
                    color = Color(0xFF333333)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))







            // 이미지 영역
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                if (item.image != null) {
                    Image(
                        painter = painterResource(id = item.image),
                        contentDescription = item.name,
                        modifier = Modifier.fillMaxSize().padding(30.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // 상품 정보
            Text(
                text = "${item.name} : 🌳 ${item.price}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = detailFont,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(20.dp))







            // 구매 버튼 관련
            Button(

                // 클릭하면
                onClick = {
                    onBuyClick() // 구매하고
                    onBackClick() // 바로 StoreScreen 으로 복귀
                },

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFA1887F),
                    disabledContainerColor = Color.Gray
                ),

                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),

                // 돈 없으면 버튼 비활성화 처리
                enabled = canAfford
            )





            {
                Text(    // 구매 버튼에 뜨는 텍스트 결정 ( 구매 & 잔액 부족 )
                    text = if (canAfford) "구 매" else "잔액 부족",
                    fontSize = 30.sp,
                    fontFamily = detailFont,
                    color = Color.White
                )
            }
        }
    }
}

