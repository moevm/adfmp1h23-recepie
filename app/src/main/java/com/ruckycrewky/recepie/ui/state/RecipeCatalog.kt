package com.ruckycrewky.recepie.ui.state

import com.ruckycrewky.recepie.data.Recipe

data class RecipeCatalogUIState(
    val displayedRecipes: List<Recipe> = emptyList()
)