package org.example.project.sanatanApp.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import org.example.project.core.presentation.Orange
import org.example.project.core.presentation.TextSize
import org.example.project.core.presentation.White
import org.jetbrains.compose.resources.Font
import sanatanapp.composeapp.generated.resources.Inter_24pt_Italic
import sanatanapp.composeapp.generated.resources.Res

@Composable
fun WhiteText(
    text: String,
    textSize: TextUnit = TextSize.regular,
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontFamily = FontFamily(
        Font(
            Res.font.Inter_24pt_Italic
        )
    )
) {
    Text(
        text = text,
        color = White,
        fontSize = textSize,
        fontStyle = fontStyle,
        fontFamily = fontWeight
    )
}

@Composable
fun OrangeText(
    text: String,
    textSize: TextUnit = TextSize.regular,
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontFamily = FontFamily(
        Font(
            Res.font.Inter_24pt_Italic
        )
    ),modifier :Modifier = Modifier
) {
    Text(
        text = text,
        color = Orange,
        fontSize = textSize,
        fontStyle = fontStyle,
        fontFamily = fontWeight,
        modifier = modifier
    )
}