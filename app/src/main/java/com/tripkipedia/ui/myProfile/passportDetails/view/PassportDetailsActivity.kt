package com.tripkipedia.ui.myProfile.passportDetails.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.tripkipedia.R
import com.tripkipedia.base.view.BaseActivity
import com.tripkipedia.databinding.ActivityPassportDetailsBinding
import com.tripkipedia.ui.myProfile.passportDetails.viewmodel.PassportDetailsViewModel

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class PassportDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
        val binding: ActivityPassportDetailsBinding =
            DataBindingUtil.setContentView(activity, R.layout.activity_passport_details)
        val passportDetailsViewModel: PassportDetailsViewModel =
            ViewModelProvider(activity).get(PassportDetailsViewModel::class.java)
        passportDetailsViewModel.setBinder(binding)
    }

}
