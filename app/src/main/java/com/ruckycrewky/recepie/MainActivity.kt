package com.ruckycrewky.recepie

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ruckycrewky.recepie.ui.theme.GrayCookTime
import com.ruckycrewky.recepie.ui.theme.HighRating
import com.ruckycrewky.recepie.ui.theme.RecepieTheme
import com.ruckycrewky.recepie.ui.theme.Typography

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
                    val activity = LocalContext.current as Activity
                    activity.startActivity(Intent(activity,MealFeedbackActivity::class.java))
                    finish()
                }
            }
        }
    }
}
@Composable
fun RecipeCard(
    recipe: Recipe
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 1.dp,
        modifier = Modifier
            .width(180.dp)
            .height(150.dp)
    ) {
        Column {
            Image(
                painter = painterResource(recipe.imageID),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(90.dp)
            )
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                Row{
                    Text(
                        recipe.name,
                        style = Typography.labelSmall,
                        modifier = Modifier.weight(5f),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        recipe.rating.toString(),
                        modifier = Modifier.weight(1f),
                        color = HighRating,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Row {
                    Text("~${recipe.cookTime}", color = GrayCookTime)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeCardPreview() {
    val defaultRecipe = Recipe("Губадия с курагой", 4.5, "40 мин", R.drawable.gubadiya)
    RecipeCard(recipe = defaultRecipe)
}