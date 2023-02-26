package com.ruckycrewky.recepie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ruckycrewky.recepie.ui.theme.RecepieTheme

val defaultRecipe = Recipe("Губадия с курагой", 4.5, "40 мин", R.drawable.gubadiya)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecepieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        RecipeCard(defaultRecipe)
                    }
                }
            }
        }
    }
}