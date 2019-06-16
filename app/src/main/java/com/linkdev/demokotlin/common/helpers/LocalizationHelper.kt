package com.linkdev.demokotlin.common.helpers

import android.content.Context
import android.content.res.Resources

import java.util.Locale

object LocalizationHelper {


    private const val DEFAULT_LOCALE = Constants.Languages.LOCALE_ARABIC

    fun changeAppLanguage(languageToLoad: String?, ctx: Context) {

        try {
            if (languageToLoad != null && "" != languageToLoad) {
                val res = ctx.applicationContext.resources
                val config = res.configuration
                val locale = Locale(languageToLoad)
                Locale.setDefault(locale)
                config.setLocale(locale)
                res.updateConfiguration(config, res.displayMetrics)
                // save new language to shared preferences
                AppPreferences.setString(Constants.Languages.APP_LOCALE_KEY, languageToLoad, ctx)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    fun getLanguage(context: Context): String? {
        // get language from shared preferences
        return AppPreferences.getString(Constants.Languages.APP_LOCALE_KEY, context, DEFAULT_LOCALE)
    }


}
