package com.kangethe.chekicars.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.kangethe.chekicars.R

val Gilroy = FontFamily(
    Font(R.font.radomir_tinkov_gilroy_light),
    Font(R.font.radomir_tinkov_gilroy_extrabold, FontWeight.Bold)
)
// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = Gilroy,

    body1 = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h4 = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        textAlign = TextAlign.Center
    ),
    h5 = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        textAlign = TextAlign.Center
    )


    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
