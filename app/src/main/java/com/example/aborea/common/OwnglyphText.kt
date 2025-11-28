package com.example.aborea.common

import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aborea.R

@Composable
fun OwnglyphText(text: String, size: Int, offSetX: Int, offSetY: Int, color: Long) {
    Text(
        modifier = Modifier
            .offset(x = offSetX.dp, y = offSetY.dp),
        text = text,
        color = Color(color),
        fontSize = size.sp,
        fontFamily = FontFamily(Font(R.font.ownglyph))
    )
}