package com.tripkipedia.ui.myProfile.passportDetails.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import com.tripkipedia.R
import com.tripkipedia.apputils.Utils
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.*
import com.tripkipedia.interfaces.TopBarClickListener
import com.tripkipedia.ui.myProfile.passportDetails.myPassport.faceRecognization.view.FaceRecognizationActivity
import com.tripkipedia.ui.myProfile.passportDetails.myPassport.view.MyPassportActivity

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class PassportDetailsViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityPassportDetailsBinding
    lateinit var mContext: Context


    fun setBinder(binder: ActivityPassportDetailsBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        binder.topbar.isBackShow = true
        binder.topbar.isTextShow = true
        binder.topbar.tvTitle.setText("Passport Details")
        this.binder.topbar.topBarClickListener = SlideMenuClickListener()
        init()
        if ((mContext as Activity).intent.getStringExtra("flag").contentEquals("MyProfileActivity")) {
            binder.llAddPassport.visibility = View.VISIBLE
            binder.llFaceSetupSuccess.visibility = View.GONE
        } else {
            binder.llAddPassport.visibility = View.GONE
            binder.llFaceSetupSuccess.visibility = View.VISIBLE
        }

    }

    fun init() {


    }

    inner class ViewClickHandler {
        fun onAddPassportClick() {
            val i = Intent(mContext, MyPassportActivity::class.java)
            (mContext as Activity).startActivity(i)
            (mContext as Activity).finish()
        }

        fun onFaceSuccessBackClick() {

            (mContext as Activity).finish()
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


