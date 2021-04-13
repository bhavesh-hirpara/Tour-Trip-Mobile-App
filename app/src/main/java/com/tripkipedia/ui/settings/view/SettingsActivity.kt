package com.tripkipedia.ui.settings.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.tripkipedia.R
import com.tripkipedia.base.view.BaseActivity
import com.tripkipedia.databinding.ActivityMyProfileBinding
import com.tripkipedia.databinding.ActivitySettingsBinding
import com.tripkipedia.ui.myProfile.viewmodel.MyProfileViewModel
import com.tripkipedia.ui.myRewards.viewmodel.MyRewardsViewModel
import com.tripkipedia.ui.settings.viewmodel.SettingsViewModel

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
        val binding: ActivitySettingsBinding =
            DataBindingUtil.setContentView(activity, R.layout.activity_settings)
        val settingsViewModel: SettingsViewModel =
            ViewModelProvider(activity).get(SettingsViewModel::class.java)
        settingsViewModel.setBinder(binding)
    }

}
