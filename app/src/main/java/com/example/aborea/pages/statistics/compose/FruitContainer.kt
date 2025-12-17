package com.example.aborea.pages.statistics.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.aborea.R
import com.example.aborea.common.OwnglyphText

@Composable
fun FruitContainer(status: Fruits) {
    val fruits = mapOf("붉은열매" to R.drawable.redfruit, "푸른열매" to R.drawable.bluefruit, "황금열매" to R.drawable.yellowfruit, "자색열매" to R.drawable.purplefruit)
    Card(
        modifier = Modifier
            .fillMaxWidth(0.4f)
            .fillMaxHeight(0.08f)
            .offset(y = 10.dp),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFBCADA0)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OwnglyphText("열매 보관함", 30, 0, 0, 0xFFFCFCFC)
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.35f)
            .offset(y = 10.dp),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFBCADA0)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                        .background(color = Color(0xFFFCFCFC))
                ) {
                    Image(
                        modifier = Modifier
                            .size(50.dp)
                            .offset(y = 5.dp),
                        painter = painterResource(id = R.drawable.redfruit),
                        contentDescription = "fruitTree",
                        contentScale = ContentScale.Crop
                    )
                    OwnglyphText("붉은열매\n  ${status.fruitContainer[0]}개", 25, 55, 7, 0xFF6A6A6A)

                }
                Box(
                    modifier = Modifier
                        .width(150.dp)
                        .height(60.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = Color(0xFFFCFCFC))
                ) {
                    Image(
                        modifier = Modifier
                            .size(50.dp)
                            .offset(y = 5.dp),
                        painter = painterResource(id = R.drawable.bluefruit),
                        contentDescription = "fruitTree",
                        contentScale = ContentScale.Crop
                    )
                    OwnglyphText("푸른열매\n  ${status.fruitContainer[1]}개", 25, 55, 7, 0xFF6A6A6A)

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
                        .background(color = Color(0xFFFCFCFC))
                ) {
                    Image(
                        modifier = Modifier
                            .size(50.dp)
                            .offset(y = 5.dp),
                        painter = painterResource(id = R.drawable.yellowfruit),
                        contentDescription = "fruitTree",
                        contentScale = ContentScale.Crop
                    )
                    OwnglyphText("황금열매\n  ${status.fruitContainer[2]}개", 25, 55, 7, 0xFF6A6A6A)

                }
                Box(
                    modifier = Modifier
                        .width(150.dp)
                        .height(60.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = Color(0xFFFCFCFC))
                ) {
                    Image(
                        modifier = Modifier
                            .size(50.dp)
                            .offset(y = 5.dp),
                        painter = painterResource(id = R.drawable.purplefruit),
                        contentDescription = "fruitTree",
                        contentScale = ContentScale.Crop
                    )
                    OwnglyphText("자색열매\n  ${status.fruitContainer[3]}개", 25, 55, 7, 0xFF6A6A6A)

                }
            }
        }
    }
}