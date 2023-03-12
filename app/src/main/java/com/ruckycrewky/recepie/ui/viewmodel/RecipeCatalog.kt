package com.ruckycrewky.recepie.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ruckycrewky.recepie.data.*
import com.ruckycrewky.recepie.ui.state.RecipeCatalogUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RecipeCatalogViewModel(
    // TODO: remove SamplesDrivenIngredientRepository as default value
    private val recipeRepository: RecipeRepository = SamplesDrivenRecipeRepository(),
) : ViewModel() {

    private val _uiState = MutableStateFlow(RecipeCatalogUIState())
    val uiState: StateFlow<RecipeCatalogUIState> = _uiState.asStateFlow()

    var recipesQuery by mutableStateOf("")
        private set

    init {
        resetPage()
    }

    fun searchRecipes(query: String) {
        // TODO: implement
        recipesQuery = query
        _uiState.update { currentState ->
            currentState.copy(
                displayedRecipes = recipeRepository.searchByName(recipesQuery)
            )
        }
    }

    private fun resetPage() {
        _uiState.value = RecipeCatalogUIState(displayedRecipes = recipeRepository.searchByName(recipesQuery))
    }
}