package com.tripkipedia.ui.myProfile.passportDetails.myPassport.faceRecognization.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import com.tripkipedia.R
import com.tripkipedia.apputils.Utils
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.ActivityFacialRecognizeSetupBinding
import com.tripkipedia.interfaces.TopBarClickListener
import com.tripkipedia.ui.myProfile.passportDetails.view.PassportDetailsActivity

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class FaceRecognizationViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityFacialRecognizeSetupBinding
    lateinit var mContext: Context
    var flag: Int? = 0

    fun setBinder(binder: ActivityFacialRecognizeSetupBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        binder.topbar.isBackShow = true
        binder.topbar.isTextShow = true
        binder.topbar.tvTitle.setText("Facial Recognition set-up")
        binder.topbar.topBarClickListener = SlideMenuClickListener()
        init()

    }

    fun init() {


    }

    inner class ViewClickHandler {
        fun onImageClick() {
            if (binder.tvBottom.text.contains("Look into the screen")) {
                binder.imgCenter.setImageResource(R.mipmap.face_up_down)
                binder.tvBottom.setText("Ttilt your head Up and Down")
            } else if (binder.tvBottom.text.contains("Ttilt your head Up and Down")) {
                binder.imgCenter.setImageResource(R.mipmap.face_left_right)
                binder.tvBottom.setText("Turn your head Left and Right")
            } else {
                val i = Intent(mContext, PassportDetailsActivity::class.java)
                i.putExtra("flag", "FaceRecognizeActivity")
                (mContext as Activity).startActivity(i)
                (mContext as Activity).finish()
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


