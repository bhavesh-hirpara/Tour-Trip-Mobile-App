package com.tripkipedia.ui.settings.accountSettings.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
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
import com.tripkipedia.apputils.Debug
import com.tripkipedia.apputils.Utils
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.*
import com.tripkipedia.interfaces.TopBarClickListener
import com.tripkipedia.ui.settings.utils.CurrencyAdapter
import com.tripkipedia.ui.verify.view.SuccessActivity
import com.tripkipedia.ui.verify.view.VerifyActivity
import java.util.ArrayList

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class AccountSettingsViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityAccountSettingsBinding
    lateinit var mContext: Context


    fun setBinder(binder: ActivityAccountSettingsBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        binder.topbar.isBackShow = true
        binder.topbar.isTextShow = true
        binder.topbar.tvTitle.setText("Accounts Settings")
        this.binder.topbar.topBarClickListener = SlideMenuClickListener()
        init()

    }

    fun init() {


    }

    inner class ViewClickHandler {
        fun onChangeNumberClick() {

            showChangeNumberDialog()
        }

    }

    var dialogChangeMobileNumberBinding: DialogChangeMobileNumberBinding? = null
    lateinit var dialogChangeNumber: BottomSheetDialog

    fun showChangeNumberDialog() {

        if (true) {
            val v: View = (mContext as Activity).getLayoutInflater()
                .inflate(R.layout.dialog_change_mobile_number, null)
            dialogChangeMobileNumberBinding = DataBindingUtil.bind(v)
            dialogChangeNumber = BottomSheetDialog(mContext as Activity, R.style.DialogStyle)
            dialogChangeNumber.setContentView(v)
            dialogChangeNumber.setCanceledOnTouchOutside(false)
            dialogChangeNumber.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.BLACK));
            dialogChangeNumber.getWindow()
                ?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

            dialogChangeMobileNumberBinding!!.btnNext.setOnClickListener {
                val i = Intent(mContext, VerifyActivity::class.java)
                i.putExtra("flag", "account_setting")
                mContext.startActivity(i)
                dialogChangeNumber.dismiss()
            }

            dialogChangeMobileNumberBinding!!.close.setOnClickListener {

                dialogChangeNumber.dismiss()


            }
            dialogChangeNumber.setOnShowListener { dialogInterface ->
                //                                setupFullHeight(v,dialogInterface)
                val bottomSheetBehavior = BottomSheetBehavior.from((v.getParent()) as View)
                val bottomSheetDialog = dialogInterface as BottomSheetDialog
                val bottomSheet = bottomSheetDialog.findViewById<View>(
                    com.google.android.material.R.id.design_bottom_sheet
                )
                bottomSheet?.setBackgroundColor(Color.TRANSPARENT)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

                //BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
            }

        }

        dialogChangeNumber.show()
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


