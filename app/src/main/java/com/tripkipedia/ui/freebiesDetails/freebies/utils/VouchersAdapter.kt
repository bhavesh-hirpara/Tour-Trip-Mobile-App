package com.tripkipedia.ui.freebiesDetails.freebies.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tripkipedia.R
import com.tripkipedia.databinding.ItemVoucherBinding

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class VouchersAdapter() : RecyclerView.Adapter<VouchersAdapter.MyViewHolder>() {

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

    class MyViewHolder(internal var itemBinder: ItemVoucherBinding) :
            RecyclerView.ViewHolder(itemBinder.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val v: ItemVoucherBinding =
                DataBindingUtil.inflate<ItemVoucherBinding>(
                        inflater,
                        R.layout.item_voucher, parent, false

                )
        return VouchersAdapter.MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return 6
        //return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


    }


}