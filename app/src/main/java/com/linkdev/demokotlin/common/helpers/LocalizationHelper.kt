package com.linkdev.demokotlin.common.helpers

import android.content.Context
import android.content.res.Configuration
import java.util.*


object LocalizationHelper {


    private const val DEFAULT_LOCALE = Constants.Languages.LOCALE_ENGLISH

    fun changeAppLanguage(languageToLoad: String?, ctx: Context) {

        try {
            if (languageToLoad != null && "" != languageToLoad) {
                val res = ctx.applicationContext.resources
                val config = res.configuration
                val locale = Locale(languageToLoad)
                updateResources(ctx, config, locale)
                AppPreferences.setString(Constants.Languages.APP_LOCALE_KEY, languageToLoad, ctx)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun updateResources(context: Context, configuration: Configuration, locale: Locale) {
        Locale.setDefault(locale)
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        context.createConfigurationContext(configuration)
    }


    fun getLanguage(context: Context): String? {
        // get language from shared preferences
        return AppPreferences.getString(Constants.Languages.APP_LOCALE_KEY, context, DEFAULT_LOCALE)
    }


}
