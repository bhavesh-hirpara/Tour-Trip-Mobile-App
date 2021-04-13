package com.tripkipedia.ui.login.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import com.tripkipedia.R
import com.tripkipedia.apputils.Utils
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.ActivityLoginBinding
import com.tripkipedia.interfaces.TopBarClickListener
import com.tripkipedia.ui.verify.view.VerifyActivity
/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class LoginViewModel(application: Application) : BaseViewModel(application) {
    private lateinit var binder: ActivityLoginBinding
    private lateinit var mContext: Context


    fun setBinder(binding: ActivityLoginBinding) {
        binder = binding
        mContext = binding.root.context
        binder.topbar.isBackShow = true
        binder.viewmodel = this
        binder.viewClickHandler = ViewClickHandler()
        this.binder.topbar.topBarClickListener = SlideMenuClickListener()
        init()

    }

    private fun init() {

    }

    inner class ViewClickHandler {
        fun onNext(view: View) {
            try {
                Utils.hideKeyBoard(mContext, view)
                val i = Intent(mContext, VerifyActivity::class.java)
                i.putExtra("flag", "login")
                (mContext as Activity).startActivity(i)

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