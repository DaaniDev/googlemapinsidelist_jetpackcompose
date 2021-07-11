package com.daanidev.googlemapinlist

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.daanidev.googlemapinlist.ui.theme.GoogleMapInListTheme
import com.daanidev.googlemapinlist.utils.rememberMapViewWithLifecycle
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions
import com.google.android.libraries.maps.model.PolylineOptions
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val mapRepository = MapRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GoogleMapInListTheme {

                Surface(
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(bottom = 10.dp)
                ) {

                    Column {

                        LazyColumn {
                            item {


                                mapRepository.getMapData().forEach {

                                    Card(it) { obj ->

                                        Toast.makeText(
                                            this@MainActivity,
                                            "Item clicked with destination " + obj.destinationTitle,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                            }


                        }
                    }
                }
            }
        }
    }
}


@Composable
fun Card(
    objMap: MapModel,
    modifier: Modifier = Modifier,
    item: (MapModel) -> Unit
) {
    val mapView = rememberMapViewWithLifecycle()
    Surface(
        modifier = modifier
            .padding(4.dp),
        color = Color.White,
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    item.invoke(objMap)
                }
                .padding(top = 5.dp)
                .wrapContentHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,

            )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(start = 5.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = objMap.date,
                    textAlign = TextAlign.Start,
                    color = Color.Black,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(end = 5.dp)
                )
                Text(
                    text = objMap.time,
                    textAlign = TextAlign.Start,
                    color = Color.Black,
                    modifier = Modifier.wrapContentSize()
                )
            }

            Column(
                modifier = Modifier
                    .height(220.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top
            ) {

                AndroidView({ mapView }) { mapView ->
                    CoroutineScope(Dispatchers.Main).launch {
                        val map = mapView.awaitMap()
                        // map.uiSettings.isZoomControlsEnabled = true

                        val pickUp = objMap.pickUp
                        val destination = objMap.destination
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(destination, 5f))
                        val markerOptions = MarkerOptions()
                            .title(objMap.pickUpTitle)
                            .position(pickUp)
                        map.addMarker(markerOptions)

                        val markerOptionsDestination = MarkerOptions()
                            .title(objMap.destinationTitle)
                            .position(destination)
                        map.addMarker(markerOptionsDestination)

                        map.addPolyline(
                            PolylineOptions().add(
                                pickUp,
                                LatLng(-34.747, 145.592),
                                LatLng(-34.364, 147.891),
                                LatLng(-33.501, 150.217),
                                LatLng(-32.306, 149.248),
                                destination
                            )
                        )

                    }
                }

            }


        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GoogleMapInListTheme {
        //  Greeting("Android")
    }
}