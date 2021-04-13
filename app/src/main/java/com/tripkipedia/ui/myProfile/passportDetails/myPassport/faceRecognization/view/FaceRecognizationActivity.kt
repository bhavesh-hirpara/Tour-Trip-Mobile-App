package com.tripkipedia.ui.myProfile.passportDetails.myPassport.faceRecognization.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.tripkipedia.R
import com.tripkipedia.base.view.BaseActivity
import com.tripkipedia.databinding.*
import com.tripkipedia.ui.myProfile.passportDetails.myPassport.faceRecognization.viewmodel.FaceRecognizationViewModel

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class FaceRecognizationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
        val binding: ActivityFacialRecognizeSetupBinding =
            DataBindingUtil.setContentView(activity, R.layout.activity_facial_recognize_setup)
        val faceRecognizationViewModel: FaceRecognizationViewModel =
            ViewModelProvider(activity).get(FaceRecognizationViewModel::class.java)
        faceRecognizationViewModel.setBinder(binding)
    }

}
