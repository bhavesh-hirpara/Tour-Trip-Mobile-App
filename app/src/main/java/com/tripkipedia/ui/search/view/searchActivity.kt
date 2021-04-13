package com.tripkipedia.ui.search.view

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
import com.tripkipedia.ui.myRewards.viewmodel.MyRewardsViewModel
import com.tripkipedia.ui.reviews.viewmodel.ReviewsViewModel
import com.tripkipedia.ui.search.viewmodel.SearchViewModel
import com.tripkipedia.ui.tCoinsHistory.viewmodel.TCoinHistoryViewModel
import com.tripkipedia.ui.voucherDetails.viewmodel.VoucherDetailsViewModel

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class searchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
        val binding: ActivitySearchBinding =
            DataBindingUtil.setContentView(activity, R.layout.activity_search)
        val searchViewModel: SearchViewModel =
            ViewModelProvider(activity).get(SearchViewModel::class.java)
        searchViewModel.setBinder(binding)
    }

}
