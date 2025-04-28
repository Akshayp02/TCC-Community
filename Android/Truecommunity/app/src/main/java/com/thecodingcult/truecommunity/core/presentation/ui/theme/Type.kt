package com.thecodingcult.truecommunity.core.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.thecodingcult.truecommunity.R

// Set of Material typography styles to start with

// Define Font Families
val NunitoFont = FontFamily(Font(R.font.nunito_regular)) // nunito-regular font
val PoppinsFont = FontFamily(Font(R.font.poppins))
val RobotoBoldFont = FontFamily(Font(R.font.roboto_bold))


// Define Custom Typography
val AppTypography = Typography(

    bodyLarge = TextStyle(
        fontFamily = NunitoFont,
        fontSize = 16.sp,
        color = TextbodyColor
    ),
    titleLarge = TextStyle(
        fontFamily = RobotoBoldFont,
        fontSize = 22.sp,
        color = TextHeadingColor,
        lineHeight = 22.sp,
        fontWeight = FontWeight(600),
        textAlign = TextAlign.Center,

        ),
    labelLarge = TextStyle(
        fontFamily = PoppinsFont,
        fontSize = 14.sp,
        color = TextColor2
    ),
    titleMedium = TextStyle(
        fontFamily = PoppinsFont,
        fontSize = 18.sp,
        fontWeight = FontWeight(600),
        color = TextHeadingColor,
    ),
)
