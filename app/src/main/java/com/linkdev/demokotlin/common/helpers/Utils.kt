package com.linkdev.demokotlin.common.helpers

import android.content.Context
import android.net.ConnectivityManager

 class Utils {
    fun checkConnection(context: Context): Boolean {
        val connectivityManager = (context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        val activeNetInfo = connectivityManager.activeNetworkInfo
        return activeNetInfo != null && activeNetInfo.isConnected
    }

}