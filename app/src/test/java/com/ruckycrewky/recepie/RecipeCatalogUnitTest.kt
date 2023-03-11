package com.ruckycrewky.recepie

import com.ruckycrewky.recepie.data.SamplesDrivenRecipeRepository
import com.ruckycrewky.recepie.ui.viewmodel.RecipeCatalogViewModel
import junit.framework.TestCase
import org.junit.Test

class RecipeCatalogUnitTest {
    private val viewModel = RecipeCatalogViewModel(
        recipeRepository = SamplesDrivenRecipeRepository()
    )

    @Test
    fun recipeCatalogViewModel_SearchRecipeByName_UpdateDisplayedRecipes() {
        var currentRecipeCatalogUiState = viewModel.uiState.value
        val searchQuery = "Губадия"

        // Assert that not all recipe names contains searchQuery
        TestCase.assertFalse(currentRecipeCatalogUiState.displayedRecipes.all {
            it.name.contains(searchQuery, ignoreCase = true)
        })

        viewModel.searchRecipes(searchQuery)

        currentRecipeCatalogUiState = viewModel.uiState.value

        // Assert that all recipe names contains searchQuery after applying the search
        TestCase.assertTrue(currentRecipeCatalogUiState.displayedRecipes.all {
            it.name.contains(searchQuery, ignoreCase = true)
        })
    }
}