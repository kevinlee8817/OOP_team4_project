package com.example.aborea.pages.statistics.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aborea.R

@Composable
fun FruitButton(status: Fruits, index: Int, offsetX: Int, offsetY: Int) {
    when(status.fruitForHarvest[index]) {
        "empty" -> Box{}
        "redfruit" -> Button(
            modifier = Modifier
                .offset(x = offsetX.dp, y = offsetY.dp),
            onClick = { status.harvest(index) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Image(
                modifier = Modifier
                    .size(50.dp),
                painter = painterResource(id = R.drawable.redfruit),
                contentDescription = "fruitTree",
                contentScale = ContentScale.Crop
            )
        }
        "bluefruit" -> Button(
            modifier = Modifier
                .offset(x = offsetX.dp, y = offsetY.dp),
            onClick = { status.harvest(index) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Image(
                modifier = Modifier
                    .size(50.dp),
                painter = painterResource(id = R.drawable.bluefruit),
                contentDescription = "fruitTree",
                contentScale = ContentScale.Crop
            )
        }
        "yellowfruit" -> Button(
            modifier = Modifier
                .offset(x = offsetX.dp, y = offsetY.dp),
            onClick = { status.harvest(index) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Image(
                modifier = Modifier
                    .size(50.dp),
                painter = painterResource(id = R.drawable.yellowfruit),
                contentDescription = "fruitTree",
                contentScale = ContentScale.Crop
            )
        }
        "purplefruit" -> Button(
            modifier = Modifier
                .offset(x = offsetX.dp, y = offsetY.dp),
            onClick = { status.harvest(index) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Image(
                modifier = Modifier
                    .size(50.dp),
                painter = painterResource(id = R.drawable.purplefruit),
                contentDescription = "fruitTree",
                contentScale = ContentScale.Crop
            )
        }
    }
}