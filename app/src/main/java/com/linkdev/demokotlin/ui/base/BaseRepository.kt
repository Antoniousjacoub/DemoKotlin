package com.linkdev.demokotlin.ui.base

import android.content.Context
import com.linkdev.demokotlin.common.helpers.Utils

open class BaseRepository {

    fun isConnected(context: Context): Boolean {
        return Utils().checkConnection(context)
    }

}
