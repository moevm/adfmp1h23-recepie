package com.ruckycrewky.recepie.ui.component

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun StepByStepInstruction(
    onClickNextButton: () -> Unit,
    onClickBackButton: () -> Unit,
    nextButtonIsEnabled: Boolean,
    backButtonIsEnabled: Boolean,
    step: String,
    stepNumber: Int,
    ingredients: Map<String, String>
) {
    Box(
        modifier = Modifier
            .height(400.dp)
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
        ) {
            AnimatedContent(
                targetState = stepNumber,
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
                            ) with slideOutHorizontally() + fadeOut()
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
                                            append("\u2022")
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
                        Text(
                            text = step,
                            modifier = Modifier
                                .padding(top = 0.dp, start = 3.dp, end = 0.dp, bottom = 20.dp)
                        )
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    Row(){
        Spacer(modifier = Modifier.width(65.dp))
        BackButton(onClick = { onClickBackButton() }, isEnabled = backButtonIsEnabled)
        Spacer(modifier = Modifier.width(40.dp))
        NextButton(onClick = { onClickNextButton() }, isEnabled = nextButtonIsEnabled)
    }
}