package com.ruckycrewky.recepie

val feedbackSamples = listOf(
    Feedback(
        "Жопа коня",
        1,
        "Мне не понравилось",
        R.drawable.gubadiya,
    ),
    Feedback(
        "Татар",
        5,
        "ДАААААА",
        R.drawable.gubadiya,
    ),
    Feedback(
        "Жопа коня",
        1,
        "Мне не понравилось",
        R.drawable.gubadiya,
    ),
    Feedback(
        "Жопа коня",
        1,
        "Мне не понравилось",
        R.drawable.gubadiya,
    ),
    Feedback(
        "Жопа коня",
        1,
        "Мне не понравилось",
        R.drawable.gubadiya,
    )
)

val recipeSamples = listOf(
    Recipe(
        "Губадия с курагой и очень вкусной посыпкой",
        4.5,
        "1 ч 45 мин",
        R.drawable.gubadiya,
        200,
        ingredients = mapOf<String, String>(
            "Кефир" to "1 1/2 стакана",
            "Дрожжи свежие" to "50 г.",
            "Яйца куриные" to "6 шт.",
            "Масло сливочное" to "700 г.",
            "Соль" to "1 ч.л.",
            "Рис" to "1/2 стакана",
            "Курага" to "1/2 кг",
            "Сахарный песок" to "100 г",
            "Мука" to "1 кг"
        ),
        instruction = listOf<String>(
            "Готовим дрожжевое тесто. Взбиваем 1,5 ст. кефира, 2 яйца,100 гр. растопленного сливочного масла, 2 ст. л. сахарного песка, соль, 1 ст.л. подсолнечного масла и 50 гр. дрожжей. Добавляем муку и замешиваем тесто. Накрываем пленкой и ставим в теплое место где-то на 40 мин.",
            "Готовим кырт. В чугунной кастрюле или кастрюле с толстым дном кипятим 1 литр молока и 1 кг. творога до выпаривания молока. Я кипятила минут 20, потом откинула все это на сито, слила лишнюю жидкость, добавила 100 гр. сахарного песка и продолжила выпаривать, периодически помешивая. Должна получиться очень густая масса коричневого цвета. Все это действие заняло у меня 2 часа. Потом я выложила массу на пергамент и за ночь она окончательно подсохла и стала рассыпчатой. Делать кырт лучше накануне.",
            "Готовим сухофрукты. Курагу и изюм (отдельно) замачиваем, моем, сушим. Курагу режем кубиками 0,5*0,5 см."
        )
    ),
    Recipe(
        "Блины",
        4.5,
        "35 мин",
        R.drawable.blin,
        5,
        ingredients = mapOf(
            "Молоко" to "1л",
            "Яйца" to "4 шт",
            "Мука" to "500 г",
            "Сахар" to "1.5 ст. л.",
        ),
        instruction = listOf(
            "В большой миске взбить яйца и сахар.",
            "Часть молока подогреть, посолить и тщательно перемешать со взбитыми яйцами и сахаром.",
            "В полученную смесь постепенно добавлять муку, постоянно перемешивая, чтобы не было комочков.",
            "Добавить остальное молоко и взбить. Тесто должно быть в меру жидким.",
            "Обжарить блин с обеих сторон до золотистого цвета."
        )
    ),
    Recipe(
        "Блины с икрой",
        5.0,
        "35 мин",
        R.drawable.blin_s_ikroy,
        532,
        ingredients = mapOf(
            "Блины" to "1 шт.",
            "Икра" to "10 кг",
        ),
        instruction = listOf(
            "Намазать икру на блин"
        )
    ),
    Recipe(
        "Драники",
        5.0,
        "35 мин",
        R.drawable.draniki,
        1,
        ingredients = mapOf(
            "Картофель" to "1л",
            "Лук" to "1 шт",
            "Мука" to "1 ст.л.",
            "Соль" to "1 щепотка",
            "Масло" to "30 мл",
        ),
        instruction = listOf(
            "Очищаем картофель и лук.",
            "Трем картофель на крупной терке. Я это делаю не в миске, а в пластиковом друшлаге, т.к потом все равно ее нужно будет сцеживать.",
            "Туда же трем лук.",
            "Обязательно перекидываем на сито и выдерживаем минимум 5 мин., дабы стекла лишняя жидкость, так калорий меньше будет, и муки не так много понадобится, потому что масса станет гуще. Я ее выдерживаю 10 мин., при этом постоянно выжимая сок.",
            "Добавляем муку, соль и перец.",
            "Разогрейте растительное масло на средне-сильном огне. Выкладывайте драники большой ложкой на сковороду и слегка прижимайте их лопаткой.",
            "Жарим примерно 4 минуты с каждой стороны, пока не подрумянятся."
        )
    ),
    Recipe(
        "Шарлотка",
        4.5,
        "1.5 ч",
        R.drawable.sharlotka,
        1243,
        ingredients = mapOf(
            "Мука" to "200 г",
            "Сахар" to "200 г",
            "Яйца" to "4 шт.",
            "Яблоки" to "3 шт.",
        ),
        instruction = listOf(
            "Яйца взбейте миксером до появления легкой пены. Затем постепенно небольшими порциями всыпьте сахар и продолжайте взбивать до получения пышной массы в течение 3-5 минут. Чтобы проверить, как взбились яйца, проведите лопаткой линию по массе. Если след держится 1-2 секунды, значит яйца взбиты до нужной консистенции. Если след мгновенно исчезает, нужно продолжить взбивание. При этом важно не перевзбить яйца - на поверхности должны быть мелкие пузырьки.",
            "Муку просейте с разрыхлителем в отдельную миску. Муку нужно просеивать обязательно - так она насыщается кислородом, а тесто приобретает воздушность. Если есть время, то лучше даже просеять муку два раза.",
            "Продолжая взбивать яйца теперь уже на низких оборотах, частями добавьте в тесто просеянную муку. Если взбивать на высоких оборотах, то тесто может сильно осесть и потерять воздушность, ведь чем дольше взбиваются яйца, тем плотнее они становятся, а из массы уходит кислород. Можно воспользоваться и лопаткой, но миксер все же смешает тесто качественнее и равномернее.",
            "Яблоки вымойте, очистите от кожуры, удалите сердцевину и нарежьте средними кусочками.",
            "Высыпьте в тесто подготовленные яблоки и аккуратно перемешайте все лопаткой.",
            "Форму (20-22 см) застелите пергаментом и смажьте дно (но не стенки!) сливочным маслом. Выложите в форму тесто и разровняйте. При желании сверху равномерно присыпьте тесто тонким слоем сахара. Я этого не делала, тесто и так было сладким. Выпекайте шарлотку в предварительно разогретой до 180°С духовке около 40-50 минут. Первые 25 минут дверцу духовки не открывайте, иначе пирог осядет. Готовность проверяйте деревянной шпажкой – она должна выходить сухой."
        )
    )
)

val defaultRecipe = recipeSamples[0]