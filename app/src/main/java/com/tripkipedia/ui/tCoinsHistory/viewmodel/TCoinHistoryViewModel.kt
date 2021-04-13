package com.tripkipedia.ui.tCoinsHistory.viewmodel

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
import com.tripkipedia.interfaces.TopBarClickListener
import com.tripkipedia.ui.tCoinsHistory.utils.TCoinAdapter

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class TCoinHistoryViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityTCoinsHistoryBinding
    lateinit var mContext: Context
    private var dataTCoin: MutableList<String> = ArrayList<String>()
    lateinit var tCoinAdapter: TCoinAdapter


    fun setBinder(binder: ActivityTCoinsHistoryBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        binder.topbar.isBackShow = true
        binder.topbar.isTextShow = true
        binder.topbar.tvTitle.setText("T-Coins History")
        binder.topbar.topBarClickListener=SlideMenuClickListener()
        init()

    }

    fun init() {

        for (i: Int in 0..4) {
            dataTCoin.add("l" + i)
        }
        tCoinAdapter = TCoinAdapter(mContext)
        tCoinAdapter.addAll(dataTCoin as ArrayList<String>)
        binder.rvTCoins.adapter = tCoinAdapter


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


