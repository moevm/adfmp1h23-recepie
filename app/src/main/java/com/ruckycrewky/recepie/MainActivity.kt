package com.ruckycrewky.recepie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ruckycrewky.recepie.ui.theme.RecepieTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecepieTheme {
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Column {
//                        RecipeCard(defaultRecipe)
//                    }
//                }
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val app_name = stringResource(id = R.string.app_name_ru)
// icons to mimic drawer destinations
                ModalNavigationDrawer(
                    drawerShape = customShape(),
                    drawerState = drawerState,
                    drawerContent = {
                        Surface(
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(250.dp)
                        ) {
                            Column(
                            ) {
                                Spacer(Modifier.height(12.dp))
                                Text(
                                    text=app_name,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Spacer(Modifier.height(12.dp))
                                NavigationDrawerItem(
                                    label = { Text("Поиск по рецепту") },
                                    selected = false,
                                    onClick = {
                                        scope.launch {
                                            drawerState.close()
                                            navController.navigate("recipe-catalog")
                                        }
                                    },
                                    colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.White),
                                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                                )
                                Divider(
                                    color = Color.Black,
                                    thickness = 1.dp,
                                    modifier = Modifier
                                        .width(220.dp)
                                        .padding(horizontal = 15.dp)
                                )
                                NavigationDrawerItem(
                                    label = { Text("Поиск по ингредиентам") },
                                    selected = false,
                                    onClick = {
                                        scope.launch {
                                            drawerState.close()
                                            navController.navigate("ingredient-search")
                                        }
                                    },
                                    colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.White),
                                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                                )
                                Divider(
                                    color = Color.Black,
                                    thickness = 1.dp,
                                    modifier = Modifier
                                        .width(220.dp)
                                        .padding(horizontal = 15.dp)
                                )
                                NavigationDrawerItem(
                                    label = { Text("О нас") },
                                    selected = false,
                                    onClick = {
                                        scope.launch {
                                            drawerState.close()
                                            navController.navigate("about")
                                        }
                                    },
                                    colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.White),
                                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                                )
                            }
                        }
                    },
                    content = {
                        NavHost(navController = navController, startDestination = "recipe-catalog") {
                            composable("recipe-catalog") {
                                RecipeCatalog(
                                    recipes = recipeSamples,
                                    onClickMenu = { scope.launch { drawerState.open() }},
                                    navController = navController
                                )
                            }
                            composable(route ="ingredient-search") {
                                IngredientSearch(
                                    onClickMenu = { scope.launch { drawerState.open() }},
                                    //navController = navController
                                )
                            }
                            composable("about") {
                                AboutPage(onClickMenu = { scope.launch { drawerState.open() }})
                            }
                            composable("recipe/{recipeName}"){ backStackEntry ->
                                val recipeName = backStackEntry.arguments?.getString("recipeName")
                                if(recipeName != null) {
                                    val recipeObject: Recipe? =
                                        findRecipe(recipes = recipeSamples, recipeName)
                                    if(recipeObject != null){
                                        RecipePage(recipeData = recipeObject)
                                    }
                                }
                            }
                        }
                    }
                )

            }
        }
    }
}

fun findRecipe(recipes: List<Recipe>, recipeName: String): Recipe?{
    for (recipe in recipes){
        if(recipe.name == recipeName)
            return recipe
    }
    return null
}

@Composable
fun customShape() = MyShape()

class MyShape : Shape {
    var leftSpaceWidth: Float? = null
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        leftSpaceWidth = size.width * 1 / 3
        return Outline.Rectangle(Rect(left = 0f, top = 0f, right = size.width * 2 / 3, bottom = size.height))
    }
}