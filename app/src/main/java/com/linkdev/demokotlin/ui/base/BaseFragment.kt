package com.linkdev.demokotlin.ui.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment : Fragment() {
    protected abstract fun onViewReady(context: Context)

    @LayoutRes
    protected abstract fun layoutViewId(): Int

    protected abstract fun setListeners()

    protected abstract fun setObservers()
    protected abstract fun initializeViews(v: View)
    abstract fun showProgress(shouldShow: Boolean)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            onViewReady(activity!!)
            setListeners()

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(layoutViewId(), container, false)
        initializeViews(rootView)
        return rootView
    }

}