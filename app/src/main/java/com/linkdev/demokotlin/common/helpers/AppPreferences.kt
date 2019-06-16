package com.linkdev.demokotlin.common.helpers

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object AppPreferences {

    fun clearPerferences(context: Context){
        val appPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        appPreferences.edit().clear().apply()
    }
    fun getString(key: String, ctx: Context, defaultValue: String): String? {
        val appPreferences = PreferenceManager.getDefaultSharedPreferences(ctx)
        return appPreferences.getString(key, defaultValue)
    }

    fun setString(key: String, value: String?, ctx: Context) {
        val appPreferences = PreferenceManager.getDefaultSharedPreferences(ctx)
        appPreferences.edit().putString(key, value).apply()
    }

    fun getBoolean(key: String, ctx: Context, defaultValue: Boolean): Boolean {
        val appPreferences = PreferenceManager.getDefaultSharedPreferences(ctx)
        return appPreferences.getBoolean(key, defaultValue)
    }

    fun setBoolean(key: String, value: Boolean, ctx: Context) {
        val appPreferences = PreferenceManager.getDefaultSharedPreferences(ctx)
        appPreferences.edit().putBoolean(key, value).apply()
    }
}
