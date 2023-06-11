package com.example.joasandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.joasandroid.model.PlacesData
import com.example.joasandroid.ui.theme.JoasandroidTheme
import com.example.joasandroid.view.PlaceGrid
import com.example.joasandroid.view.PlacesDataDetail
import com.google.gson.Gson

class MainActivity : ComponentActivity() {

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JoasandroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    NavigatePage()
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun NavigatePage() {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = "places_data"
    ) {
        composable("places_data") {
            PlaceGrid(navController = navHostController)
        }
        composable("grid_detail/{item}",
            arguments = listOf(
                navArgument("item") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("item")?.let { json ->
                val item = Gson().fromJson(json, PlacesData::class.java)
                PlacesDataDetail(data = item)
            }
        }

    }

}