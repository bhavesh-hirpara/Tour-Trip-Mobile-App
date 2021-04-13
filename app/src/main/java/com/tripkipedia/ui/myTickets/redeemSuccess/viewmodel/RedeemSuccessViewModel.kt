package com.tripkipedia.ui.myTickets.redeemSuccess.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentManager
import com.tripkipedia.R
import com.tripkipedia.apputils.Utils
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.ActivityTicketRedeemSuccessBinding
import com.tripkipedia.interfaces.TopBarClickListener

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class RedeemSuccessViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityTicketRedeemSuccessBinding
    lateinit var mContext: Context


    fun setBinder(
        binder: ActivityTicketRedeemSuccessBinding,
        supportFragmentManager: FragmentManager
    ) {
        this.binder = binder
        this.mContext = binder.root.context
        this.supportFragmentManager = supportFragmentManager
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        init()

    }

    fun init() {

    }


    inner class ViewClickHandler {

        fun onBackToHomeClick(view: View) {
            try {
                (mContext as Activity).finish()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


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

