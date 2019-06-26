package com.linkdev.demokotlin.common.helpers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.IntentSender
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.Uri
import android.widget.Toast
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import com.linkdev.demokotlin.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object Utils {

    fun isTablet(context: Context): Boolean {
        return context.resources.getBoolean(R.bool.isTablet)
    }

    fun checkConnection(context: Context): Boolean {
        val connectivityManager = (context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        val activeNetInfo = connectivityManager.activeNetworkInfo
        return activeNetInfo != null && activeNetInfo.isConnected
    }

    fun parseDate(oldDateFormat: String?, inputPattern: String?, outputPattern: String?): String {
        if (oldDateFormat == null || inputPattern == null || outputPattern == null)
            return ""
        val inputFormat = SimpleDateFormat(inputPattern, Locale.ENGLISH)
        val outputFormat = SimpleDateFormat(outputPattern, Locale.ENGLISH)
        return try {
            val date = inputFormat.parse(oldDateFormat)
            outputFormat.format(date)

        } catch (e: ParseException) {
            e.printStackTrace()
            ""
        }

    }

    private fun createLocationRequest(): LocationRequest? {
        return LocationRequest.create()?.apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    fun checkEnableGPS(context: Activity?, code: Int): Boolean {
        if (context == null) return false
        val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return true
        }
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(createLocationRequest()!!)
        val client: SettingsClient = LocationServices.getSettingsClient(context)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())
        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    exception.startResolutionForResult(
                        context,
                        code
                    )
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                    sendEx.printStackTrace()
                }
            }
        }
        return false
    }

    private fun showMessage(context: Context?, content: String?) {
        if (context == null || content == null)
            return
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show()
    }

    fun openWebsiteOnBrowser(context: Context, url: String?) {
        if (url == null || url == "")
            return //we don't need to open website if this check is true

        var webpage = Uri.parse(url)

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            webpage = Uri.parse("http://$url")
        }

        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(context.packageManager) != null) {
            intent.flags = FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        } else {
            showMessage(context, context.getString(R.string.no_apps_can_resolve_the_intent))
        }
    }

}