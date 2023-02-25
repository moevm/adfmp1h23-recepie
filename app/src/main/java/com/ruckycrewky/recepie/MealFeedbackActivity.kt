package com.ruckycrewky.recepie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ruckycrewky.recepie.ui.theme.RecepieTheme
import com.ruckycrewky.recepie.ui.theme.Typography

class MealFeedbackActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecepieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(){
                        RecipeTitle(recipe = Recipe("Губадия с курагой", 4.5, "40 мин", R.drawable.gubadiya))
                    }
                }
            }
        }
    }
}

@Composable
fun RecipeTitle(
    recipe: Recipe
) {
    Box(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth()
    ){
        Box(
            contentAlignment = Center,
            modifier = Modifier
                .wrapContentSize()
                .fillMaxSize()
                .padding(start = 40.dp)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = recipe.name,
                    style = Typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .width(200.dp)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = stringResource(R.string.feedbackLabel),
                    style = Typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
        Image(
            painter = painterResource(recipe.imageID),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(90.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeTitlePreview() {
    val defaultRecipe = Recipe("Губадия с курагой с курагой с курагой", 4.5, "40 мин", R.drawable.gubadiya)
    RecipeTitle(recipe = defaultRecipe)
}



