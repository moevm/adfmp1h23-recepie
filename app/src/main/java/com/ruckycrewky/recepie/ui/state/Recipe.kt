package com.ruckycrewky.recepie.ui.state

import com.ruckycrewky.recepie.data.Recipe
import com.ruckycrewky.recepie.data.defaultRecipe

data class RecipeUIState(
    val recipe: Recipe = defaultRecipe,
    val stepNumber: Int = 0,
    val step: String = "",
    val nextButtonIsEnabled: Boolean = true,
    val backButtonIsEnabled: Boolean = false,
)