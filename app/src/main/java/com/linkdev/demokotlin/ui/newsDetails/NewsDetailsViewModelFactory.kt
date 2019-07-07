package com.linkdev.demokotlin.ui.newsDetails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle

class NewsDetailsViewModelFactory(private val application: Application?, private val bundle: Bundle?) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsDetailsViewModel::class.java)) {
            return NewsDetailsViewModel(application, bundle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

}