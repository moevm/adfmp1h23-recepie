package com.ruckycrewky.recepie

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ruckycrewky.recepie.ui.theme.ClickAnimationColor
import com.ruckycrewky.recepie.ui.theme.DisabledButton

@Composable
fun BackButton(
    onClick: () -> Unit,
    isEnabled: Boolean,
)
{
    var color = Color.White
    if(!isEnabled){
        color = DisabledButton
    }
    Surface(
        color = color,
        modifier = Modifier
            .width(120.dp)
            .height(50.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(
                    bounded = true,
                    color = ClickAnimationColor
                )
            )
            {
                if (isEnabled)
                    onClick()
            },
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 1.dp)
    {
        Icon(Icons.Filled.ArrowBack, "back")
    }
}