package org.example.project.sanatanApp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.example.project.core.presentation.Gray
import org.example.project.core.presentation.Orange
import org.example.project.core.presentation.White
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenAction
import org.jetbrains.compose.resources.painterResource
import sanatanapp.composeapp.generated.resources.Res
import sanatanapp.composeapp.generated.resources.aarti
import sanatanapp.composeapp.generated.resources.notification

@Composable
fun TopBar(searchQuery:String,onSearchQueryChange:(String) -> Unit,profile:Boolean=false,onBackClick:()->Unit={}){
    Box(modifier = Modifier.fillMaxWidth().height(70.dp).background(Orange)) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 5.dp, start = 10.dp, end = 10.dp)
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (profile) {
                Image(
                    painter = painterResource(Res.drawable.aarti),
                    contentDescription = "profile",
                    modifier = Modifier.clip(CircleShape).size(50.dp).border(
                        1.dp,
                        White, CircleShape
                    ),
                    contentScale = ContentScale.Fit
                )
            }else{
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = Gray,
                        contentDescription = "arrow",
                        modifier = Modifier.size(30.dp).clickable { onBackClick() }
                    )
            }
            Spacer(modifier = Modifier.width(5.dp))
            SearchBar(
                searchQuery = searchQuery,
                onSearchQueryChange = {
                    onSearchQueryChange(it)
                },
                onImeSearch = { })

        }
    }
}