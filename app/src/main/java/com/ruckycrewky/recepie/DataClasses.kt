package com.ruckycrewky.recepie

import androidx.annotation.DrawableRes

data class Recipe(
    val name: String,
    val rating: Double,
    val cookTime: String,
    @DrawableRes val imageID: Int, // TODO: убрать
)

data class Feedback(
    val name: String,
    val rating: Int,
    val feedback: String,
    @DrawableRes val imageID: Int, // TODO: убрать
)