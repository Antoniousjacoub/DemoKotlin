package com.linkdev.demokotlin.ui.location

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.linkdev.demokotlin.ui.base.BaseViewModel

class LocationViewModel(application: Application, private val locationListener: LocationListener) :
    BaseViewModel(application),
    GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {
    private var mGoogleApiClient: GoogleApiClient? = null

    @Synchronized
    fun buildGoogleApiClient() {
        mGoogleApiClient = GoogleApiClient.Builder(getApplication())
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build()

        mGoogleApiClient!!.connect()
    }

    override fun onConnectionSuspended(p0: Int) {

    }

    @SuppressLint("MissingPermission")
    override fun onConnected(p0: Bundle?) {
        val mLocationRequest = LocationRequest()
        mLocationRequest.interval = 1000
        mLocationRequest.fastestInterval = 1000
        mLocationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, locationListener)

    }

    override fun onCleared() {
        super.onCleared()
        mGoogleApiClient!!.disconnect()
    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }
}




