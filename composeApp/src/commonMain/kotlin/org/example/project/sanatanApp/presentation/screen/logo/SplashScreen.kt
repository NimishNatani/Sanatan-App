package org.example.project.sanatanApp.presentation.screen.logo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.example.project.core.presentation.TextSize
import org.example.project.sanatanApp.presentation.components.WhiteText
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import sanatanapp.composeapp.generated.resources.Inter_24pt_SemiBoldItalic
import sanatanapp.composeapp.generated.resources.Res
import sanatanapp.composeapp.generated.resources.flag
import sanatanapp.composeapp.generated.resources.splashbackground

@Composable
fun SplashScreenRoot(onSplash: () -> Unit) {

    SplashScreen(onSplash = { onSplash() })
}

@Composable
fun SplashScreen(onSplash: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2500)
        onSplash()
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(Res.drawable.splashbackground),
            contentDescription = "Splash Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(Res.drawable.flag),
                contentDescription = "Splash Background",
                modifier = Modifier.size(140.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            WhiteText(text = "सत्य सनातन", textSize = TextSize.veryLarge, fontStyle = FontStyle.Italic,
                fontWeight = FontFamily(Font(Res.font.Inter_24pt_SemiBoldItalic))
            )
        }
    }
}