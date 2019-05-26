package com.linkdev.demokotlin.common.helpers

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.View
import com.linkdev.demokotlin.R

object SnackbarHelper {
    fun showErrorMessage(context: Context, view: View, message: Int) {
        val snackbar = Snackbar.make(view, context.getString(message), Snackbar.LENGTH_LONG)
        val sbView = snackbar.view
        sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorError))
        snackbar.show()
    }

    fun showWarningMessage(context: Context?, view: View?, message: Int) {
        if (context == null || view == null)
            return
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        val sbView = snackbar.view
        sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWarning))
        snackbar.show()
    }

    fun showSuccessMessage(context: Context, view: View?, messageRes: Int) {
        if (view == null)
            return
        val snackbar = Snackbar.make(view, messageRes, Snackbar.LENGTH_LONG)
        val sbView = snackbar.view
        sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorSuccess))
        snackbar.show()
    }


}