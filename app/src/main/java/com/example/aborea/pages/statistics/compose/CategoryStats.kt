package com.example.aborea.pages.statistics.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aborea.R
import com.example.aborea.common.OwnglyphText

@Composable
fun CategoryStats(status: Fruits) {
    status.getPercentage()
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalDivider(color = Color(0xFF6A6A6A))
        OwnglyphText("분야별 목표 달성 비율", 35, 0, 0, 0xFF6A6A6A)
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(60.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    modifier = Modifier
                        .size(40.dp)
                        .offset(y = 5.dp),
                    painter = painterResource(id = R.drawable.study),
                    contentDescription = "fruitTree",
                    contentScale = ContentScale.Crop
                )
                OwnglyphText("공부: ${status.percentage[0]*100f}%", 25, 40, 15, 0xFF6A6A6A)

            }
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(60.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    modifier = Modifier
                        .size(40.dp)
                        .offset(y = 5.dp),
                    painter = painterResource(id = R.drawable.sports),
                    contentDescription = "fruitTree",
                    contentScale = ContentScale.Crop
                )
                OwnglyphText("운동: ${status.percentage[1]*100f}%", 25, 40, 15, 0xFF6A6A6A)

            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(60.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    modifier = Modifier
                        .size(40.dp)
                        .offset(y = 5.dp),
                    painter = painterResource(id = R.drawable.work),
                    contentDescription = "fruitTree",
                    contentScale = ContentScale.Crop
                )
                OwnglyphText("업무: ${status.percentage[2]*100f}%", 25, 40, 15, 0xFF6A6A6A)

            }
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(60.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    modifier = Modifier
                        .size(40.dp)
                        .offset(y = 5.dp),
                    painter = painterResource(id = R.drawable.hobby),
                    contentDescription = "fruitTree",
                    contentScale = ContentScale.Crop
                )
                OwnglyphText("취미: ${status.percentage[3]*100f}%", 25, 40, 15, 0xFF6A6A6A)

            }
        }
        HorizontalDivider(color = Color(0xFF6A6A6A))
    }
}