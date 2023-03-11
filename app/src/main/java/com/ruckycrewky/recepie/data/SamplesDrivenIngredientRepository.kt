package com.ruckycrewky.recepie.data

import com.ruckycrewky.recepie.R

class SamplesDrivenIngredientRepository: IngredientRepository {
    private val ingredients: List<Ingredient>

    init {
         ingredients = listOf(
            Ingredient(
                name = "Зелёное яблоко",
                category = "Фрукты и ягоды",
                imageID = R.drawable.yabloki,
            ),
            Ingredient(
                name = "Ананас",
                category = "Фрукты и ягоды",
                imageID = R.drawable.pineapple,
            ),
            Ingredient(
                name = "Вишня",
                category = "Фрукты и ягоды",
                imageID = R.drawable.cherry,
            ),
            Ingredient(
                name = "Апельсин",
                category = "Фрукты и ягоды",
                imageID = R.drawable.orange,
            ),
            Ingredient(
                name = "Мука",
                category = "Мука и мучная продукция",
                imageID = R.drawable.muka
            )
        )
    }

    override fun getByCategory(category: String): List<Ingredient> {
        return ingredients.filter {
            it.category == category
        }
    }
}