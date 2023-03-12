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
                nextButtonIsEnabled = isNextButtonEnabled(currentState.stepNumber + 1),
                backButtonIsEnabled = isBackButtonEnabled(currentState.stepNumber + 1)
            )
        }
    }

    fun prevStep(){
        _uiState.update{currentState ->
            currentState.copy(
                stepNumber = currentState.stepNumber - 1,
                step = recipe.instruction[currentState.stepNumber - 2],
                nextButtonIsEnabled = isNextButtonEnabled(currentState.stepNumber - 1),
                backButtonIsEnabled = isBackButtonEnabled(currentState.stepNumber - 1)
            )
        }
    }

    private fun isNextButtonEnabled(currentStepNumber: Int): Boolean{
        return currentStepNumber != uiState.value.recipe.instruction.size
    }

    private fun isBackButtonEnabled(currentStepNumber: Int): Boolean{
        return currentStepNumber != 0
    }
}