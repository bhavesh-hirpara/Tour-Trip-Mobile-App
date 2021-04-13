package com.tripkipedia.ui.login.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.tripkipedia.R
import com.tripkipedia.base.view.BaseActivity
import com.tripkipedia.databinding.ActivityLoginBinding
import com.tripkipedia.ui.login.viewmodel.LoginViewModel
import com.tripkipedia.ui.onboarding.viewmodel.OnBoardingViewModel
/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(activity, R.layout.activity_login)
        val loginViewModel: LoginViewModel = ViewModelProvider(activity).get(LoginViewModel::class.java)
        loginViewModel.setBinder(binding)
    }
}
