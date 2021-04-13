package com.tripkipedia.ui.choose_interest.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.tripkipedia.R
import com.tripkipedia.base.view.BaseActivity
import com.tripkipedia.databinding.ActivityChooseInterestBinding
import com.tripkipedia.ui.choose_interest.viewmodel.ChooseInterestViewModel
import com.tripkipedia.ui.login.viewmodel.LoginViewModel
/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class ChooseInterestActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
        val binding: ActivityChooseInterestBinding = DataBindingUtil.setContentView(activity, R.layout.activity_choose_interest)
        val loginViewModel: ChooseInterestViewModel = ViewModelProvider(activity).get(ChooseInterestViewModel::class.java)
        loginViewModel.setBinder(binding)
    }
}
