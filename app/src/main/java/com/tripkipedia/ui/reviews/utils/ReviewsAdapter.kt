package com.tripkipedia.ui.reviews.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tripkipedia.R
import com.tripkipedia.databinding.ItemGetRideBinding
import com.tripkipedia.databinding.ItemInvitesBinding
import com.tripkipedia.databinding.ItemReviewsBinding
import com.tripkipedia.databinding.ItemWayToEarnMoreRewardsBinding
import com.tripkipedia.ui.freebiesDetails.datamodel.FreebiesDetailsData

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class ReviewsAdapter() : RecyclerView.Adapter<ReviewsAdapter.MyViewHolder>() {

    private var mContext: Context? = null
    private var data: MutableList<FreebiesDetailsData.Data.Review> = ArrayList<FreebiesDetailsData.Data.Review>()


    constructor(
        context: Context
    ) : this() {

        mContext = context
    }

    fun addAll(list: ArrayList<FreebiesDetailsData.Data.Review>) {
        data.clear()
        data.addAll(list)
        this.notifyDataSetChanged()
    }



    class MyViewHolder(internal var itemBinder: ItemReviewsBinding) :
        RecyclerView.ViewHolder(itemBinder.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val v: ItemReviewsBinding =
            DataBindingUtil.inflate<ItemReviewsBinding>(
                inflater,
                R.layout.item_reviews, parent, false

            )
        return ReviewsAdapter.MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        if (data.equals(null)) {
            return 0
        } else {
            return data.size

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }


}