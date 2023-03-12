package com.ruckycrewky.recepie.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.ruckycrewky.recepie.data.Recipe
import com.ruckycrewky.recepie.ui.state.RecipeUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RecipeViewModel(
    private val recipe: Recipe,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecipeUIState(recipe = recipe))
    val uiState: StateFlow<RecipeUIState> = _uiState.asStateFlow()

    fun nextStep(){
        _uiState.update{currentState ->
            currentState.copy(
                stepNumber = currentState.stepNumber + 1,
                step = recipe.instruction[currentState.stepNumber],
                nextButtonIsEnabled = getNextButtonIsEnabled(currentState.stepNumber + 1),
                backButtonIsEnabled = getBackButtonIsEnabled(currentState.stepNumber + 1)
            )
        }
    }

    fun prevStep(){
        _uiState.update{currentState ->
            currentState.copy(
                stepNumber = currentState.stepNumber - 1,
                step = recipe.instruction[currentState.stepNumber - 1],
                nextButtonIsEnabled = getNextButtonIsEnabled(currentState.stepNumber - 1),
                backButtonIsEnabled = getBackButtonIsEnabled(currentState.stepNumber - 1)
            )
        }
    }

    private fun getNextButtonIsEnabled(currentStepNumber: Int): Boolean{
        return currentStepNumber != uiState.value.recipe.instruction.size
    }

    private fun getBackButtonIsEnabled(currentStepNumber: Int): Boolean{
        return currentStepNumber != 0
    }
}