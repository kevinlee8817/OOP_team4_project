package com.example.aborea.pages.statistics.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.aborea.R

@Composable
fun FruitTree(status: Fruits) {
    Box {
        Image(
            modifier = Modifier
                .size(450.dp),
            painter = painterResource(id = R.drawable.fruittree),
            contentDescription = "fruitTree",
            contentScale = ContentScale.Crop
        )
        FruitInterface(status)
    }
}