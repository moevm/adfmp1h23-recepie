package com.ruckycrewky.recepie

import com.ruckycrewky.recepie.data.defaultRecipe
import com.ruckycrewky.recepie.ui.viewmodel.RecipeViewModel
import junit.framework.TestCase
import org.junit.Test

class RecipeUnitTest {
    private val recipe = defaultRecipe
    private val viewModel = RecipeViewModel(recipe = recipe)

    @Test
    fun recipeViewModel_ClickNextAndBackButton_UpdateStepByStepInstruction(){
        TestCase.assertEquals(0, viewModel.uiState.value.stepNumber)

        viewModel.nextStep()

        TestCase.assertEquals(1, viewModel.uiState.value.stepNumber)
        TestCase.assertEquals(recipe.instruction[0], viewModel.uiState.value.step)

        viewModel.nextStep()
        viewModel.nextStep()
        viewModel.prevStep()

        TestCase.assertEquals(recipe.instruction[1], viewModel.uiState.value.step)
    }

    @Test
    fun recipeViewModel_ClickNextAndBackButton_ChangesInButtonsAvailability(){
        var currentStep = 0
        TestCase.assertEquals(currentStep, viewModel.uiState.value.stepNumber)
        TestCase.assertFalse(viewModel.uiState.value.backButtonIsEnabled)
        TestCase.assertTrue(viewModel.uiState.value.nextButtonIsEnabled)

        while(currentStep < recipe.instruction.size){
            viewModel.nextStep()
            currentStep += 1
        }

        TestCase.assertFalse(viewModel.uiState.value.nextButtonIsEnabled)
        TestCase.assertTrue(viewModel.uiState.value.backButtonIsEnabled)

        viewModel.prevStep()

        TestCase.assertTrue(viewModel.uiState.value.nextButtonIsEnabled)
    }
}