package com.ruckycrewky.recepie.data

import androidx.annotation.DrawableRes

data class Feedback(
    val name: String,
    val rating: Int,
    val feedback: String,
    @DrawableRes val imageID: Int, // TODO: убрать
)