package com.tripkipedia.ui.myTickets.past.utils

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tripkipedia.R
import com.tripkipedia.databinding.ItemPastBinding
import com.tripkipedia.ui.freebiesDetails.leaveAReviews.view.LeaveAReviewsActivity

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class PastAdapter() : RecyclerView.Adapter<PastAdapter.MyViewHolder>() {

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

    class MyViewHolder(internal var itemBinder: ItemPastBinding) :
        RecyclerView.ViewHolder(itemBinder.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val v: ItemPastBinding =
            DataBindingUtil.inflate<ItemPastBinding>(
                inflater,
                R.layout.item_past, parent, false

            )
        return PastAdapter.MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        if (data.equals(null)) {
            return 0
        } else {
            return data.size

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBinder.btnLeaveReview.setOnClickListener {
            val i = Intent(mContext, LeaveAReviewsActivity::class.java)
            mContext?.startActivity(i)
        }

    }


}