package com.example.aborea.pages.home

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aborea.common.*
import com.example.aborea.pages.home.ui.*
import com.example.aborea.R

@Composable
fun statistics(navController: NavController) {
    var period by remember { mutableStateOf(PeriodType.DAY) }

    SetBackground()
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(0.dp, 34.dp)
    ){
        //상단 바(홈화면 아이콘 + 날짜 카테고리)
        Row(
            modifier = Modifier
                .padding(25.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            //아이콘
            Image(
                painterResource(id=R.drawable.btn_returnhome),
                "홈으로 돌아가기",
                modifier = Modifier
                    .size(30.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                //날짜 카테고리
                PeriodCategory(selected = period, onSelect = { period = it } )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            //날짜 바
            PeriodBar()
            //집중 숲
            focusForest()
            //집중 시간 그래프
            focusGraph()
        }

    }
}

@Preview(showBackground = true)
@Composable
fun defaultStatisticsPreview() {
    val navController = rememberNavController()
    statistics(navController = navController)
}