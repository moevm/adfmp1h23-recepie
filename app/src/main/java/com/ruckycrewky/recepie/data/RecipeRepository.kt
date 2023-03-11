package com.ruckycrewky.recepie.data

interface RecipeRepository {

    fun searchByName(searchQuery: String): List<Recipe>
}