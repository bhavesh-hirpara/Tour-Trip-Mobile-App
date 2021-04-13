package com.tripkipedia.ui.freebiesDetails.leaveAReviews.view

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
import com.tripkipedia.databinding.ActivityLeaveAReviewBinding
import com.tripkipedia.ui.freebiesDetails.leaveAReviews.viewmodel.LeaveAReviewsViewModel
import com.tripkipedia.ui.freebiesDetails.viewmodel.FreebiesDetailsViewModel

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class LeaveAReviewsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
        val binding: ActivityLeaveAReviewBinding =
            DataBindingUtil.setContentView(activity, R.layout.activity_leave_a_review)
        val leaveAReviewsViewModel: LeaveAReviewsViewModel =
            ViewModelProvider(activity).get(LeaveAReviewsViewModel::class.java)
        leaveAReviewsViewModel.setBinder(binding, supportFragmentManager)
    }

}
