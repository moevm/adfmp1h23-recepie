package com.ruckycrewky.recepie

import androidx.annotation.DrawableRes
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ruckycrewky.recepie.ui.theme.GrayCookTime
import com.ruckycrewky.recepie.ui.theme.HighRating
import com.ruckycrewky.recepie.ui.theme.Typography

data class RecipeInfo(
    val name: String,
    val rating: Double,
    val numberOfReviews: Int,
    val cookTime: String,
    @DrawableRes val imageID: Int,
    val listOfIngredients: Map<String, String>,
    val instruction: Map<Int, String>
)

val step_1 = "Готовим дрожжевое тесто. Взбиваем 1,5 ст. кефира, 2 яйца,100 гр. растопленного сливочного масла, 2 ст. л. сахарного песка, соль, 1 ст.л. подсолнечного масла и 50 гр. дрожжей. Добавляем муку и замешиваем тесто. Накрываем пленкой и ставим в теплое место где-то на 40 мин."
val step_2 = "Готовим кырт. В чугунной кастрюле или кастрюле с толстым дном кипятим 1 литр молока и 1 кг. творога до выпаривания молока. Я кипятила минут 20, потом откинула все это на сито, слила лишнюю жидкость, добавила 100 гр. сахарного песка и продолжила выпаривать, периодически помешивая. Должна получиться очень густая масса коричневого цвета. Все это действие заняло у меня 2 часа. Потом я выложила массу на пергамент и за ночь она окончательно подсохла и стала рассыпчатой. Делать кырт лучше накануне."
val step_3 = "Готовим сухофрукты. Курагу и изюм (отдельно) замачиваем, моем, сушим. Курагу режем кубиками 0,5*0,5 см."

