package com.linkdev.demokotlin.ui.base

import android.content.Context
import com.linkdev.demokotlin.common.helpers.Utils

open class BaseRepository {

    open fun isConnected(context: Context): Boolean {
        return Utils.checkConnection(context)
    }

}
