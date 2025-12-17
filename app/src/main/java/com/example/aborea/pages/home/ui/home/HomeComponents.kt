package com.example.aborea.pages.home.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.aborea.R
import com.example.aborea.common.*
import com.example.aborea.pages.home.Home1

//btn_통계
@Composable
fun buttonStatistics(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
    ){
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                modifier = Modifier
                    .size(30.dp),
                painter = painterResource(id = R.drawable.btn_statistics),
                contentDescription = "btn_statistics"
            )
            OwnglyphText("통계", 18, 0, 0, 0xFF3A3A3A)
        }
    }
}

//btn_타이머 스탑
@Composable
fun buttontimerstop(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
    ){
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                modifier = Modifier.size(40.dp),
                        painter = painterResource(id = R.drawable.btn_timerstop),
                contentDescription = "timerstop"
            )
        }
    }
}

//집중 시간 text
@Composable
fun focusTime(
    focustime: Int
){
    Box(
        modifier = Modifier
            .padding(37.dp, 12.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        OwnglyphText("오늘의 집중 시간: ${focustime} 분", 32, 0, 0, 0xFF3A3A3A)
    }
}
@Composable
//카테고리
fun goalCategory(
    goalName: String
){
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Box(
                modifier = Modifier
                    .width(92.dp)
                    .height(34.dp)
                    .background(Color(0x80D9D9D9), shape = RoundedCornerShape(50.dp) ),
                contentAlignment = Alignment.Center
            ){
                Row(){
                    //아이콘
                    OwnglyphText("$goalName", 20, 0, 0, 0xFF6A6A6A)
                }
            }
        }
}
//나무 종류별 정의
enum class TreeType(val treetype: String) {
    Default("Default"),
    Maple("Maple")
}
//나무 단계별 정의
enum class TreeLevel(val treelevel: Int)
//show 나무
//추후 수정 필요 -> tree type, treelevel별 나무 제공 (전체 component에 써야 하나)

@Composable
fun showTree(
    treeName: String,
    stage: Int
){
    val treeResId = TreeMapper.getTreeRes(treeName,stage)

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        Image(
            modifier = Modifier.size(307.dp),
            painter = painterResource(id = treeResId),
            contentDescription = "tree"
        )
    }


}
//progress bar
@Composable
fun progressBar(
    percent: Int,
    treeName: String
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //글씨
        Row(
            modifier = Modifier
                .width(264.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ){
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.Bottom
            ){
                OwnglyphText("오늘의 첫번째", 15, 0, 0, 0xFF7A7A7A)
                OwnglyphText(treeName, 20, 0, 0, 0xFF3A3A3A)
            }
            OwnglyphText("$percent%", 20, 0, 0, 0xFF8FD036)
        }
        Spacer(modifier = Modifier.height(7.dp))
        //progress bar
        Box(
            modifier = Modifier
                .width(264.dp)
                .height(22.dp)
                .background(Color(0xFFD9D9D9), shape = RoundedCornerShape(50.dp)),
            contentAlignment = Alignment.CenterStart
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth(percent/100f)
                    .fillMaxHeight()
                    .background(Color(0xFF8FD036), shape = RoundedCornerShape(50.dp))
            )
        }
    }
}

//뽀모도로 스위치
@Composable
fun btnBbomodoro(){
    Box(
            modifier = Modifier
                .width(139.dp)
                .height(44.dp)
                .background(Color(0xFFFCFCFC), shape = RoundedCornerShape(50.dp))
                .padding(10.dp, 0.dp),
            contentAlignment = Alignment.Center
        ){
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ){
                OwnglyphText("뽀모도로", 23, 0, 0, 0xFF6A6A6A)
                //스위치
                Box(
                    modifier = Modifier
                        .width(52.dp)
                        .height(26.dp)
                        .background(Color(0xFFD9D9D9), shape = RoundedCornerShape(50.dp))
                        .padding(3.dp, 0.dp),
                    contentAlignment = Alignment.CenterStart
                ){
                    Box(
                        modifier = Modifier
                            .width(23.dp)
                            .height(23.dp)
                            .background(Color(0xFFB5F065), shape = RoundedCornerShape(50.dp)),
                    )
                }
            }
        }
    }


//타이머 버튼
@Composable
fun btnTimer(
    timeText: String,
    onStartClick: () -> Unit,
    onStopClick: () -> Unit
){
    Box(
        modifier = Modifier
            .width(271.dp)
            .height(51.dp)
            .background(Color(0xFFFCFCFC), shape = RoundedCornerShape(50.dp)),
        contentAlignment = Alignment.Center
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            //시작 아이콘
            Image(
                modifier = Modifier
                    .size(27.dp)
                    .clickable{ onStartClick() },
                painter = painterResource(id = R.drawable.btn_timerstart),
                contentDescription = "btn_timerstart"
            )
            //타이머 시간
            OwnglyphText(timeText, 32, 0,0, 0xFF7A7A7A)
        }
    }
}


//타이머
@Composable
fun timerON(
    timeText: String,
    onStartClick: () -> Unit,
    onStopClick: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 17.5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(9.dp)
    ){
        //뽀모도로 버튼
        btnBbomodoro()
        //타이머 버튼
        btnTimer(
            timeText = timeText,
            onStartClick = onStartClick,
            onStopClick = onStopClick
        )
    }
}

@Composable
fun RunningTimer(
    timeText: String,
    onStopClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OwnglyphText(timeText, 48, 0, 0, 0xFF7A7A7A)

        Spacer(modifier = Modifier.height(20.dp))

        Image(
            modifier = Modifier
                .size(48.dp)
                .clickable { onStopClick() },
            painter = painterResource(id = R.drawable.btn_timerstop),
            contentDescription = "stop"
        )
    }
}

//하단 바

@Preview(showBackground = true)
@Composable
fun Home1Preview() {
    val navController = rememberNavController()
    Home1(navController = navController)
}

