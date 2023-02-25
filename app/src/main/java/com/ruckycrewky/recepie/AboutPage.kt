package com.ruckycrewky.recepie

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ruckycrewky.recepie.ui.theme.GrayCookTime


@Preview
@Composable
fun AboutPage(){

    Surface(
        color = Color(0xFFE9E9E9),
    ) {
        Column() {
            TopBar()
            Spacer(modifier = Modifier.height(10.dp))
            AboutInfo()
        }
    }

}

@Composable
fun TopBar(){
    val title = "О нас"
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White
    ) {
        Box(
            modifier = Modifier
        ) {
            Icon(Icons.Filled.Menu, "menu", modifier =  Modifier.padding(5.dp))
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
    val appName = "Recepie"
    val version = "Версия 0.1.0"
    val company = "Rucky Crewky Mobile"
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