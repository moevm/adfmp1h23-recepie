package com.ruckycrewky.recepie.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ruckycrewky.recepie.R
import com.ruckycrewky.recepie.ui.component.BackPageButton
import com.ruckycrewky.recepie.ui.component.StepByStepInstruction
import com.ruckycrewky.recepie.ui.theme.*
import com.ruckycrewky.recepie.ui.viewmodel.RecipeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RecipePage(
    viewModel: RecipeViewModel = viewModel(),
    navController: NavController
){
    val uiState by viewModel.uiState.collectAsState()
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        color = GrayBackground
    ) {
        Column() {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                Image(
                    painter = painterResource(uiState.recipe.imageID),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
                BackPageButton(
                    onClick = { navController.popBackStack() }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = uiState.recipe.name,
                style = Typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Spacer(modifier = Modifier.width(40.dp))
                Surface(
                    modifier = Modifier
                        .width(170.dp)
                        .height(55.dp)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = rememberRipple(
                                bounded = true,
                                color = ClickAnimationColor
                            )
                        ) {
                            navController.navigate("feedback/${uiState.recipe.name}")
                        },
                    shape = MaterialTheme.shapes.medium,
                    shadowElevation = 1.dp,
                ){
                    Column(
                        modifier = Modifier
                    ) {
                        Row() {
                            for (i in 1..5) {
                                var star = R.drawable.empty_star_rate_24
                                if (i <= uiState.recipe.rating) {
                                    star = R.drawable.full_star_rate_24
                                }
                                Image(
                                    painter = painterResource(id = star),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(start = 2.dp, top = 5.dp)
                                )
                            }
                            Text(
                                text = "${uiState.recipe.rating}",
                                color = HighRating,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Right,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 7.dp, start = 0.dp, end = 8.dp, bottom = 0.dp)
                            )
                        }
                        Text(
                            text = "${uiState.recipe.numberOfReviews} отзывов",
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
                        text = uiState.recipe.cookTime,
                        color = GrayCookTime
                    )
                    Image(
                         painterResource(id = R.drawable.baseline_access_time_24),
                        "time",

                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            StepByStepInstruction(
                ingredients = uiState.recipe.ingredients,
                stepNumber = uiState.stepNumber,
                step = uiState.step,
                onClickBackButton = { viewModel.prevStep() },
                onClickNextButton = { viewModel.nextStep() },
                backButtonIsEnabled = uiState.backButtonIsEnabled,
                nextButtonIsEnabled = uiState.nextButtonIsEnabled
            )
        }
    }
}