package com.tripkipedia.ui.settings.termsAndConditions.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tripkipedia.R
import com.tripkipedia.apputils.Utils
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.*
import com.tripkipedia.interfaces.TopBarClickListener

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class TermsAndConditionsViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityTermsAndConditionBinding
    lateinit var mContext: Context


    fun setBinder(binder: ActivityTermsAndConditionBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        binder.topbar.isBackShow = true
        binder.topbar.isTextShow = true
        binder.topbar.tvTitle.setText("Terms & Conditions")
        init()
        this.binder.topbar.topBarClickListener = SlideMenuClickListener()

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


