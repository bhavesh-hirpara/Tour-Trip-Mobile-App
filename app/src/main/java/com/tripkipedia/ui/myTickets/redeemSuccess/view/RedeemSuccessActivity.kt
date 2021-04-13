package com.tripkipedia.ui.myTickets.redeemSuccess.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.tripkipedia.R
import com.tripkipedia.base.view.BaseActivity
import com.tripkipedia.databinding.ActivityTicketRedeemSuccessBinding
import com.tripkipedia.ui.myTickets.redeemSuccess.viewmodel.RedeemSuccessViewModel


/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class RedeemSuccessActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
        val binding: ActivityTicketRedeemSuccessBinding =
            DataBindingUtil.setContentView(activity, R.layout.activity_ticket_redeem_success)
        val redeemSuccessViewModel: RedeemSuccessViewModel =
            ViewModelProviders.of(activity).get(RedeemSuccessViewModel::class.java)
        redeemSuccessViewModel.setBinder(binding, supportFragmentManager)
    }

}
