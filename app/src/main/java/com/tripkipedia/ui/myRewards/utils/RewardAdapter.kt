package com.tripkipedia.ui.myRewards.utils

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tripkipedia.R
import com.tripkipedia.databinding.ItemFeaturedBinding
import com.tripkipedia.databinding.ItemGetRideBinding
import com.tripkipedia.databinding.ItemHomeBinding
import com.tripkipedia.databinding.ItemRewardsBinding
import com.tripkipedia.ui.freebiesDetails.view.FreebiesDetailsActivity
import com.tripkipedia.ui.voucherDetails.view.VoucherDetailsActivity

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class RewardAdapter() : RecyclerView.Adapter<RewardAdapter.MyViewHolder>() {

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

    class MyViewHolder(internal var itemBinder: ItemRewardsBinding) :
        RecyclerView.ViewHolder(itemBinder.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val v: ItemRewardsBinding =
            DataBindingUtil.inflate<ItemRewardsBinding>(
                inflater,
                R.layout.item_rewards, parent, false

            )
        return RewardAdapter.MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        if (data.equals(null)) {
            return 0
        } else {
            return data.size

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBinder.useNow.setOnClickListener {
            val i = Intent(mContext, VoucherDetailsActivity::class.java)
            mContext?.startActivity(i)
        }

    }


}