package com.linkdev.demokotlin.common.helpers

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.net.ConnectivityManager
import android.provider.Settings
import android.support.v7.app.AlertDialog

object Utils {
    fun checkConnection(context: Context): Boolean {
        val connectivityManager = (context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        val activeNetInfo = connectivityManager.activeNetworkInfo
        return activeNetInfo != null && activeNetInfo.isConnected
    }

    fun checkEnableGPS(context: Context?): Boolean {
        if (context == null) return false
        val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return true
        }
        AlertDialog.Builder(context)
            .setTitle("GPS Permission Needed")
            .setMessage("Please enable your GPS")
            .setPositiveButton("OK") { dialog, which ->
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                context.startActivity(intent)
            }
            .setCancelable(false)
            .create()
            .show()

        return false
    }
}