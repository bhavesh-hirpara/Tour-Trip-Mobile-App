package com.tripkipedia.ui.freebiesDetails.leaveAReviews.viewmodel

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
import com.tripkipedia.databinding.ActivityFreebiesDetailsBinding
import com.tripkipedia.databinding.ActivityLeaveAReviewBinding
import com.tripkipedia.interfaces.TopBarClickListener
import com.tripkipedia.ui.freebiesDetails.utils.ScreenSlidePage
import com.tripkipedia.ui.freebiesDetails.view.FreebiesDetailsActivity
import com.tripkipedia.ui.login.view.LoginActivity

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class LeaveAReviewsViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityLeaveAReviewBinding
    lateinit var mContext: Context



    fun setBinder(
        binder: ActivityLeaveAReviewBinding,
        supportFragmentManager: FragmentManager
    ) {
        this.binder = binder
        this.mContext = binder.root.context
        this.supportFragmentManager = supportFragmentManager
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        binder.topbar.isBackShow = true
        binder.topbar.isTextShow = true
        binder.topbar.isShareShow = true
        binder.topbar.isHomeShow = true
        binder.topbar.tvTitle.setText("Black Ball")
        this.binder.topbar.topBarClickListener = SlideMenuClickListener()
        init()

    }

    fun init() {

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
        }
    }

}

