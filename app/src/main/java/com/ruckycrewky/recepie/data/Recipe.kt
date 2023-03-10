package com.ruckycrewky.recepie.data

import androidx.annotation.DrawableRes

data class Recipe(
    val name: String,
    val rating: Double,
    val cookTime: String,
    @DrawableRes val imageID: Int, // TODO: убрать
    val numberOfReviews: Int,
    val ingredients: Map<String, String>,
    val instruction: List<String>
)