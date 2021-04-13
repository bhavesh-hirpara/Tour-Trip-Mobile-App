package com.tripkipedia.ui.reviews.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import com.tripkipedia.R
import com.tripkipedia.apputils.Constant
import com.tripkipedia.apputils.Utils
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.ActivityMyRewardsBinding
import com.tripkipedia.databinding.ActivityReviewsBinding
import com.tripkipedia.interfaces.CallbackListener
import com.tripkipedia.interfaces.TopBarClickListener
import com.tripkipedia.ui.freebiesDetails.datamodel.FreebiesDetailsData
import com.tripkipedia.ui.freebiesDetails.datamodel.FreebiesDetailsDataModel
import com.tripkipedia.ui.freebiesDetails.leaveAReviews.view.LeaveAReviewsActivity
import com.tripkipedia.ui.reviews.utils.ReviewsAdapter

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class ReviewsViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityReviewsBinding
    lateinit var mContext: Context
    private var dataReviews: MutableList<String> = ArrayList<String>()
    lateinit var reviewsAdapter: ReviewsAdapter
    lateinit var freebiesDetailsDataModel: FreebiesDetailsDataModel


    fun setBinder(binder: ActivityReviewsBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        binder.topbar.isBackShow = true
        binder.topbar.isTextShow = true
        binder.topbar.isHomeShow = true
        binder.topbar.isShareShow = true
        binder.topbar.tvTitle.text = "Black Ball"
        freebiesDetailsDataModel= FreebiesDetailsDataModel()
        this.binder.topbar.topBarClickListener = SlideMenuClickListener()
        initDashboard()
        init()

    }
    private fun initDashboard() {
        reviewsAdapter = ReviewsAdapter(mContext)
        binder.rvReviews.adapter = reviewsAdapter

        getHomeOutlets()

    }

    private fun getHomeOutlets() {
        isInternetAvailable(mContext, object : CallbackListener {
            override fun onSuccess() {
                showDialog("", (mContext as Activity))
                freebiesDetailsDataModel.item_id=(mContext as Activity).intent.getStringExtra("itemId")
                freebiesDetailsDataModel.getFreebiesDetailsData(mContext).observeForever { reviewData ->
                    dismissDialog()
                    if (reviewData.statusCode.equals(Constant.RESPONSE_FAILURE_CODE)) {


                    } else
                    {

                        reviewsAdapter.addAll(reviewData.data!!.reviews as ArrayList<FreebiesDetailsData.Data.Review>)
                    }

                }
            }

            override fun onCancel() {
                TODO("Not yet implemented")
            }

            override fun onRetry() {
                TODO("Not yet implemented")
            }
        })

    }
    fun init() {

    }

    inner class ViewClickHandler {
        fun onLeaveAReviewClick(v:View) {

            try {
                val i = Intent(mContext, LeaveAReviewsActivity::class.java)
                (mContext as Activity).startActivity(i)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    inner class SlideMenuClickListener : TopBarClickListener {
        override fun onTopBarClickListener(view: View?, value: String?) {
            Utils.hideKeyBoard(getContext(), view!!)
            if (value.equals(getLabelText(R.string.back))) {
                try {
                    (mContext as Activity).finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

}


