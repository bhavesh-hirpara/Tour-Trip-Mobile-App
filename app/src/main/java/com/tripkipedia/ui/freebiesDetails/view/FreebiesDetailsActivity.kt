package com.tripkipedia.ui.freebiesDetails.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.tripkipedia.R
import com.tripkipedia.base.view.BaseActivity
import com.tripkipedia.databinding.ActivityFreebiesDetailsBinding
import com.tripkipedia.ui.freebiesDetails.viewmodel.FreebiesDetailsViewModel

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class FreebiesDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
        val binding: ActivityFreebiesDetailsBinding =
                DataBindingUtil.setContentView(activity, R.layout.activity_freebies_details)
        val freebiesDetailsViewModel: FreebiesDetailsViewModel =
            ViewModelProvider(activity).get(FreebiesDetailsViewModel::class.java)
        freebiesDetailsViewModel.setBinder(binding, supportFragmentManager)
    }

}
