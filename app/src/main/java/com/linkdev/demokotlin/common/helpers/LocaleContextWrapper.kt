package com.linkdev.demokotlin.common.helpers

import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.LocaleList
import java.util.*

class LocaleContextWrapper(base: Context) : ContextWrapper(base) {
    companion object {
        fun wrap(context: Context, language: String): ContextWrapper {
            var context1 = context
            val res = context1.resources
            val configuration = res.configuration
            val newLocale = Locale(language)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                configuration.setLocale(newLocale)
                val localeList = LocaleList(newLocale)
                LocaleList.setDefault(localeList)
                configuration.locales = localeList
                context1 = context1.createConfigurationContext(configuration)
            } else {
                configuration.setLocale(newLocale)
                context1 = context1.createConfigurationContext(configuration)
            }
            return LocaleContextWrapper(context1)
        }
    }
}

