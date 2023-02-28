package com.ruckycrewky.recepie

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RecipeCatalog(
    recipes: List<Recipe>,
    onClickMenu: () -> Unit,
    navController: NavController
) {
    Column {
        val onValueChangeDoNothing = { _: String -> {} }
        SearchBar(
            placeHolder = stringResource(id = R.string.recipe_search_placeholder),
            onValueChange = onValueChangeDoNothing, // TODO: временная заглушка
            leadingIcon = {
            Image(
                painter = painterResource(R.drawable.recipe),
                contentDescription = "Search",
                modifier = Modifier
                    .padding(15.dp)
                    .clickable { onClickMenu() }
            )
        })
        LazyVerticalGrid(
            columns = GridCells.Adaptive(180.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(recipes) {
                RecipeCard(
                    recipe = it,
                    modifier = Modifier
                        .height(150.dp)
                        .width(180.dp)
                        .clickable {
                            navController.navigate("recipe/${it.name}")
                        }
                )
            }
        }
    }
}
