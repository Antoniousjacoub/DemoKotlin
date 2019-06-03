package com.linkdev.demokotlin.ui.base


import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.widget.FrameLayout
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.models.dto.DrawerItem
import com.linkdev.demokotlin.ui.anko.MainActivityAnko
import com.linkdev.demokotlin.ui.location.LocationActivity
import kotlinx.android.synthetic.main.activity_base_for_drawer.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.startActivity
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
        val itemTwo = DrawerItem()
        itemTwo.itemName = getString(R.string.tryAnko)
        itemTwo.imgResID = R.drawable.ic_explore
        dataListOFMenuItems.add(itemTwo)

        val customDrawerAdapter = CustomDrawerAdapter(this, dataListOFMenuItems, this)
        val layoutManager = LinearLayoutManager(this)
        rv_menuList.layoutManager = layoutManager
        rv_menuList.adapter = customDrawerAdapter

    }


    override fun onItemSideMenuClicked(position: Int) {
        drawer_layout.closeDrawers()
        when (position) {
            SideMenuItems.OPEN_MAP -> {
                LocationActivity.startActivity(this)
            }
            SideMenuItems.OPEN_ANKO_ACTIVITY -> {
                startActivity<MainActivityAnko>()
            }
        }
    }

    private object SideMenuItems {
        const val OPEN_MAP = 0
        const val OPEN_ANKO_ACTIVITY = 1

    }
}
