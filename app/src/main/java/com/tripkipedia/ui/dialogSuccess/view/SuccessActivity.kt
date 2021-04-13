package com.tripkipedia.ui.verify.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.tripkipedia.R
import com.tripkipedia.base.view.BaseActivity
import com.tripkipedia.ui.accountSetup.view.AccountSetupActivity
import com.tripkipedia.ui.verify.viewmodel.VerifyViewModel
/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class SuccessActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_success)
        val handler = Handler()
        val startApp: Runnable = object : Runnable {
            override fun run() {
                handler.removeCallbacks(this)
                val i = Intent(activity, AccountSetupActivity::class.java)
                activity.startActivity(i)
                (activity as Activity).finish()
            }
        }

        fun startApplication(sleepTime: Long) {
            handler.postDelayed(startApp, sleepTime)
        }

        startApplication(2000)


    }
}
