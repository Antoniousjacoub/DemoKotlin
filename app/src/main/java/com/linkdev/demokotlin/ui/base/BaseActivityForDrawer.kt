package com.linkdev.demokotlin.ui.base


import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.widget.FrameLayout
import com.facebook.login.LoginManager
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.common.helpers.AppPreferences
import com.linkdev.demokotlin.common.helpers.Constants
import com.linkdev.demokotlin.common.helpers.LocalizationHelper
import com.linkdev.demokotlin.common.helpers.UIUtils.loadImageWithPicasso
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
        val drawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.open, R.string.close)
        drawerToggle.setHomeAsUpIndicator(R.drawable.ic_drawer)
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        val dataListOFMenuItems = ArrayList<DrawerItem>()
        val itemOne = DrawerItem()
        itemOne.itemName = getString(R.string.showLoactionOnMap)
        itemOne.imgResID = R.drawable.ic_explore
        dataListOFMenuItems.add(itemOne)
        val changeLang = DrawerItem()
        changeLang.itemName = getString(R.string.changeLang)
        changeLang.imgResID = R.drawable.ic_change_langauge
        dataListOFMenuItems.add(changeLang)
        val itemLogout = DrawerItem()
        itemLogout.itemName = getString(R.string.logout)
        itemLogout.imgResID = R.drawable.ic_logout
        dataListOFMenuItems.add(itemLogout)
        val customDrawerAdapter = CustomDrawerAdapter(this, dataListOFMenuItems, this)
        val layoutManager = LinearLayoutManager(this)
        rvMenuList.layoutManager = layoutManager
        rvMenuList.adapter = customDrawerAdapter
        tvName.text = AppPreferences.getString(Constants.Keys.NAME, this, "")
        loadImageWithPicasso(
            AppPreferences.getString(Constants.Keys.PHOTO_URL, this, ""),
            imageHeaderMenu,
            getDrawable(R.drawable.placeholder),
            getDrawable(R.drawable.placeholder)
        )
    }


    override fun onItemSideMenuClicked(position: Int) {
        drawerLayout.closeDrawers()
        when (position) {
            SideMenuItems.OPEN_MAP -> {
                LocationActivity.startActivity(this)
            }
            SideMenuItems.CHANGE_LANG -> {
                if (LocalizationHelper.getLanguage(this) == Constants.Languages.LOCALE_ARABIC) {
                    AppPreferences.setString(
                        Constants.Languages.APP_LOCALE_KEY,
                        Constants.Languages.LOCALE_ENGLISH,
                        this
                    )
                } else {
                    AppPreferences.setString(
                        Constants.Languages.APP_LOCALE_KEY,
                        Constants.Languages.LOCALE_ARABIC,
                        this
                    )

                }
                LocalizationHelper.changeAppLanguage(LocalizationHelper.getLanguage(this), this)
                finish()
                startActivity(intent)


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
        const val CHANGE_LANG = 1
        const val LOGOUT = 2


    }
}
