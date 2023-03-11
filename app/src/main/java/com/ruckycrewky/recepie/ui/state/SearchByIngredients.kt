package com.ruckycrewky.recepie.ui.state

import com.ruckycrewky.recepie.data.Ingredient

data class SearchByIngredientsUIState(
    val chosenIngredientsCategory: String = "",
    val showChosenIngredients: Boolean = false,
    val chosenIngredients: List<Ingredient> = emptyList(),
)