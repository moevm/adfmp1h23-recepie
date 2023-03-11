package com.ruckycrewky.recepie.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.ruckycrewky.recepie.data.Ingredient
import com.ruckycrewky.recepie.data.IngredientCategory
import com.ruckycrewky.recepie.data.ingredientCategoriesSamples
import com.ruckycrewky.recepie.data.ingredientSamples
import com.ruckycrewky.recepie.ui.state.SearchByIngredientsUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*

class SearchByIngredientsViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(SearchByIngredientsUIState())
    val uiState: StateFlow<SearchByIngredientsUIState> = _uiState.asStateFlow()

    fun unselectCategory() {
        _uiState.update { currentState ->
            currentState.copy(
                chosenIngredientsCategory = ""
            )
        }
    }

    fun selectCategory(categoryName: String) {
        _uiState.update { currentState ->
            currentState.copy(
                chosenIngredientsCategory = categoryName
            )
        }
    }

    fun toggleShowIngredients() {
        _uiState.update { currentState ->
            currentState.copy(
                showChosenIngredients = !currentState.showChosenIngredients
            )
        }
    }

    fun removeIngredient(ingredientName: String) {
        val currentIngredients = _uiState.value.chosenIngredients.toMutableList()
        currentIngredients.removeIf { ingredient -> ingredient.name == ingredientName }
        _uiState.update { currentState ->
            currentState.copy(
                chosenIngredients = Collections.unmodifiableList(currentIngredients)
            )
        }
    }

    fun addIngredient(ingredient: Ingredient) {
        _uiState.update { currentState ->
            currentState.copy(
                chosenIngredients = currentState.chosenIngredients + ingredient
            )
        }
    }

    fun getIngredientsOfChosenCategory(): List<Ingredient> {
        // TODO: fetch data form some data source
        val allIngredients = ingredientSamples
        val chosenIngredientsName = _uiState.value.chosenIngredients.map { it.name }
        return allIngredients.filter {
            it.category == _uiState.value.chosenIngredientsCategory && !chosenIngredientsName.contains(it.name)
        }
    }

    fun getIngredientCategories(): List<IngredientCategory> {
        return ingredientCategoriesSamples
    }
}