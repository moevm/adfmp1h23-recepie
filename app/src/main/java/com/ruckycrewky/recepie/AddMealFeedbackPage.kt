package com.ruckycrewky.recepie

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ruckycrewky.recepie.ui.theme.Typography


@Composable
fun AddMealFeedbackPage(
    modifier: Modifier = Modifier,
    navController: NavController
){
    Column {
        RecipeTitle(recipe = recipeSamples[1], stringResource(R.string.addFeedbackLabel))

        NameInputField()

        SetRatingField()

        FeedbackInputField()

        AddImageField()

        Spacer(modifier = Modifier.weight(1f))

        SendFeedbackButton(
            Modifier
                .align(Alignment.End)
        )
    }
}

//@Preview
//@Composable
//fun AddMealFeedbackPagePreview(){
//    RecepieTheme {
//        // A surface container using the 'background' color from the theme
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background
//        ) {
//            AddMealFeedbackPage()
//        }
//    }
//}


@Preview(showBackground = true)
@Composable
fun AddFeedbackTitlePreview() {
    RecipeTitle(recipe = recipeSamples[1], stringResource(R.string.addFeedbackLabel))
}


@Composable
fun NameInputField(
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    val nameMaxLength = 20

    Column(){
        TextField(
            value = name,
            textStyle = TextStyle.Default.copy(fontSize = 28.sp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = {
                if (it.text.length <= nameMaxLength) {
                    name = it
                }
            },
            placeholder = { Text(text = stringResource(R.string.inputYourNameLabel)) },
            maxLines = 1,
            singleLine = true,
            shape = RoundedCornerShape(50),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(top = 10.dp, bottom = 5.dp)
                .border(1.dp, Color.Black, shape = RoundedCornerShape(50))
        )
        Text(
            text = "${name.text.length} / $nameMaxLength",
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp, bottom = 10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NameInputFieldPreview() {
    NameInputField()
}

@Composable
fun SetRatingField(
    modifier: Modifier = Modifier
){
    var rating by remember { mutableStateOf(1) }
    Box(
        contentAlignment = Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp)

    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 1..5) {
                var star = R.drawable.empty_star_rate_24
                if (i <= rating) {
                    star = R.drawable.full_star_rate_24
                }
                Image(
                    painter = painterResource(id = star),
                    contentDescription = null,
                    modifier = Modifier
                        .size(70.dp)
                        .clickable(
                            enabled = true,
                            onClick = {
                                rating = i
                            }
                        )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SetRatingFieldPreview() {
    SetRatingField()
}

@Composable
fun FeedbackInputField(
    modifier: Modifier = Modifier
) {
    var feedback by remember { mutableStateOf(TextFieldValue("")) }
    val feedbackMaxLength = 200

    Column(){
        TextField(
            value = feedback,
            textStyle = TextStyle.Default.copy(fontSize = 28.sp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = {
                if (it.text.length <= feedbackMaxLength) {
                    feedback = it
                }
            },
            placeholder = { Text(text = stringResource(R.string.callOfFeedbackLabel)) },
            shape = RoundedCornerShape(20),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(top = 10.dp, bottom = 5.dp)
                .height(200.dp)
                .border(1.dp, Color.Black, shape = RoundedCornerShape(20))
        )
        Text(
            text = "${feedback.text.length} / $feedbackMaxLength",
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp, bottom = 10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FeedbackInputFieldPreview() {
    FeedbackInputField()
}

@Composable
fun AddImageField(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Center,
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth()
            .padding(horizontal = 90.dp, vertical = 10.dp)
            .clip(RoundedCornerShape(20))
            .border(1.dp, Color.Black, shape = RoundedCornerShape(20))
            .clickable(
                enabled = true,
                onClick = { /*TODO*/ }
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(id = R.drawable.baseline_photo_camera_24),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
            )
            Text(
                text = stringResource(R.string.callOfCamShotLabel),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge,
                maxLines = 2,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .width(170.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddImageFieldPreview(){
    AddImageField()
}

@Composable
fun SendFeedbackButton(
    modifier: Modifier = Modifier
){
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(Color.Blue),
        modifier = modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .height(50.dp)
    ) {
        Text(
            text = stringResource(R.string.sendFeedbackLabel),
            style = Typography.titleLarge,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SendFeedbackButtonPreview(){
    SendFeedbackButton()
}
