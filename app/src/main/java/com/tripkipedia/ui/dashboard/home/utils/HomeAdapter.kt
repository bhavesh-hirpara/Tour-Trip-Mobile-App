package com.tripkipedia.ui.dashboard.home.utils

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tripkipedia.R
import com.tripkipedia.databinding.ItemFeaturedBinding
import com.tripkipedia.databinding.ItemHomeBinding
import com.tripkipedia.ui.freebiesDetails.view.FreebiesDetailsActivity

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class HomeAdapter() : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

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

    class MyViewHolder(internal var itemBinder: ItemHomeBinding) :
        RecyclerView.ViewHolder(itemBinder.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val v: ItemHomeBinding =
            DataBindingUtil.inflate<ItemHomeBinding>(
                inflater,
                R.layout.item_home, parent, false

            )
        return HomeAdapter.MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        if (data.equals(null)) {
            return 0
        } else {
            return data.size

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBinder.flMain.setOnClickListener {
            val i = Intent(mContext, FreebiesDetailsActivity::class.java)
            mContext?.startActivity(i)
        }

    }


}