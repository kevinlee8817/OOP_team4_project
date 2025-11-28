package com.example.aborea.common

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BasicFrame(cardWidth: Float, cardHeight: Float, offSetX: Int, offSetY: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth(cardWidth)
            .fillMaxHeight(cardHeight)
            .offset(x = offSetX.dp, y = offSetY.dp),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFBCADA0)
        )
    ) {

    }
}