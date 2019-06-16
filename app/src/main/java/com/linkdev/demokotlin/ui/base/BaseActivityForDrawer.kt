package com.linkdev.demokotlin.ui.base


import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.widget.FrameLayout
import com.facebook.login.LoginManager
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.common.helpers.AppPreferences
import com.linkdev.demokotlin.common.helpers.Constants
import com.linkdev.demokotlin.common.helpers.UIUtils
import com.linkdev.demokotlin.models.dto.DrawerItem
import com.linkdev.demokotlin.ui.location.LocationActivity
import com.linkdev.demokotlin.ui.splash.SplashActivity
import kotlinx.android.synthetic.main.activity_base_for_drawer.*
import kotlinx.android.synthetic.main.layout_nav_header.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import java.util.*

abstract class BaseActivityForDrawer : BaseActivity(), CustomDrawerAdapter.OnItemSideMenuClicked {

    override fun setContentView(layoutResID: Int) {
        val fullView = layoutInflater.inflate(R.layout.activity_base_for_drawer, null) as DrawerLayout
        val activityContainer = fullView.findViewById(R.id.flContent) as FrameLayout
        layoutInflater.inflate(layoutResID, activityContainer, true)
        super.setContentView(fullView)

    }

    protected fun setupDrawer() {

        val drawerToggle = ActionBarDrawerToggle(this, drawer_layout, tool_bar, R.string.open, R.string.close)
        drawerToggle.setHomeAsUpIndicator(R.drawable.ic_drawer)
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        val dataListOFMenuItems = ArrayList<DrawerItem>()
        val itemOne = DrawerItem()
        itemOne.itemName = getString(R.string.showLoactionOnMap)
        itemOne.imgResID = R.drawable.ic_explore
        dataListOFMenuItems.add(itemOne)
        val itemLoguout = DrawerItem()
        itemLoguout.itemName = getString(R.string.logout)
        itemLoguout.imgResID = R.drawable.ic_logout
        dataListOFMenuItems.add(itemLoguout)
        val customDrawerAdapter = CustomDrawerAdapter(this, dataListOFMenuItems, this)
        val layoutManager = LinearLayoutManager(this)
        rv_menuList.layoutManager = layoutManager
        rv_menuList.adapter = customDrawerAdapter
        tv_name.text = AppPreferences.getString(Constants.Keys.NAME, this, "")
        UIUtils.loadImageWithPicasso(
            AppPreferences.getString(Constants.Keys.PHOTO_URL, this, "")!!,
            image_header_menu,
            getDrawable(R.drawable.placeholder)!!,
            getDrawable(R.drawable.placeholder)!!
        )
    }


    override fun onItemSideMenuClicked(position: Int) {
        drawer_layout.closeDrawers()
        when (position) {
            SideMenuItems.OPEN_MAP -> {
                LocationActivity.startActivity(this)
            }
            SideMenuItems.LOGOUT -> {
                LoginManager.getInstance().logOut()
                SplashActivity.startActivity(this)
                AppPreferences.clearPerferences(this)
                finish()
            }
        }
    }

    private object SideMenuItems {
        const val OPEN_MAP = 0
        const val LOGOUT = 1

    }
}
