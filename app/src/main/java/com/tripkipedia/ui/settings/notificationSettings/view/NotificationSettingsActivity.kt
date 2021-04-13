package com.tripkipedia.ui.settings.notificationSettings.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.tripkipedia.R
import com.tripkipedia.base.view.BaseActivity
import com.tripkipedia.databinding.ActivityAccountSettingsBinding
import com.tripkipedia.databinding.ActivityNotificationsSettingsBinding
import com.tripkipedia.databinding.ActivityPrivacyPolicyBinding
import com.tripkipedia.ui.settings.accountSettings.viewmodel.AccountSettingsViewModel
import com.tripkipedia.ui.settings.notificationSettings.viewmodel.NotificationSettingsViewModel
import com.tripkipedia.ui.settings.privacyPolicy.viewmodel.PrivacyPolicyViewModel

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class NotificationSettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
        val binding: ActivityNotificationsSettingsBinding =
            DataBindingUtil.setContentView(activity, R.layout.activity_notifications_settings)
        val notificationSettingsViewModel: NotificationSettingsViewModel =
            ViewModelProvider(activity).get(NotificationSettingsViewModel::class.java)
        notificationSettingsViewModel.setBinder(binding)
    }

}
