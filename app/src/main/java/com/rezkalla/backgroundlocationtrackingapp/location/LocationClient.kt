package com.rezkalla.backgroundlocationtrackingapp.location

import android.location.Location
import kotlinx.coroutines.flow.Flow
import java.lang.Exception


interface LocationClient {
    // interval : how often you want to get location updates
    fun getLocationUpdates(interval:Long) : Flow<Location>

    class LocationException(message:String):Exception()
}