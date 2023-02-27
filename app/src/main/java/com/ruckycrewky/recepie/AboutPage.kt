package com.ruckycrewky.recepie

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ruckycrewky.recepie.ui.theme.GrayCookTime
import com.ruckycrewky.recepie.ui.theme.GrayBackground

@Composable
fun AboutPage(
    onClickMenu: () -> Unit
){
    val title = stringResource(id = R.string.about_title)
    Surface(
        color = GrayBackground,
    ) {
        Column() {
            TopBar(title, onClickMenu)
            Spacer(modifier = Modifier.height(10.dp))
            AboutInfo()
        }
    }
}

@Composable
fun TopBar(
    title: String,
    onClickMenu: () -> Unit
){
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White
    ) {
        Box(
            modifier = Modifier
        ) {
            TextButton(
                onClick = { onClickMenu() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black)
            ) {
                Icon(Icons.Filled.Menu,
                    "menu",
                    modifier = Modifier.padding(5.dp))
            }
            Text(
                text = title,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            )
        }
    }
}

@Composable
fun AboutInfo(){
    val appName = stringResource(id = R.string.app_name)
    val version = stringResource(id = R.string.version)
    val company = stringResource(id = R.string.company)
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = appName,
                color = GrayCookTime
            )
            Text(
                textAlign = TextAlign.Center,
                text = version,
                color = GrayCookTime
            )
            Text(
                textAlign = TextAlign.Center,
                text = company,
                color = GrayCookTime
            )
        }
    }
}