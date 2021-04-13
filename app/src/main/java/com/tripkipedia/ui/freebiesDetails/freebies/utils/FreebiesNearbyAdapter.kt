package com.tripkipedia.ui.freebiesDetails.freebies.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tripkipedia.R
import com.tripkipedia.apputils.Utils
import com.tripkipedia.databinding.ItemNeaybyFreebiesBinding
import com.tripkipedia.ui.dashboard.datamodel.ItemsData

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class FreebiesNearbyAdapter() : RecyclerView.Adapter<FreebiesNearbyAdapter.MyViewHolder>() {

    private var mContext: Context? = null
    private var data: MutableList<ItemsData.Datum> = ArrayList<ItemsData.Datum>()


    constructor(
        context: Context
    ) : this() {

        mContext = context
    }

    fun addAll(list: ArrayList<ItemsData.Datum>) {
        data.clear()
        data.addAll(list)
        this.notifyDataSetChanged()
    }

    fun getItem(pos: Int): ItemsData.Datum {
        return data[pos]
    }

    class MyViewHolder(internal var itemBinder: ItemNeaybyFreebiesBinding) :
        RecyclerView.ViewHolder(itemBinder.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val v: ItemNeaybyFreebiesBinding =
            DataBindingUtil.inflate<ItemNeaybyFreebiesBinding>(
                inflater,
                R.layout.item_neayby_freebies, parent, false

            )
        return FreebiesNearbyAdapter.MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Utils.loadImage(holder.itemBinder.imgMain, data[position].photo!!, mContext!!, R.mipmap.item_nearby_image)
        holder.itemBinder.txtTitle.text= data[position].name
        holder.itemBinder.txtType.text= data[position].type
        holder.itemBinder.txtCategory.text= data[position].category
        holder.itemBinder.txtReview.text="" + data[position].reviews + " Reviews"



    }


}