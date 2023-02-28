package com.ruckycrewky.recepie

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ruckycrewky.recepie.ui.theme.Typography


@Composable
fun MealFeedbackPage(
    modifier: Modifier = Modifier,
    navController: NavController
){
    Column {
        RecipeTitle(recipe = recipeSamples[0], stringResource(R.string.feedbackLabel))

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 15.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .weight(1f)
        ){
            items(feedbackSamples){
                FeedbackCard(feedback = it)
            }
        }

        AddFeedbackButton( onClick = { navController.navigate("add-feedback/${recipeSamples[1].name}") })
    }
}

//@Preview
//@Composable
//fun MealFeedbackPagePreview(){
//    RecepieTheme {
//        // A surface container using the 'background' color from the theme
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background
//        ) {
//            MealFeedbackPage()
//        }
//    }
//}

@Composable
fun RecipeTitle(
    recipe: Recipe,
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth()
    ){
        Box(
            contentAlignment = Center,
            modifier = Modifier
                .wrapContentSize()
                .fillMaxSize()
                .padding(start = 40.dp)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = recipe.name,
                    style = Typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .width(200.dp)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = text,
                    style = Typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
        Image(
            painter = painterResource(recipe.imageID),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(90.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeTitlePreview() {
    RecipeTitle(recipe = recipeSamples[0], stringResource(R.string.feedbackLabel))
}

@Composable
fun FeedbackCard(
    feedback: Feedback,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 1.dp,
        modifier = Modifier
            .wrapContentSize()
    ) {
        Column {
            Text(
                text = feedback.name,
                style = Typography.titleSmall,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .padding(start = 10.dp, top = 5.dp)
            )
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 5.dp)
            ){
                Row(){
                    for (i in 1..5) {
                        var star = R.drawable.empty_star_rate_24
                        if (i <= feedback.rating) {
                            star = R.drawable.full_star_rate_24
                        }
                        Image(
                            painter = painterResource(id = star),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 2.dp, top = 5.dp)
                        )
                    }
                }
            }
            Text(
                text = feedback.feedback,
                style = Typography.bodySmall,
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp)
            )

            val painter = painterResource(id = feedback.imageID)
            val imageRatio = painter.intrinsicSize.width / painter.intrinsicSize.height
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 10.dp, top = 20.dp, end = 10.dp, bottom = 10.dp)
                    .aspectRatio(imageRatio)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeedbackCardPreview() {
    FeedbackCard(feedback = feedbackSamples[0])
}

@Composable
fun AddFeedbackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(Color.Blue),
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .height(50.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.addFeedbackButtonLabel),
            style = Typography.titleLarge,
            textAlign = TextAlign.Center,
        )
    }
}

//@Preview
//@Composable
//fun AddFeedbackButtonPreview() {
//    AddFeedbackButton()
//}
