package com.tripkipedia.ui.myTickets.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.tripkipedia.R
import com.tripkipedia.base.view.BaseActivity
import com.tripkipedia.databinding.ActivityMyTicketsBinding
import com.tripkipedia.databinding.ActivityNotificationBinding
import com.tripkipedia.ui.myTickets.viewmodel.MyTicketsViewModel
import com.tripkipedia.ui.notifications.viewmodel.NotificationsViewModel

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class MyTicketsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
        val binding: ActivityMyTicketsBinding =
            DataBindingUtil.setContentView(activity, R.layout.activity_my_tickets)
        val myTicketsViewModel: MyTicketsViewModel =
            ViewModelProviders.of(activity).get(MyTicketsViewModel::class.java)
        myTicketsViewModel.setBinder(binding, supportFragmentManager)
    }

}
