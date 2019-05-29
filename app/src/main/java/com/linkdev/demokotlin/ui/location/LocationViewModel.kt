package com.linkdev.demokotlin.ui.location

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.location.Location
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.common.helpers.LocationHelper
import com.linkdev.demokotlin.ui.base.BaseViewModel

class LocationViewModel(application: Application) :
    BaseViewModel(application) {
    private val onSuccessLoadLocation: MutableLiveData<Location> = MutableLiveData()
    fun requestMyLocation() {
        onSetLoading(true)
        val locationResult = object : LocationHelper.MyLocationResult() {
            override fun gotLocation(location: Location?) {
                onSetLoading(false)
                //Got the location!
                if (location != null) {
                    onSuccessLoadLocation.postValue(location)
                } else {
                    onSetError(R.string.failedToGetLocation)
                }
            }
        }
        val myLocation = LocationHelper()
        myLocation.getLocation(getApplication(), locationResult)
    }

    fun getOnSuccessLoadLocation(): MutableLiveData<Location> {
        return onSuccessLoadLocation
    }
}




