package com.linkdev.demokotlin.ui.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.common.helpers.LocaleContextWrapper
import com.linkdev.demokotlin.common.helpers.LocalizationHelper
import com.linkdev.demokotlin.common.helpers.LocalizationHelper.getLanguage

abstract class BaseActivity : AppCompatActivity() {

    private var myToolbar: Toolbar? = null

    @LayoutRes
    protected abstract fun layoutViewId(): Int

    protected abstract fun initializeViews()

    protected abstract fun setListeners()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocalizationHelper.changeAppLanguage(LocalizationHelper.getLanguage(this), this)
        setContentView(layoutViewId())
        initializeViews()
        setListeners()

    }

    protected fun addFragment(@IdRes containerViewId: Int, fragment: Fragment, fragmentTag: String) {
        supportFragmentManager.beginTransaction()
            .add(containerViewId, fragment, fragmentTag)
            .disallowAddToBackStack()
            .commit()
    }

    protected fun replaceFragment(@IdRes containerViewId: Int, fragment: Fragment, fragmentTag: String) {
        supportFragmentManager.beginTransaction().replace(containerViewId, fragment, fragmentTag).commit()
    }

    protected fun setToolbar(toolbar: Toolbar, title: String, showUpButton: Boolean, withElevation: Boolean) {
        myToolbar = toolbar
        myToolbar?.title = title
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && withElevation) {
            toolbar.elevation = resources.getDimension(R.dimen.margin_medium)
        }
        setSupportActionBar(myToolbar)

        if (showUpButton) {
            val actionBar = supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    fun setToolbarTitle(title: String) {
        myToolbar?.title = title

    }

    override fun attachBaseContext(newBase: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val contextWrapper = LocaleContextWrapper.wrap(newBase, getLanguage(newBase)!!)
            super.attachBaseContext(contextWrapper)
        } else {
            super.attachBaseContext(newBase)
        }
    }
}