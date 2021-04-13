package com.tripkipedia.ui.myRewards.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tripkipedia.R
import com.tripkipedia.databinding.ItemGetRideBinding
import com.tripkipedia.databinding.ItemInvitesBinding
import com.tripkipedia.databinding.ItemWayToEarnMoreRewardsBinding

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class MoreRewardsAdapter() : RecyclerView.Adapter<MoreRewardsAdapter.MyViewHolder>() {

    private var mContext: Context? = null
    private var data: MutableList<String> = ArrayList<String>()


    constructor(
        context: Context
    ) : this() {

        mContext = context
    }

    fun addAll(list: ArrayList<String>) {
        data.clear()
        data.addAll(list)
        this.notifyDataSetChanged()
    }

    fun getItem(pos: Int): String {
        return data[pos]
    }

    class MyViewHolder(internal var itemBinder: ItemWayToEarnMoreRewardsBinding) :
        RecyclerView.ViewHolder(itemBinder.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val v: ItemWayToEarnMoreRewardsBinding =
            DataBindingUtil.inflate<ItemWayToEarnMoreRewardsBinding>(
                inflater,
                R.layout.item_way_to_earn_more_rewards, parent, false

            )
        return MoreRewardsAdapter.MyViewHolder(v)
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