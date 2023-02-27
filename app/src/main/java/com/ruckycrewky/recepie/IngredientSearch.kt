package com.ruckycrewky.recepie

import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ruckycrewky.recepie.ui.theme.FindReceiptButtonColor
import java.util.Collections

@Composable
fun IngredientSearch(
    onClickMenu: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var chosenCategory by remember { mutableStateOf("") }
    var showChosenIngredients by remember { mutableStateOf(false) }
    var chosenIngredients by remember { mutableStateOf(emptyList<Ingredient>()) }

    val defaultButtonElevation = ButtonDefaults.buttonElevation(
        defaultElevation = 10.dp,
        pressedElevation = 15.dp,
        disabledElevation = 0.dp
    )

    val modifierWithDefaultWidth = Modifier
        .padding(start = 10.dp, end = 10.dp)
        .fillMaxWidth()

    val onValueChangeDoNothing = { _: String -> {} } // TODO: временная заглушка

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SearchBar(
            placeHolder = stringResource(id = R.string.ingredient_search_placeholder),
            onValueChange = onValueChangeDoNothing,
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
                onClick = {
                    chosenCategory = ""
                },
                enabled = (chosenCategory != ""),
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
                    showChosenIngredients = !showChosenIngredients
                },
                shape = RoundedCornerShape(22.dp),
                elevation = defaultButtonElevation,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                ),
            ) {
                var message = stringResource(id = R.string.ingredient_search_show_chosen_ingredients)
                if (showChosenIngredients) {
                    message = stringResource(id = R.string.ingredient_search_hide_chosen_ingredients)
                }
                Text(
                    message,
                    fontSize = 10.sp
                )
            }
            InvisibleButton()
        }

        if (showChosenIngredients) {
            ChosenIngredients(
                ingredients = chosenIngredients,
                onDeleteIconClick = { ingredient: Ingredient ->
                    {
                        val mutableChosenIngredients = chosenIngredients.toMutableList()
                        mutableChosenIngredients.remove(ingredient)
                        chosenIngredients = Collections.unmodifiableList(mutableChosenIngredients)
                    }
                }
            )
        }

        if (chosenCategory != "") {
            // Show ingredients
            val chosenIngredientsName = chosenIngredients.map { it.name }
            val availableIngredientsOfChosenCategory = ingredientSamples.filter {
                it.category == chosenCategory && !chosenIngredientsName.contains(it.name)
            }
            IngredientsCatalog(
                availableIngredients = availableIngredientsOfChosenCategory,
                onIngredientClick = { ingredient: Ingredient ->
                    {
                        chosenIngredients = chosenIngredients + ingredient
                        Log.d(
                            "IngredientSearch",
                            "Ingredient ${ingredient.name} was chosen"
                        )
                    }
                }
            )
        }
        else {
            // Show categories of ingredients
            IngredientCategoriesCatalog(
                categories = ingredientCategoriesSamples,
                onCategoryClick = { category: IngredientCategory ->
                    {
                        chosenCategory = category.name
                        Log.d("IngredientSearch", "Category ${category.name} was chosen")
                    }
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            enabled = chosenIngredients.isNotEmpty(),
            elevation = defaultButtonElevation,
            modifier = modifierWithDefaultWidth,
            colors = ButtonDefaults.buttonColors(
                containerColor = FindReceiptButtonColor,
                contentColor = Color.White,
            ),
            onClick = { /* TODO */ }
        ) {
            var message = stringResource(id = R.string.ingredient_search_find_recipes)
            if (chosenIngredients.isEmpty())
                message = stringResource(id = R.string.ingredient_search_user_must_chose_ingredient)
            Text(message)
        }
    }
}

@Composable
fun ChosenIngredients(
    ingredients: List<Ingredient>,
    onDeleteIconClick: (Ingredient) -> () -> Unit,
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
                modifier = Modifier.clickable(onClick = onDeleteIconClick(ingredient))
            )
        }
    }
}

@Composable
fun IngredientsCatalog(
    availableIngredients: List<Ingredient>,
    onIngredientClick: (ingredient: Ingredient) -> () -> Unit,
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
                    .clickable(onClick = onIngredientClick(it))
            )
        }
    }
}

@Composable
fun IngredientCategoriesCatalog(
    categories: List<IngredientCategory>,
    onCategoryClick: (IngredientCategory) -> () -> Unit,
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
                    .clickable(onClick = onCategoryClick(it))
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

@Composable
@Preview
fun IngredientSearchPreview() {
    IngredientSearch({})
}