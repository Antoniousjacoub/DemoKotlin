package com.linkdev.demokotlin.ui.base

import android.content.Context
import com.linkdev.demokotlin.common.helpers.Utils
import io.reactivex.Single
import java.util.concurrent.Callable

open class BaseRepository {
     fun <S> createSingle(callable: Callable<S>): Single<S> {
        return Single.fromCallable(callable)
    }
    fun isConnected(context: Context): Boolean {
        return Utils.checkConnection(context)
    }

}
