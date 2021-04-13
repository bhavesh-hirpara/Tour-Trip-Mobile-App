package com.tripkipedia.ui.myTickets.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.tripkipedia.R
import com.tripkipedia.apputils.Utils
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.ActivityMyTicketsBinding
import com.tripkipedia.interfaces.TopBarClickListener
import com.tripkipedia.ui.login.view.LoginActivity
import com.tripkipedia.ui.myTickets.utils.ScreenSlidePage

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class MyTicketsViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityMyTicketsBinding
    lateinit var mContext: Context
    lateinit var screenSlidePage: ScreenSlidePage


    fun setBinder(
        binder: ActivityMyTicketsBinding,
        supportFragmentManager: FragmentManager
    ) {
        this.binder = binder
        this.mContext = binder.root.context
        this.supportFragmentManager = supportFragmentManager
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        binder.topbar.isMenuShow = true
        binder.topbar.isTextShow = true
        binder.run { topbar.tvTitle.setText(getLabelText(R.string.MyTickets)) }
        this.binder.topbar.topBarClickListener = SlideMenuClickListener()
        init()
        initDrawer(mContext)

    }

    fun init() {
        screenSlidePage =
            ScreenSlidePage(
                supportFragmentManager
            )
        binder.viewpager.setOffscreenPageLimit(screenSlidePage.count)
        binder.viewpager.setAdapter(screenSlidePage)
        binder.tablayout.setupWithViewPager(binder.viewpager)
        binder.viewpager.measure(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
    }


    inner class ViewClickHandler {


    }


    inner class SlideMenuClickListener : TopBarClickListener {
        override fun onTopBarClickListener(view: View?, value: String?) {
            Utils.hideKeyBoard(getContext(), view!!)
            if (value.equals(getLabelText(R.string.back))) {
                try {
                    (mContext as Activity).finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            if (value.equals(getLabelText(R.string.menu))) {
                try {
                    onTopMenuClick()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

}

