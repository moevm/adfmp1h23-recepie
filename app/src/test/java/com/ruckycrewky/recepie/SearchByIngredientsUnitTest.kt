package com.ruckycrewky.recepie

import com.ruckycrewky.recepie.data.Ingredient
import com.ruckycrewky.recepie.data.SamplesDrivenIngredientRepository
import com.ruckycrewky.recepie.ui.viewmodel.SearchByIngredientsViewModel
import junit.framework.TestCase.*
import org.junit.Assert.assertArrayEquals
import org.junit.Test

class SearchByIngredientsUnitTest {
    private val viewModel = SearchByIngredientsViewModel(
        ingredientRepository = SamplesDrivenIngredientRepository()
    )

    @Test
    fun searchByIngredientsViewModel_SelectAndUnselectCategory_UpdateChosenCategory() {
        assertEquals("", viewModel.uiState.value.chosenIngredientsCategory)

        val selectedCategoryName = "Фрукты и ягоды"
        viewModel.selectCategory(selectedCategoryName)
        assertEquals(selectedCategoryName, viewModel.uiState.value.chosenIngredientsCategory)

        viewModel.unselectCategory()
        assertEquals("", viewModel.uiState.value.chosenIngredientsCategory)
    }

    @Test
    fun searchByIngredientsViewModel_ToggleShowIngredients_FlagInverted() {
        assertFalse(viewModel.uiState.value.showChosenIngredients)

        viewModel.toggleShowIngredients()
        assertTrue(viewModel.uiState.value.showChosenIngredients)

        viewModel.toggleShowIngredients()
        assertFalse(viewModel.uiState.value.showChosenIngredients)
    }

    @Test
    fun searchByIngredientsViewModel_ChooseCategory_FilteredIngredients() {
        viewModel.selectCategory("Фрукты и ягоды")
        val actualIngredientNames = viewModel.getIngredientsOfChosenCategory().map { it.name }
        val expectedIngredientNames = listOf("Зелёное яблоко", "Ананас", "Вишня", "Апельсин")
        assertArrayEquals(expectedIngredientNames.toTypedArray(), actualIngredientNames.toTypedArray())
    }

    @Test
    fun searchByIngredientsViewModel_AddAndRemoveIngredient_ChosenIngredientsUpdated() {
        assertEquals(0, viewModel.uiState.value.chosenIngredients.size)
        viewModel.addIngredient(
            Ingredient("Мука", "Мука и мучная продукция", 0)
        )
        assertEquals(1, viewModel.uiState.value.chosenIngredients.size)
        viewModel.addIngredient(
            Ingredient("Банан", "Фрукты и овощи", 0)
        )
        assertEquals(2, viewModel.uiState.value.chosenIngredients.size)

        viewModel.removeIngredient("Киви")
        assertEquals(2, viewModel.uiState.value.chosenIngredients.size)
        viewModel.removeIngredient("Банан")
        assertEquals(1, viewModel.uiState.value.chosenIngredients.size)
        viewModel.removeIngredient("Мука")
        assertEquals(0, viewModel.uiState.value.chosenIngredients.size)
    }

    @Test
    fun searchByIngredientsViewModel_RemoveIngredient_NotCrush() {
        assertEquals(0, viewModel.uiState.value.chosenIngredients.size)
        viewModel.removeIngredient("Банан")
        assertEquals(0, viewModel.uiState.value.chosenIngredients.size)
    }
}