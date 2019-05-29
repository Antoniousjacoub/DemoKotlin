package com.linkdev.demokotlin.common.helpers

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.net.ConnectivityManager
import android.provider.Settings
import android.support.v7.app.AlertDialog
import com.linkdev.demokotlin.R

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
            .setTitle(context.getString(R.string.GPSPermissionNeeded))
            .setMessage(context.getString(R.string.pleaseEnableGPS))
            .setPositiveButton(context.getString(R.string.ok)) { _, _ ->
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                context.startActivity(intent)
            }
            .setCancelable(false)
            .create()
            .show()

        return false
    }
}