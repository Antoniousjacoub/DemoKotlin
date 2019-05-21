package com.linkdev.demokotlin.ui.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.linkdev.demokotlin.R

abstract class BaseFragment : Fragment() {
    private lateinit var myToolbar: Toolbar

    protected abstract fun onViewReady(context: Context)

    @LayoutRes
    protected abstract fun layoutViewId(): Int

    protected abstract fun initializeViews(v: View)

    protected abstract fun setListeners()

    protected abstract fun setObservers()

    abstract fun showProgress(shouldShow: Boolean)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            onViewReady(activity!!)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(layoutViewId(), container, false)
        initializeViews(rootView)
        setListeners()
        return rootView
    }

    protected fun setToolbar(toolbar: Toolbar, title: String, showUpButton: Boolean, withElevation: Boolean) {
        if (activity == null) return
        myToolbar = toolbar
        myToolbar.title = title
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && withElevation) {
            toolbar.elevation = resources.getDimension(R.dimen.margin_medium)
        }
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        if (showUpButton) {
            val actionBar = (activity as AppCompatActivity).supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }
}