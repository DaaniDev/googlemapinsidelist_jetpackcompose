package com.daanidev.googlemapinlist

import com.google.android.libraries.maps.model.LatLng

data class MapModel (val pickUp:LatLng,val destination:LatLng,val date:String,val time:String
,val pickUpTitle:String,val destinationTitle:String)