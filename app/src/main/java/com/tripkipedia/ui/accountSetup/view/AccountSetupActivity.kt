package com.tripkipedia.ui.accountSetup.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.tripkipedia.R
import com.tripkipedia.base.view.BaseActivity
import com.tripkipedia.databinding.ActivityAccountSetupBinding
import com.tripkipedia.databinding.ActivityOnboardingBinding
import com.tripkipedia.ui.accountSetup.viewmodel.AccountSetupViewModel
import com.tripkipedia.ui.onboarding.viewmodel.OnBoardingViewModel

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class AccountSetupActivity : BaseActivity() {
    lateinit var accountSetupViewModel: AccountSetupViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
        val binding: ActivityAccountSetupBinding =
            DataBindingUtil.setContentView(activity, R.layout.activity_account_setup)
        accountSetupViewModel =
            ViewModelProviders.of(activity).get(AccountSetupViewModel::class.java)
        accountSetupViewModel.setBinder(binding)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

            accountSetupViewModel!!.onActivityResult(requestCode, resultCode, data)

    }
}
