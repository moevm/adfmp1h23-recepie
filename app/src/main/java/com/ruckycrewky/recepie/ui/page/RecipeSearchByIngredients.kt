package com.ruckycrewky.recepie.ui.page

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ruckycrewky.recepie.R
import com.ruckycrewky.recepie.data.Ingredient
import com.ruckycrewky.recepie.data.IngredientCategory
import com.ruckycrewky.recepie.ui.component.SearchBar
import com.ruckycrewky.recepie.ui.component.SimpleCard
import com.ruckycrewky.recepie.ui.theme.FindReceiptButtonColor
import com.ruckycrewky.recepie.ui.viewmodel.RecipeSearchByIngredientsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RecipeSearchByIngredients(
    onClickMenu: () -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: RecipeSearchByIngredientsViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    val defaultButtonElevation = ButtonDefaults.buttonElevation(
        defaultElevation = 10.dp,
        pressedElevation = 15.dp,
        disabledElevation = 0.dp
    )

    val modifierWithDefaultWidth = Modifier
        .padding(start = 10.dp, end = 10.dp)
        .fillMaxWidth()

    BackHandler(onBack = { viewModel.unselectCategory() })
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SearchBar(
            placeHolder = stringResource(id = R.string.ingredient_search_placeholder),
            onSearchRequest = { viewModel.searchIngredients(it) },
            leadingIcon = {
                Image(
                    painter = painterResource(R.drawable.carrot),
                    contentDescription = "search",
                    modifier = Modifier
                        .padding(15.dp)
                        .clickable { onClickMenu() }
                )
            }
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifierWithDefaultWidth
                .height(50.dp)
        ) {
            Button(
                onClick = { viewModel.unselectCategory() },
                enabled = (uiState.chosenIngredientsCategory != ""),
                elevation = defaultButtonElevation,
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),  // avoid the little icon
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                ),
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    tint = Color.Black,
                    contentDescription = "back"
                )
            }
            Button(
                onClick = {
                    viewModel.toggleShowIngredients()
                },
                shape = RoundedCornerShape(22.dp),
                elevation = defaultButtonElevation,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                ),
            ) {
                var message = stringResource(id = R.string.ingredient_search_show_chosen_ingredients)
                if (uiState.showChosenIngredients) {
                    message = stringResource(id = R.string.ingredient_search_hide_chosen_ingredients)
                }
                Text(
                    message,
                    fontSize = 10.sp
                )
            }
            InvisibleButton()
        }

        if (uiState.showChosenIngredients) {
            ChosenIngredients(
                ingredients = uiState.chosenIngredients,
                onDeleteIconClick = { ingredient ->
                    viewModel.removeIngredient(ingredient.name)
                }
            )
        }

        if (uiState.chosenIngredientsCategory != "") {
            IngredientsCatalog(
                availableIngredients = viewModel.getIngredientsOfChosenCategory(),
                onIngredientClick = { ingredient ->
                    viewModel.addIngredient(ingredient)
                }
            )
        }
        else {
            // Show categories of ingredients
            IngredientCategoriesCatalog(
                categories = viewModel.getIngredientCategories(),
                onCategoryClick = { ingredientCategory ->
                    viewModel.selectCategory(ingredientCategory.name)
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            enabled = uiState.chosenIngredients.isNotEmpty(),
            elevation = defaultButtonElevation,
            modifier = modifierWithDefaultWidth,
            colors = ButtonDefaults.buttonColors(
                containerColor = FindReceiptButtonColor,
                contentColor = Color.White,
            ),
            onClick = {
                val chosenIngredientName = uiState.chosenIngredients.map{it.name}
                val chosenIngredientNameString = chosenIngredientName.joinToString(",")
                navController.navigate("recipe-search-result/${chosenIngredientNameString}")
            }
        ) {
            var message = stringResource(id = R.string.ingredient_search_find_recipes)
            if (uiState.chosenIngredients.isEmpty())
                message = stringResource(id = R.string.ingredient_search_user_must_chose_ingredient)
            Text(message)
        }
    }
}

@Composable
fun ChosenIngredients(
    ingredients: List<Ingredient>,
    onDeleteIconClick: (Ingredient) -> Unit,
    modifier: Modifier = Modifier,
) {
    for (ingredient in ingredients) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(40.dp)
                .padding(start = 60.dp, end = 60.dp, top = 10.dp, bottom = 0.dp)
                .fillMaxWidth()
        ) {
            Image(
                painterResource(id = ingredient.imageID),
                contentDescription = ingredient.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
            )
            Text(
                text = ingredient.name,
                modifier = Modifier
                    .padding(start = 10.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "delete",
                modifier = Modifier
                    .clickable(
                        onClick = { onDeleteIconClick(ingredient) }
                    )
            )
        }
    }
}

@Composable
fun IngredientsCatalog(
    availableIngredients: List<Ingredient>,
    onIngredientClick: (ingredient: Ingredient) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (availableIngredients.isEmpty()) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.ingredient_search_no_more_ingredients_in_this_category),
                modifier = Modifier
            )
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(100.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(availableIngredients) {
            SimpleCard(
                it.name,
                painterResource(id = it.imageID),
                modifier = Modifier
                    .height(150.dp)
                    .clickable(onClick = { onIngredientClick(it) })
            )
        }
    }
}

@Composable
fun IngredientCategoriesCatalog(
    categories: List<IngredientCategory>,
    onCategoryClick: (IngredientCategory) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(categories) {
            SimpleCard(
                it.name,
                painterResource(id = it.imageID),
                modifier = Modifier
                    .height(150.dp)
                    .clickable(onClick = { onCategoryClick(it) })
            )
        }
    }
}

@Composable
fun InvisibleButton(
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {},
        enabled = false,
        modifier = modifier
            .alpha(0f)
    ){}
}