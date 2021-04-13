package com.tripkipedia.ui.myProfile.view

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
import com.tripkipedia.ui.myProfile.viewmodel.MyProfileViewModel
import com.tripkipedia.ui.myRewards.viewmodel.MyRewardsViewModel

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class MyProfileActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
        val binding: ActivityMyProfileBinding =
            DataBindingUtil.setContentView(activity, R.layout.activity_my_profile)
        val myProfileViewModel: MyProfileViewModel =
            ViewModelProvider(activity).get(MyProfileViewModel::class.java)
        myProfileViewModel.setBinder(binding)
    }

}
