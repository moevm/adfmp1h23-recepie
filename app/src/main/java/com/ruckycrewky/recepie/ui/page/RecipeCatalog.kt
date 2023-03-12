package com.ruckycrewky.recepie.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ruckycrewky.recepie.R
import com.ruckycrewky.recepie.ui.component.RecipeCard
import com.ruckycrewky.recepie.ui.component.SearchBar
import com.ruckycrewky.recepie.ui.viewmodel.RecipeCatalogViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RecipeCatalog(
    onClickMenu: () -> Unit,
    navController: NavController,
    viewModel: RecipeCatalogViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    Column {
        SearchBar(
            placeHolder = stringResource(id = R.string.recipe_search_placeholder),
            onSearchRequest = { viewModel.searchRecipes(it) }, // TODO: временная заглушка
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
            items(uiState.displayedRecipes) {
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
