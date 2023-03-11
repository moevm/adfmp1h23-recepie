package com.ruckycrewky.recepie.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ruckycrewky.recepie.R

@Composable
fun SearchBar(
    placeHolder: String,
    onSearchRequest: (String) -> Unit,
    leadingIcon: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    Surface(
        shadowElevation = 5.dp,
        shape = RoundedCornerShape(22.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(56.dp),
    ) {
        TextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
            },
            modifier = modifier,
            leadingIcon = leadingIcon,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier
                        .clickable(onClick = {
                            onSearchRequest(searchQuery)
                        })
                )
            },
            placeholder = {
                Text(placeHolder)
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    val leadingIcon = @Composable {
        Image(
            painter = painterResource(R.drawable.recipe),
            contentDescription = "Search",
        )
    }
    val onSearchRequestDoNothing = { _: String -> }
    SearchBar(
        "Искать",
        onSearchRequestDoNothing,
        leadingIcon)
}