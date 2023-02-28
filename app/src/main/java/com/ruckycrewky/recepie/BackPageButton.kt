package com.ruckycrewky.recepie

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ruckycrewky.recepie.ui.theme.BlueButton

@Composable
fun BackPageButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val defaultButtonElevation = ButtonDefaults.buttonElevation(
        defaultElevation = 10.dp,
        pressedElevation = 15.dp,
        disabledElevation = 0.dp
    )
    Button(
        onClick = { onClick() },
        elevation = defaultButtonElevation,
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp),  // avoid the little icon
        colors = ButtonDefaults.buttonColors(
            containerColor = BlueButton,
        ),
        modifier = Modifier
            .padding(start = 20.dp, top = 10.dp, end = 10.dp),
    ) {
        Icon(
            Icons.Default.ArrowBack,
            tint = Color.White,
            contentDescription = "back"
        )
    }
}

//@Preview
//@Composable
//fun BackPageButtonPreview(){
//    BackPageButton()
//}