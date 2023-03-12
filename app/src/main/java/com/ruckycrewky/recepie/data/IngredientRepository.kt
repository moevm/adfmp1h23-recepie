package com.ruckycrewky.recepie.data

interface IngredientRepository {
    fun getByCategory(category: String): List<Ingredient>
}