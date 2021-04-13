package com.tripkipedia.ui.dashboard.view

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.tripkipedia.R
import com.tripkipedia.base.view.BaseActivity
import com.tripkipedia.databinding.ActivityHomePageBinding
import com.tripkipedia.databinding.ActivityOnboardingBinding
import com.tripkipedia.ui.dashboard.viewmodel.DashboardViewModel
import com.tripkipedia.ui.onboarding.viewmodel.OnBoardingViewModel

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class DashboardActivity : BaseActivity() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
        val binding: ActivityHomePageBinding =
            DataBindingUtil.setContentView(activity, R.layout.activity_home_page)
        dashboardViewModel = ViewModelProvider(activity).get(DashboardViewModel::class.java)
        dashboardViewModel.setBinder(binding, supportFragmentManager)
    }
}
