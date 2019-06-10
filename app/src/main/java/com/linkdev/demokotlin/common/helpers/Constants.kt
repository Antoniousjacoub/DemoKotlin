package com.linkdev.demokotlin.common.helpers

import com.linkdev.demokotlin.BuildConfig

object Constants {
    const val BASE_URL = BuildConfig.BASE_URL
    const val SOURCE = BuildConfig.SOURCE

    object Keys {
        const val ARTICLE_KEY = "ARTICLE_KEY"
    }

    object Date {
        const val inputPattern = "yyyy-MM-dd"
        const val outputPattern = "MMM dd, yyyy"

    }
}