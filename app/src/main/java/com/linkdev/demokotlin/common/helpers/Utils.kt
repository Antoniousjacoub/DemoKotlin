package com.linkdev.demokotlin.common.helpers

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.Uri
import android.provider.Settings
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.linkdev.demokotlin.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {
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

    fun showMessage(context: Context?, content: String?) {
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