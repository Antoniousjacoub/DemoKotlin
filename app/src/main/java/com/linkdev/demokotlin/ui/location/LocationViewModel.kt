package com.linkdev.demokotlin.ui.location

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.location.Location
import android.os.Bundle
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.ui.base.BaseViewModel

class LocationViewModel(application: Application) :
    BaseViewModel(application),
    GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, LocationListener {
    private var mGoogleApiClient: GoogleApiClient? = null
    private val onSuccessLoadLocation: MutableLiveData<Location> = MutableLiveData()

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
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this)

    }

    override fun onCleared() {
        super.onCleared()
        mGoogleApiClient!!.disconnect()
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        onSetError(R.string.failedToGetLocation)
    }

    override fun onLocationChanged(location: Location?) {
        onSuccessLoadLocation.postValue(location)
    }

    fun getOnSuccessLoadLocation(): MutableLiveData<Location> {
        return onSuccessLoadLocation
    }
}




