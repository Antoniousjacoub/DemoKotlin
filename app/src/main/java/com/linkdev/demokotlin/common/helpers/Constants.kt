package com.linkdev.demokotlin.common.helpers

import com.linkdev.demokotlin.BuildConfig

object Constants {
    object General {
        const val SPLASH_DELAY = 2000

    }

    object Languages {
        const val LOCALE_ENGLISH = "en"
        const val LOCALE_ARABIC = "ar"
        const val APP_LOCALE_KEY = "APP_LOCALE_KEY"


    }

    object Network {
        const val BASE_URL = BuildConfig.BASE_URL
        const val SOURCE = BuildConfig.SOURCE
    }

    object Keys {
        const val ARTICLE_KEY = "ARTICLE_KEY"
        const val NAME: String = "NAME"
        const val EMAIL: String = "EMAIL"
        const val PHOTO_URL: String = "PHOTO_URL"
    }

    object Date {
        const val inputPattern = "yyyy-MM-dd"
        const val outputPattern = "MMM dd, yyyy"

    }
}