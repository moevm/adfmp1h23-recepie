package com.ruckycrewky.recepie

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RecipeSearchResultPage(
    result: List<RecipeSearchResult>,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val defaultButtonElevation = ButtonDefaults.buttonElevation(
        defaultElevation = 10.dp,
        pressedElevation = 15.dp,
        disabledElevation = 0.dp
    )

    Column {
        Button(
            onClick = {},
            elevation = defaultButtonElevation,
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),  // avoid the little icon
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
            ),
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, end = 10.dp),
        ) {
            Icon(
                Icons.Default.ArrowBack,
                tint = Color.Black,
                contentDescription = "back"
            )
        }
        LazyColumn(
            modifier = Modifier,
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            items(result) {
                RecipeSearchResultCard(
                    recipe = it,
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("recipe/${it.name}")
                        },
                )
            }
        }
    }
}