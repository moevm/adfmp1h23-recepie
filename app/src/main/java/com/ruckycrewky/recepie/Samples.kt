package com.ruckycrewky.recepie

val recipeSamples = listOf(
    Recipe(
        "Губадия с курагой и очень вкусной посыпкой",
        4.5,
        "1 ч 45 мин",
        R.drawable.gubadiya,
    ),
    Recipe(
        "Блины",
        4.5,
        "35 мин",
        R.drawable.blin,
    ),
    Recipe(
        "Блины с икрой",
        5.0,
        "35 мин",
        R.drawable.blin_s_ikroy,
    ),
    Recipe(
        "Драники",
        5.0,
        "35 мин",
        R.drawable.draniki,
    ),
    Recipe(
        "Шарлотка",
        4.5,
        "1.5 ч",
        R.drawable.sharlotka,
    )
)

val ingredientCategoriesSamples = listOf(
    IngredientCategory(
        name = "Фрукты и ягоды",
        imageID = R.drawable.frukrti_i_yagodi,
    ),
    IngredientCategory(
        name = "Молочная продукция",
        imageID = R.drawable.molochnaya_produkciya,
    ),
    IngredientCategory(
        name = "Мука и мучная продукция",
        imageID = R.drawable.myka_i_co,
    ),
    IngredientCategory(
        name = "Птица",
        imageID = R.drawable.ptica,
    ),
    IngredientCategory(
        name = "Рыба и морепродукты",
        imageID = R.drawable.riba,
    ),
    IngredientCategory(
        name = "Пищевые добавки",
        imageID = R.drawable.pishevie_dobavki,
    ),
)

val ingredientSamples = listOf(
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