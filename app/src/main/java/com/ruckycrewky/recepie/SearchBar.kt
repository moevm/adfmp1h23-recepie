package com.ruckycrewky.recepie

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    placeHolder: String,
    leadingIcon: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    TextField(
        value = searchQuery,
        onValueChange = {
            searchQuery = it
        },
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        leadingIcon = leadingIcon,
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        },
        placeholder = {
            Text(placeHolder)
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    val leadingIcon = @Composable {
        Image(
            painter = painterResource(R.drawable.baseline_list_alt_24),
            contentDescription = "Search",
        )
    }
    SearchBar("Искать", leadingIcon)
}