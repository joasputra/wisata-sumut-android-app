package com.example.joasandroid

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.joasandroid.map.OSMDroid
import com.example.joasandroid.ui.theme.JoasandroidTheme
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapController
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

class MapScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            lateinit var mapController: MapController
            val context = LocalContext.current

            JoasandroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Greeting("Android")
                    OSMDroid(
                        modifier = Modifier.fillMaxSize(),
                        onLoad = {
                            val lat = intent.getStringExtra(EXTRA_LATITUDE)?.toDouble()
                            val long = intent.getStringExtra(EXTRA_LONGITUDE)?.toDouble()
                            val markerName = intent.getStringExtra(EXTRA_MARKER_INFO)
                            val point = GeoPoint(lat ?: 0.0, long ?: 0.0)
                            Configuration.getInstance().apply {
                                load(
                                    context,
                                    context.getSharedPreferences("osmdroid", Context.MODE_PRIVATE)
                                )
                                userAgentValue = context.packageName
                            }
                            it.maxZoomLevel = 25.0
                            it.minZoomLevel = 5.0
                            it.setMultiTouchControls(true)
                            it.zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)
                            it.controller.setCenter(point)
                            it.controller.setZoom(16.0)

                            val workMarker = Marker(it).apply {
                                title = "Horas"
                                snippet = markerName
                                position = point
                                icon = AppCompatResources.getDrawable(context, R.drawable.ic_marker)
                            }
                            it?.overlays?.add(workMarker)

//                            val myLocationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context), it)
//                            myLocationOverlay.enableMyLocation()
//                            myLocationOverlay.enableFollowLocation()
//                            myLocationOverlay.isDrawAccuracyEnabled = true
//                            it.overlays.add(myLocationOverlay)
//                            it.controller.setCenter(myLocationOverlay.myLocation)
                        }

                    )
                }
            }
        }
    }

    companion object{
        const val EXTRA_LATITUDE = "extraLatitude"
        const val EXTRA_LONGITUDE = "extraLongitude"
        const val EXTRA_MARKER_INFO = "extraMarkerInfo"
    }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JoasandroidTheme {
        MapScreen()
    }
}
