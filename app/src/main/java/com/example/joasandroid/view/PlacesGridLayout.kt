package com.example.joasandroid.view

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.joasandroid.R
import com.example.joasandroid.model.PlacesData
import com.example.joasandroid.ui.theme.Purple500
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ExperimentalFoundationApi
@Composable
fun PlaceGrid(navController: NavController) {

    val context = LocalContext.current
    val dataFileString = getJsonDataFromAsset(context, "PlacesList.json")
    val gson = Gson()
    val gridSampleType = object : TypeToken<List<PlacesData>>() {}.type
    val placeData: List<PlacesData> = gson.fromJson(dataFileString, gridSampleType)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = Purple500)
                .padding(6.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Wonderful Sumatera Utara",
                color = White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        LazyVerticalGrid(
            cells = GridCells.Fixed(3),
            modifier = Modifier.padding(10.dp)
        ) {
            items(placeData) { data ->
                PlaceDataGridItem(data, navController)
            }
        }
    }
}

@Composable
fun PlaceDataGridItem(data: PlacesData, navController: NavController) {
    Card(
        modifier = Modifier
            .clickable {
                val itemVal = Gson().toJson(data)
                navController.navigate("grid_detail/$itemVal")
            }
            .padding(10.dp)
            .fillMaxSize(),
        elevation = 5.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(modifier = Modifier) {
            Image(
                painter = painterResource(
                    id = when (data.id) {
                        1L -> R.drawable.aeksijornih1
                        2L -> R.drawable.airpanassipoholon2
                        3L -> R.drawable.airsodatarutung3
                        4L -> R.drawable.airterjunsipisopiso4
                        5L -> R.drawable.bukitkubu5
                        6L -> R.drawable.bukitlawang6
                        7L -> R.drawable.geositesipinsur7
                        8L -> R.drawable.kebuntehsidamanik8
                        9L -> R.drawable.mikieholidayfunland9
                        10L -> R.drawable.pantaipandansibolga10
                        11L -> R.drawable.tamaneden10011
                        12L -> R.drawable.tuktuksiadongsamosir12
                        else -> R.drawable.airterjunsipisopiso4
                    }
                ),
                contentDescription = "Grid Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(5.dp)),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.padding(3.dp))
            Text(
                text = data.name,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.padding(3.dp))
            Text(
                text = data.desc,
                modifier = Modifier
                    .padding(7.dp, 0.dp, 0.dp, 20.dp),
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

fun getJsonDataFromAsset(context: Context, data: String): String {
    return context.assets.open(data).bufferedReader().use { it.readText() }
}
