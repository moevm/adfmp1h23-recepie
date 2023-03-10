package com.ruckycrewky.recepie.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ruckycrewky.recepie.R
import com.ruckycrewky.recepie.data.RecipeSearchResult
import com.ruckycrewky.recepie.data.recipeSearchResultSamples
import com.ruckycrewky.recepie.ui.theme.GrayCookTime
import com.ruckycrewky.recepie.ui.theme.HighRating
import com.ruckycrewky.recepie.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeSearchResultCard(
    recipe: RecipeSearchResult,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 1.dp,
        modifier = modifier
    ) {
        Column {
            Image(
                painter = painterResource(recipe.imageID),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(2f)
            )
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        recipe.name,
                        modifier = Modifier
                            .weight(5f),
                        style = Typography.titleLarge,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        modifier = Modifier
                            .weight(2f),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = recipe.cookTime,
                            color = GrayCookTime
                        )
                        Image(
                            painterResource(id = R.drawable.baseline_access_time_24),
                            "time",
                        )
                    }
                }
                Text(
                    modifier = Modifier
                        .padding(start = 10.dp),
                    style = Typography.titleSmall,
                    text = "Неиспользованные ингредиенты"
                )
                LazyRow(
                    modifier = Modifier
                        .padding(start=10.dp, end=10.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    items(items=recipe.unusedIngredients) {
                        SuggestionChip(
                            onClick = { /*TODO*/ },
                            label = { Text(it.name) },
                            modifier = Modifier
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        recipe.rating.toString(),
                        color = HighRating,
                        style = Typography.titleLarge,
                    )
                    Image(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(id = R.drawable.baseline_star_24),
                        contentDescription = "star"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun RecipeSearchCardPreview() {
    RecipeSearchResultCard(
        recipeSearchResultSamples[0],
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
    )
}