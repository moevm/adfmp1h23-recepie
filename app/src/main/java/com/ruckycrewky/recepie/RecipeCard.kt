package com.ruckycrewky.recepie

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ruckycrewky.recepie.ui.theme.GrayCookTime
import com.ruckycrewky.recepie.ui.theme.HighRating
import com.ruckycrewky.recepie.ui.theme.Typography

@Composable
fun RecipeCard(
    recipe: Recipe,
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 1.dp,
        modifier = Modifier
            .height(150.dp)
            .width(180.dp)
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
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text("~${recipe.cookTime}", color = GrayCookTime)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        recipe.rating.toString(),
                        color = HighRating,
                        fontWeight = FontWeight.Bold
                    )
                    Image(
                        painter = painterResource(id = R.drawable.baseline_star_24),
                        contentDescription = "Звезда"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeCardPreview() {
    RecipeCard(recipe = defaultRecipe)
}