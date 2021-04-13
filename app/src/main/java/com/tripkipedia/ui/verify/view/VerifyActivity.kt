package com.tripkipedia.ui.verify.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.tripkipedia.R
import com.tripkipedia.base.view.BaseActivity
import com.tripkipedia.databinding.ActivityVerifyBinding
import com.tripkipedia.ui.verify.viewmodel.VerifyViewModel
/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class VerifyActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
        val binding: ActivityVerifyBinding =
            DataBindingUtil.setContentView(activity, R.layout.activity_verify)
        val loginViewModel: VerifyViewModel =  ViewModelProvider(activity).get(
            VerifyViewModel::class.java
        )
        loginViewModel.setBinder(binding)
    }
}
