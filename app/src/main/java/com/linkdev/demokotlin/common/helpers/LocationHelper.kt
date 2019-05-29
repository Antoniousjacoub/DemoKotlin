package com.linkdev.demokotlin.common.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle

import java.util.Timer
import java.util.TimerTask

class LocationHelper {
    private var timer1: Timer? = null
    private var lm: LocationManager? = null
    private var myLocationResult: MyLocationResult? = null
    private var gps_enabled = false
    private var network_enabled = false

    private val locationListenerGps = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            timer1!!.cancel()
            myLocationResult!!.gotLocation(location)
            lm!!.removeUpdates(this)
//            lm!!.removeUpdates(locationListenerNetwork)
        }

        override fun onProviderDisabled(provider: String) {}

        override fun onProviderEnabled(provider: String) {}

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    }

    private val locationListenerNetwork = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            timer1!!.cancel()
            myLocationResult!!.gotLocation(location)
            lm!!.removeUpdates(this)
            lm!!.removeUpdates(locationListenerGps)
        }

        override fun onProviderDisabled(provider: String) {}

        override fun onProviderEnabled(provider: String) {}

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    }

    @SuppressLint("MissingPermission")
    fun getLocation(context: Context, result: MyLocationResult): Boolean {
        //I use MyLocationResult callback class to pass location value from LocationHelper to user code.
        myLocationResult = result
        if (lm == null)
            lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        //exceptions will be thrown if provider is not permitted.
        if (lm != null)
            gps_enabled = lm!!.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (lm != null)
            network_enabled = lm!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        //don't start listeners if no provider is enabled
        if (!gps_enabled && !network_enabled)
            return false

        if (gps_enabled && lm != null)
            lm!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListenerGps)
        if (network_enabled && lm != null)
            lm!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0f, locationListenerNetwork)
        timer1 = Timer()
        timer1!!.schedule(GetLastLocation(), 20000)
        return true
    }

    internal inner class GetLastLocation : TimerTask() {
        @SuppressLint("MissingPermission")
        override fun run() {
            lm!!.removeUpdates(locationListenerGps)
            lm!!.removeUpdates(locationListenerNetwork)

            var net_loc: Location? = null
            var gps_loc: Location? = null
            if (gps_enabled)
                gps_loc = lm!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (network_enabled)
                net_loc = lm!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

            //if there are both values use the latest one
            if (gps_loc != null && net_loc != null) {
                if (gps_loc.time > net_loc.time)
                    myLocationResult!!.gotLocation(gps_loc)
                else
                    myLocationResult!!.gotLocation(net_loc)
                return
            }

            if (gps_loc != null) {
                myLocationResult!!.gotLocation(gps_loc)
                return
            }
            if (net_loc != null) {
                myLocationResult!!.gotLocation(net_loc)
                return
            }
            myLocationResult!!.gotLocation(null)
        }
    }

    abstract class MyLocationResult {
        internal abstract fun gotLocation(location: Location?)
    }
}
