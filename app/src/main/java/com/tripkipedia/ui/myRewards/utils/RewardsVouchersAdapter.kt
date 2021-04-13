package com.tripkipedia.ui.myRewards.details.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tripkipedia.R
import com.tripkipedia.databinding.ItemGetRideBinding
import com.tripkipedia.databinding.ItemInvitesBinding
import com.tripkipedia.databinding.ItemMyRewardsBinding
import com.tripkipedia.ui.myRewards.utils.RewardAdapter

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class RewardsVouchersAdapter() : RecyclerView.Adapter<RewardsVouchersAdapter.MyViewHolder>() {

    private var mContext: Context? = null
    private var data: MutableList<String> = ArrayList<String>()
    private var dataReward: MutableList<String> = ArrayList<String>()
    lateinit var rewardAdapter: RewardAdapter


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

    class MyViewHolder(internal var itemBinder: ItemMyRewardsBinding) :
        RecyclerView.ViewHolder(itemBinder.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val v: ItemMyRewardsBinding =
            DataBindingUtil.inflate<ItemMyRewardsBinding>(
                inflater,
                R.layout.item_my_rewards, parent, false

            )
        return RewardsVouchersAdapter.MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        if (data.equals(null)) {
            return 0
        } else {
            return data.size

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBinder.cvReward.setOnClickListener {
            if (holder.itemBinder.rvVouchers.visibility == View.VISIBLE) {
                holder.itemBinder.imgDropdown.rotation = 0F
                holder.itemBinder.rvVouchers.visibility = View.GONE
            } else {
                holder.itemBinder.imgDropdown.rotation = 180F
                holder.itemBinder.rvVouchers.visibility = View.VISIBLE
            }
        }


        if (position == 0) {
            holder.itemBinder.imgLogo.setImageResource(R.mipmap.bounce_logo)
            holder.itemBinder.tvTitle.setText("Bounce")
            holder.itemBinder.tvSecond.setText("3")

            dataReward.clear()
            for (i: Int in 0..2) {
                dataReward.add("l" + i)
            }
            rewardAdapter = RewardAdapter(mContext!!)
            rewardAdapter.addAll(dataReward as ArrayList<String>)
            holder.itemBinder.rvVouchers.adapter = rewardAdapter

        }
        if (position == 1) {
            holder.itemBinder.imgLogo.setImageResource(R.mipmap.burger_king_logo)
            holder.itemBinder.tvTitle.setText("Burger King")
            holder.itemBinder.tvSecond.setText("5")

            dataReward.clear()
            for (i: Int in 0..4) {
                dataReward.add("l" + i)
            }
            rewardAdapter = RewardAdapter(mContext!!)
            rewardAdapter.addAll(dataReward as ArrayList<String>)
            holder.itemBinder.rvVouchers.adapter = rewardAdapter

        }
        if (position == 2) {
            holder.itemBinder.imgLogo.setImageResource(R.mipmap.starbucks_logo)
            holder.itemBinder.tvTitle.setText("Starbucks")
            holder.itemBinder.tvSecond.setText("10")

            dataReward.clear()
            for (i: Int in 0..9) {
                dataReward.add("l" + i)
            }
            rewardAdapter = RewardAdapter(mContext!!)
            rewardAdapter.addAll(dataReward as ArrayList<String>)
            holder.itemBinder.rvVouchers.adapter = rewardAdapter

        }
        if (position == 3) {
            holder.itemBinder.imgLogo.setImageResource(R.mipmap.black_ball_logo)
            holder.itemBinder.tvTitle.setText("Black Ball")
            holder.itemBinder.tvSecond.setText("8")

            dataReward.clear()
            for (i: Int in 0..7) {
                dataReward.add("l" + i)
            }
            rewardAdapter = RewardAdapter(mContext!!)
            rewardAdapter.addAll(dataReward as ArrayList<String>)
            holder.itemBinder.rvVouchers.adapter = rewardAdapter

        }
        if (position == 4) {
            holder.itemBinder.imgLogo.setImageResource(R.mipmap.t_voucher_logo)
            holder.itemBinder.tvTitle.setText("T-Voucher")
            holder.itemBinder.tvSecond.setText("4")

            dataReward.clear()
            for (i: Int in 0..3) {
                dataReward.add("l" + i)
            }
            rewardAdapter = RewardAdapter(mContext!!)
            rewardAdapter.addAll(dataReward as ArrayList<String>)
            holder.itemBinder.rvVouchers.adapter = rewardAdapter

        }
    }


}