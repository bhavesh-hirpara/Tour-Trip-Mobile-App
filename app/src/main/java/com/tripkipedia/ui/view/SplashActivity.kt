package com.tripkipedia.ui.view

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.tripkipedia.R
import com.tripkipedia.apputils.Utils
import com.tripkipedia.base.view.BaseActivity
import com.tripkipedia.databinding.ActivitySplashBinding
import com.tripkipedia.ui.dashboard.view.DashboardActivity
import com.tripkipedia.ui.onboarding.view.OnBoardingActivity

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class SplashActivity : BaseActivity() {
    internal var handler = Handler()
    internal lateinit var binding: ActivitySplashBinding

    internal var startApp: Runnable = object : Runnable {
        override fun run() {
            handler.removeCallbacks(this)

            val i = Intent(activity, OnBoardingActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        Utils.getHashKey()

        checkPermission(this, permissionsListener)
    }


    fun startApplication(sleepTime: Long) {
        handler.postDelayed(startApp, sleepTime)
    }

    private val permissionsListener = object : PermissionListener {
        override fun onGranted() {
//            val i = Intent(mContext, CheckEligibilityActivity::class.java)
//            i.putExtra("from", Constant.FROM_WELCOME_SCREEN)
//            mContext.startActivity(i)
            startApplication(1000)
        }

        override fun onDenied() {
            showPermissionAlert()
        }
    }

    public override fun onDestroy() {
        try {
            handler.removeCallbacks(startApp)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onDestroy()
    }

}
