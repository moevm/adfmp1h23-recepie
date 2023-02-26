package com.ruckycrewky.recepie

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RecipeCatalog(
    recipes: List<Recipe>
) {
    Column {
        val leadingIcon = @Composable {
            Image(
                painter = painterResource(R.drawable.baseline_list_alt_24),
                contentDescription = "Search",
            )
        }
        SearchBar(placeHolder = "Введите название рецепта", leadingIcon = leadingIcon)
        LazyVerticalGrid(
            columns = GridCells.Adaptive(180.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(recipes) {
                RecipeCard(recipe = it)
            }
        }
    }
}

@Preview
@Composable
fun RecipeCatalogPreview() {
    RecipeCatalog(recipeSamples)
}

val recipeSamples = listOf(
    Recipe(
        "Губадия с курагой и очень вкусной посыпкой",
        4.5,
        "1 ч 45 мин",
        R.drawable.gubadiya,
    ),
    Recipe(
        "Блины",
        4.5,
        "35 мин",
        R.drawable.blin,
    ),
    Recipe(
        "Блины с икрой",
        5.0,
        "35 мин",
        R.drawable.blin_s_ikroy,
    ),
    Recipe(
        "Драники",
        5.0,
        "35 мин",
        R.drawable.draniki,
    ),
    Recipe(
        "Шарлотка",
        4.5,
        "1.5 ч",
        R.drawable.sharlotka,
    )
)