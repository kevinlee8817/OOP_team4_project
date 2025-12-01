package com.example.aborea.pages.home.compose

import android.graphics.Paint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.aborea.R
import com.example.aborea.pages.home.statistics
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aborea.common.*
import com.example.aborea.pages.home.Home1
import com.example.aborea.pages.home.Home2
import java.time.Period

//날짜 카테고리
//일 선택시, 버튼 생성 -> 날짜 선택 가능? OR 1일씩 이동
enum class PeriodType { DAY, WEEK, MONTH, YEAR}

@Composable
fun PeriodItem(
    name: String,
    type: PeriodType,
    selected: PeriodType,
    onSelect: (PeriodType) -> Unit
){
    val isSelected = selected == type // 이게 뭐지

    Box(
        modifier = Modifier
            .width(66.dp)
            .height(22.dp)
            .background(
                color =
                    if (isSelected) Color(0xFF46BC43)
                    else Color(0x0046BC43),
                    shape = RoundedCornerShape(50.dp)
            )
            .clickable { onSelect(type) }, //type을 받는 이유?
        contentAlignment = Alignment.Center
    ){
        OwnglyphText(name, 20, 0, 0,0xFFFFFFFF)
    }

}

//기간 카테고리
@Composable
fun PeriodCategory(
    selected: PeriodType,
    onSelect: (PeriodType) -> Unit
){
    Box(
        modifier = Modifier
            .width(264.dp)
            .height(22.dp)
            .background(Color(0xFFD9D9D9), shape = RoundedCornerShape(50.dp))
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            PeriodItem("일", PeriodType.DAY, selected, onSelect)
            PeriodItem("주", PeriodType.WEEK, selected, onSelect)
            PeriodItem("월", PeriodType.MONTH, selected, onSelect)
            PeriodItem("년", PeriodType.YEAR, selected, onSelect)
        }
    }
}
//날짜 바
@Preview
@Composable
fun PeriodBar(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(Color(0x66D9D9D9)),
        contentAlignment = Alignment.Center
    ){
        OwnglyphText("2025년 11월 6일 (오늘)", 20, 0, 0, 0xFF6A6A6A)
    }
}
//집중 숲
@Composable
fun focusForest(){
    //숲 베이스
    Image(
        modifier = Modifier
            .size(356.dp, 275.dp),
        painter = painterResource(id= R.drawable.forestbase),
        contentDescription = "forestbase"
    )
}
//집중 시간 그래프
@Composable
fun focusGraph(){
    Box(
        modifier = Modifier
            .width(381.dp)
            .height(342.dp)
            .background(Color(0xFFBCADA0), shape = RoundedCornerShape(38.dp)),
        contentAlignment = Alignment.Center,
    ){
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            OwnglyphText("집중 시간 분포도", 23, 0, 0, 0xFFF6F3EE)
            Box(
                modifier = Modifier
                    .width(318.04.dp)
                    .height(2.dp)
                    .background(Color.White.copy(alpha = 0.7f))
            )

            OwnglyphText("총 집중시간:  4시간 17분", 20, 0, 0, 0xFFF6F3EE)

            Image(
                modifier = Modifier
                    .width(330.dp)
                    .height(232.dp),
                painter = painterResource(id = R.drawable.graphbase),
                contentDescription = "graphbase"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun statisticsPreview(){
    val navController = rememberNavController()
    statistics(navController = navController)
}