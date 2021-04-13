package com.tripkipedia.ui.verify.viewmodel

import android.app.Activity
import android.app.Application
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Handler
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil

import com.tripkipedia.R
import com.tripkipedia.apputils.Utils
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.custom.PinView
import com.tripkipedia.databinding.ActivityVerifyBinding
import com.tripkipedia.databinding.DialogFaceRecognizationBinding
import com.tripkipedia.databinding.DialogSuccessBinding
import com.tripkipedia.interfaces.TopBarClickListener
import com.tripkipedia.ui.accountSetup.view.AccountSetupActivity
import com.tripkipedia.ui.verify.view.SuccessActivity
import com.tripkipedia.ui.verify.view.VerifyActivity

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class VerifyViewModel(application: Application) : BaseViewModel(application) {
    private lateinit var binder: ActivityVerifyBinding
    private lateinit var mContext: Context

    fun setBinder(binding: ActivityVerifyBinding) {
        binder = binding
        mContext = binding.root.context
        init()
        binder.topbar.isBackShow = true

    }

    private fun init() {
        binder.viewClickHandler = ViewClickHandler()
        binder.topbar.topBarClickListener = ViewBackClickListener()
    }

    inner class ViewClickHandler {
        fun onVerify(view: View) {
            try {
                if ((mContext as VerifyActivity).intent.getStringExtra("flag") == "login") {
                    Utils.hideKeyBoard(mContext, view)
                    val i = Intent(mContext, SuccessActivity::class.java)
                    mContext.startActivity(i)
                    (mContext as Activity).finish()
                } else {
                    showSuccessDialog(R.layout.dialog_success)

                }


            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }

    inner class ViewBackClickListener : TopBarClickListener {
        override fun onTopBarClickListener(view: View?, value: String?) {
            Utils.hideKeyBoard(getContext(), view!!)
            if (value.equals(getLabelText(R.string.back))) {
                (mContext as Activity).finish()
            }
        }
    }


    fun showSuccessDialog(view: Int) {
        val dialog: Dialog = Dialog(mContext)
        dialog.setContentView(view)

        val dialogWindow: Window? = dialog.window
        val lp: WindowManager.LayoutParams = dialogWindow!!.getAttributes()
        lp.x = 5 // The new position of the X coordinates
        lp.y = 5 // The new position of the Y coordinates
        lp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lp.width = getWidth()
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.dimAmount = 1f;
        dialog.getWindow()?.setAttributes(lp);

        try {
            dialog.show()
            val handler = Handler()
            val startApp: Runnable = object : Runnable {
                override fun run() {
                    handler.removeCallbacks(this)
                    dialog.dismiss()
                    (mContext as Activity).finish()
                }
            }

            fun startApplication(sleepTime: Long) {
                handler.postDelayed(startApp, sleepTime)
            }

            startApplication(2000)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun getWidth(): Int {
        val displayMetrics = DisplayMetrics()
        val windowmanager =
            this!!.mContext!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics)
        return displayMetrics.widthPixels;
    }

}