@Preview
@Composable
fun RecipePage(recipeID: Int = 1){
    // TODO: get data from backend
    val listOfIngredients = mapOf<String, String>(
        "Кефир" to "1 1/2 стакана",
        "Дрожжи свежие" to "50 г.",
        "Яйца куриные" to "6 шт.",
        "Масло сливочное" to "700 г.",
        "Соль" to "1 ч.л.",
        "Рис" to "1/2 стакана",
        "Курага" to "1/2 кг",
        "Сахарный песок" to "100 г",
        "Мука" to "1 кг"
    )
    val instruction = mapOf<Int, String>(1 to step_1, 2 to step_2, 3 to step_3)
    val defaultRecipeData = RecipeInfo(
        name = "Губадия с курагой",
        rating = 1.2,
        numberOfReviews = 200,
        cookTime = "1ч 45м",
        imageID = R.drawable.gubadiya,
        listOfIngredients = listOfIngredients,
        instruction = instruction
    )
    val recipeData = defaultRecipeData
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        color = Color(0xFFF1F1F1)
    ) {
        Column() {
            Image(
                painter = painterResource(recipeData.imageID),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = recipeData.name,
                style = Typography.labelSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Spacer(modifier = Modifier.width(70.dp))
                Surface(
                    //onClick = {},
                    //colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White),
                    modifier = Modifier
                        .width(140.dp)
                        .height(55.dp)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = rememberRipple(
                                bounded = true,
                                color = Color(0xFF9B9B9B)
                            )
                        ) { },
                    shape = MaterialTheme.shapes.medium,
                    shadowElevation = 1.dp,
                ){
                    Column(
                        modifier = Modifier
                    ) {
                        for(i in 0..4){

                        }
                        // TODO: add rating stars
                        Text(
                            text = "${recipeData.rating}",
                            color = HighRating,
                            textAlign = TextAlign.Right,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 14.dp, start = 0.dp, end = 8.dp, bottom = 0.dp)
                        )
                        Text(
                            text = "${recipeData.numberOfReviews} отзывов",
                            textAlign = TextAlign.Center,
                            textDecoration = TextDecoration.Underline,
                            color = GrayCookTime,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 2.dp, start = 0.dp, end = 0.dp, bottom = 0.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(20.dp))
                Row(
                    modifier = Modifier.padding(vertical = 15.dp)
                ) {
                    Text(
                        text = recipeData.cookTime,
                        color = GrayCookTime
                    )
                    Icon(
                        Icons.Outlined.AccessTime,
                        "time",

                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            StepByStepInstruction(
                ingredients = recipeData.listOfIngredients,
                instructions = recipeData.instruction
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun StepByStepInstruction(
    ingredients: Map<String, String>,
    instructions: Map<Int, String>
) {
    var currentPage by remember { mutableStateOf(0) }
    var currentText by remember { mutableStateOf("") }
    if(currentPage !== 0)
        currentText = instructions.getValue(currentPage)
    Box(
        modifier = Modifier
            .height(200.dp)
    ) {
        Surface(
            color = Color.White,
            modifier = Modifier
                .animateContentSize(
                    animationSpec = tween(durationMillis = 200)
                )
                .width(400.dp)
                .padding(top = 0.dp, start = 40.dp, end = 40.dp, bottom = 0.dp)
                .verticalScroll(rememberScrollState()),

            shape = MaterialTheme.shapes.medium,
            shadowElevation = 1.dp
        )
        {
            AnimatedContent(
                targetState = currentPage,
                transitionSpec = {
                    fadeIn(
                        animationSpec = tween(
                            delayMillis = 500
                        )
                    ) +
                            slideInHorizontally(
                                animationSpec = tween(
                                    delayMillis = 500
                                )
                                { fullWidth ->
                                    fullWidth
                                }
                            ) with slideOutHorizontally() +
                            fadeOut()
                }
            )
            { targetPage ->
                Column() {
                    if (targetPage == 0) {
                        Text(
                            text = "Ингредиенты",
                            modifier = Modifier
                                .padding(top = 3.dp, start = 12.dp, end = 0.dp, bottom = 0.dp)
                        )
                        val bullet = "\u2022"
                        val paragraphStyle =
                            ParagraphStyle(textIndent = TextIndent(restLine = 12.sp))
                        Text(
                            buildAnnotatedString {
                                ingredients.forEach { ingredient ->
                                    withStyle(style = paragraphStyle) {
                                        withStyle(
                                            style = SpanStyle(
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                        {
                                            append("\t")
                                            append(bullet)
                                            append("\t")
                                            append(ingredient.key)
                                        }
                                        append(" - ")
                                        append(ingredient.value)
                                    }
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                    } else {
                        Text(
                            text = "Шаг $targetPage",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(top = 3.dp, start = 12.dp, end = 0.dp, bottom = 0.dp)
                        )
                        currentText = instructions.getValue(targetPage)
                        Text(
                            text = currentText,
                            modifier = Modifier
                                .padding(top = 0.dp, start = 3.dp, end = 0.dp, bottom = 20.dp)
                        )
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    var backDisabled = true
    if(currentPage > 0){
        backDisabled = false
    }
    var nextDisabled = true
    if(currentPage < instructions.keys.size){
        nextDisabled = false
    }
    Row(){
        Spacer(modifier = Modifier.width(40.dp))
        BackButton(onClick = { currentPage -= 1 }, isDisable = backDisabled)
        Spacer(modifier = Modifier.width(40.dp))
        NextButton(onClick = { currentPage += 1 }, isDisable = nextDisabled)
    }
}

@Composable
fun NextButton(
    onClick: () -> Unit,
    isDisable: Boolean,
)
{
    var color = Color.White
    if(isDisable){
        color = Color(0xFFF1F1F1)
    }
    Surface(
        color = color,
        modifier = Modifier
            .width(120.dp)
            .height(50.dp)
            .padding(top = 0.dp, start = 0.dp, end = 0.dp, bottom = 0.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(
                    bounded = true,
                    color = Color(0xFF9B9B9B)
                )
            )
            {
                if (!isDisable)
                    onClick()
            },
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 1.dp)
    {
        Icon(Icons.Filled.NavigateNext, "next")
    }
}

@Composable
fun BackButton(
    onClick: () -> Unit,
    isDisable: Boolean,
)
{
    var color = Color.White
    if(isDisable){
        color = Color(0xFFF1F1F1)
    }
    Surface(
        color = color,
        modifier = Modifier
            .width(120.dp)
            .height(50.dp)
            .padding(top = 0.dp, start = 0.dp, end = 0.dp, bottom = 0.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(
                    bounded = true,
                    color = Color(0xFF9B9B9B)
                )
            )
            {
                if (!isDisable)
                    onClick()
            },
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 1.dp)
    {
        Icon(Icons.Filled.NavigateBefore, "back")
    }
}