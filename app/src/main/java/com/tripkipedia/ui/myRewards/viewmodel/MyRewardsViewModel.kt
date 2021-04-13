package com.tripkipedia.ui.myRewards.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.View
import com.tripkipedia.R
import com.tripkipedia.apputils.Utils
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.ActivityMyRewardsBinding
import com.tripkipedia.interfaces.TopBarClickListener
import com.tripkipedia.ui.myRewards.details.utils.RewardsVouchersAdapter
import com.tripkipedia.ui.myRewards.utils.MoreRewardsAdapter

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class MyRewardsViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityMyRewardsBinding
    lateinit var mContext: Context
    private var dataRewardsVouchers: MutableList<String> = ArrayList<String>()
    lateinit var rewardsVouchersAdapter: RewardsVouchersAdapter

    private var dataMoreRewards: MutableList<String> = ArrayList<String>()
    lateinit var moreRewardsAdapter: MoreRewardsAdapter


    fun setBinder(binder: ActivityMyRewardsBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        binder.topbar.isMenuShow = true
        binder.topbar.isTextShow = true
        binder.topbar.tvTitle.setText("My Rewards")
        binder.topbar.topBarClickListener = SlideMenuClickListener()
        initDrawer(mContext)
        init()

    }

    fun init() {

        for (i: Int in 0..4) {
            dataRewardsVouchers.add("l" + i)
        }
        rewardsVouchersAdapter = RewardsVouchersAdapter(mContext)
        rewardsVouchersAdapter.addAll(dataRewardsVouchers as ArrayList<String>)
        binder.rvRewardVouchers.adapter = rewardsVouchersAdapter

        for (i: Int in 0..1) {
            dataMoreRewards.add("l" + i)
        }
        moreRewardsAdapter = MoreRewardsAdapter(mContext)
        moreRewardsAdapter.addAll(dataMoreRewards as ArrayList<String>)
        binder.rvEarnMoreRewards.adapter = moreRewardsAdapter

    }

    inner class ViewClickHandler {


    }


}


