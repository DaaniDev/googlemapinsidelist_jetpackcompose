package com.daanidev.googlemapinlist

import com.google.android.libraries.maps.model.LatLng

class MapRepository {


    fun getMapData(): List<MapModel> {

        return listOf(
            MapModel(
                LatLng(-35.016, 143.321),
                LatLng(-32.491, 147.309),
                "07-11-2021",
                "16:00",
                "Pickup one",
                "Destination one"
            ),
            MapModel(
                LatLng(-35.016, 143.321),
                LatLng(-32.491, 147.309),
                "05-08-2021",
                "12:00",
                "Pickup two",
                "Destination two"
            ),
            MapModel(
                LatLng(-35.016, 143.321),
                LatLng(-32.491, 147.309),
                "03-20-2021",
                "08:00",
                "Pickup three",
                "Destination three"
            ),
            MapModel(
                LatLng(-35.016, 143.321),
                LatLng(-32.491, 147.309),
                "07-28-2021",
                "18:00",
                "Pickup four",
                "Destination four"
            ),
            MapModel(
                LatLng(-35.016, 143.321),
                LatLng(-32.491, 147.309),
                "07-06-2021",
                "02:00",
                "Pickup five",
                "Destination five"
            ),
            MapModel(
                LatLng(-35.016, 143.321),
                LatLng(-32.491, 147.309),
                "07-10-2021",
                "22:00",
                "Pickup six",
                "Destination six"
            )

        )
    }
}