package com.tripkipedia.ui.voucherDetails.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.View
import com.tripkipedia.R
import com.tripkipedia.apputils.Utils
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.ActivityMyRewardsBinding
import com.tripkipedia.databinding.ActivityReviewsBinding
import com.tripkipedia.databinding.ActivityTCoinsHistoryBinding
import com.tripkipedia.databinding.ActivityVoucherDetailsBinding
import com.tripkipedia.interfaces.TopBarClickListener
import com.tripkipedia.ui.tCoinsHistory.utils.TCoinAdapter

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class VoucherDetailsViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityVoucherDetailsBinding
    lateinit var mContext: Context


    fun setBinder(binder: ActivityVoucherDetailsBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        binder.topbar.isBackShow = true
        binder.topbar.isTextShow = true
        binder.topbar.tvTitle.setText("Pizza Hut")
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


