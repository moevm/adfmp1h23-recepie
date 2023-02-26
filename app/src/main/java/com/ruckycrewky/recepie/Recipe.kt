package com.ruckycrewky.recepie

import androidx.annotation.DrawableRes

data class Recipe(
    val name: String,
    val rating: Double,
    val cookTime: String,
    @DrawableRes val imageID: Int, // TODO: убрать
)