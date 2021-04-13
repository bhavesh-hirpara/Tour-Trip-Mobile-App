package com.tripkipedia.ui.freebiesDetails.details.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.text.Html
import com.tripkipedia.R
import com.tripkipedia.apputils.Constant
import com.tripkipedia.apputils.Utils
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.FragmentDetailsBinding
import com.tripkipedia.interfaces.CallbackListener
import com.tripkipedia.ui.freebiesDetails.datamodel.FreebiesDetailsDataModel
import com.tripkipedia.ui.freebiesDetails.details.utils.GetRideAdapter
import com.tripkipedia.ui.freebiesDetails.details.utils.RewardAdapter
import com.tripkipedia.ui.freebiesDetails.details.utils.SuggestedAdapter
import com.tripkipedia.ui.freebiesDetails.leaveAReviews.view.LeaveAReviewsActivity
import com.tripkipedia.ui.reviews.view.ReviewsActivity


class DetailsViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: FragmentDetailsBinding
    lateinit var mContext: Context
    lateinit var freebiesDetailsDataModel: FreebiesDetailsDataModel
    private var dataGetRide: MutableList<String> = ArrayList<String>()
    private var dataReward: MutableList<String> = ArrayList<String>()
    private var datasuggested: MutableList<String> = ArrayList<String>()
    lateinit var itemId: String
    lateinit var getRideAdapter: GetRideAdapter
    lateinit var rewardAdapter: RewardAdapter
    lateinit var suggestedAdapter: SuggestedAdapter


    fun setBinder(
        binder: FragmentDetailsBinding,
        itemId: String?
    ) {
        this.binder = binder
        this.mContext = binder.root.context
        this.itemId = itemId.toString()
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        freebiesDetailsDataModel = FreebiesDetailsDataModel()
        getItems()
        init()

    }

    private fun getItems() {
        isInternetAvailable(mContext, object : CallbackListener {
            override fun onSuccess() {
                freebiesDetailsDataModel.item_id = itemId
                freebiesDetailsDataModel.getFreebiesDetailsData(mContext)
                    .observeForever { itemData ->


                        if (itemData.statusCode.equals(Constant.RESPONSE_FAILURE_CODE)) {


                        } else {
                            binder.txtRI.text = itemData.data!!.reviews!!.size.toString()
                            binder.txtRIB.text = itemData.data!!.reviews!!.size.toString()
                            binder.txtDescription.text = Html.fromHtml(itemData.data!!.description)
                            binder.txtWebsite.text = itemData.data!!.website
                            binder.txtNumber.text = itemData.data!!.mobile

                        }

                    }
            }

            override fun onCancel() {

            }

            override fun onRetry() {

                getItems()
            }

        })


    }

    fun init() {



        getRideAdapter = GetRideAdapter(mContext)
        binder.rvGetRide.adapter = getRideAdapter

        rewardAdapter = RewardAdapter(mContext)
        binder.rvGetReward.adapter = rewardAdapter

        suggestedAdapter = SuggestedAdapter(mContext)
        binder.rvSuggested.adapter = suggestedAdapter


    }

    inner class ViewClickHandler {
        fun onExtendClick() {


        }

        fun onLeaveReviewClick() {
            try {
                val i = Intent(mContext, LeaveAReviewsActivity::class.java)
                (mContext as Activity).startActivity(i)

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        fun onSeeMoreReviewClick() {
            try {
                val i = Intent(mContext, ReviewsActivity::class.java)
                i.putExtra("itemId", itemId)
                (mContext as Activity).startActivity(i)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}


