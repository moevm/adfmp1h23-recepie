package com.ruckycrewky.recepie.ui.state

import com.ruckycrewky.recepie.data.Recipe

data class RecipeUIState(
    val recipe: Recipe,
    val stepNumber: Int = 0,
    val step: String = "",
    val nextButtonIsEnabled: Boolean = true,
    val backButtonIsEnabled: Boolean = false,
)