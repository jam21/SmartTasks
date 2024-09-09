package com.smarttasks.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.smarttasks.R

// Set of Material typography styles to start with
val AmsiPro = FontFamily(
    Font(R.font.amsi_pro_regular, FontWeight.Normal), // Regular style
    Font(R.font.amsi_pro_bold, FontWeight.Bold)       // Bold style
)
val Typography = Typography(

    bodySmall = TextStyle(
        fontFamily = AmsiPro,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    titleLarge = TextStyle(
        fontFamily = AmsiPro,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    )
)