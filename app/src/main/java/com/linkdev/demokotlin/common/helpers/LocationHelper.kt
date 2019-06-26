package com.linkdev.demokotlin.common.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle

class LocationHelper {
    private var lm: LocationManager? = null
    private var myLocationResult: MyLocationResult? = null
    private var GPSEnabled: Boolean? = false
    private var networkEnabled: Boolean? = false

    private val locationListenerGps = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            myLocationResult?.gotLocation(location)
            lm?.removeUpdates(this)
        }

        override fun onProviderDisabled(provider: String) {}

        override fun onProviderEnabled(provider: String) {}

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    }

    private val locationListenerNetwork = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            myLocationResult?.gotLocation(location)
            lm?.removeUpdates(this)
            lm?.removeUpdates(locationListenerGps)
        }

        override fun onProviderDisabled(provider: String) {}

        override fun onProviderEnabled(provider: String) {}

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    }

    @SuppressLint("MissingPermission")
    fun getLocation(context: Context, result: MyLocationResult): Boolean {
        myLocationResult = result
        lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        GPSEnabled = lm?.isProviderEnabled(LocationManager.GPS_PROVIDER)
        networkEnabled = lm?.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (!GPSEnabled!! && !networkEnabled!!)
            return false

        if (GPSEnabled!!)
            lm?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListenerGps)
        if (networkEnabled!!)
            lm?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0f, locationListenerNetwork)
        return true
    }


    abstract class MyLocationResult {
        internal abstract fun gotLocation(location: Location?)
    }
}
