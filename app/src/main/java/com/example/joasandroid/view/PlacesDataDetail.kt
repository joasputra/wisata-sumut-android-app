package com.example.joasandroid.view

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.joasandroid.MapScreen
import com.example.joasandroid.MapScreen.Companion.EXTRA_LATITUDE
import com.example.joasandroid.MapScreen.Companion.EXTRA_LONGITUDE
import com.example.joasandroid.MapScreen.Companion.EXTRA_MARKER_INFO
import com.example.joasandroid.R
import com.example.joasandroid.model.PlacesData
import com.example.joasandroid.ui.theme.Purple500

@Composable
fun PlacesDataDetail(data: PlacesData) {

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Purple500),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Wonderful Sumatera Utara",
                color = White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.padding(20.dp))
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
                modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                alignment = Alignment.Center
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                text = data.name,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Description",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(6.dp),
                color = Blue,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
            )
            Spacer(modifier = Modifier.padding(1.dp))
            Text(
                text = data.desc,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(6.dp),
                color = Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 15.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {
                        if (requestPermissionsIfNecessary(context)) {
                            context.startActivity(Intent(context, MapScreen::class.java).apply {
                                putExtra(EXTRA_LATITUDE, data.lat)
                                putExtra(EXTRA_LONGITUDE, data.long)
                                putExtra(EXTRA_MARKER_INFO, data.name)
                            })
                        }
                        println("CEK STATUS ${requestPermissionsIfNecessary(context)}")
                    },
                    modifier = Modifier
                        .wrapContentWidth()
                ) {
                    Image(
                        painterResource(id = R.drawable.ic_baseline_map_24),
                        contentDescription = "icon gmaps",
                    )
                    Text(
                        text = "Maps",
                    )
                }
            }
            Spacer(modifier = Modifier.padding(20.dp))
        }

    }
}

private fun requestPermissionsIfNecessary(context: Context): Boolean {
    val permissionsToRequest = ArrayList<String>()
    val permissions = arrayOf(
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
    )
    for (permission in permissions) {
        if (ContextCompat.checkSelfPermission(context, permission)
            != PackageManager.PERMISSION_GRANTED
        ) {
            permissionsToRequest.add(permission)
        }
    }
    println("CEK ARRAY PERMISSION ${permissionsToRequest}")
    if (permissionsToRequest.size > 0) {
        ActivityCompat.requestPermissions(
            context as Activity,
            permissionsToRequest.toTypedArray(),
            101
        )
        return false
    } else {
        return true
    }
}
