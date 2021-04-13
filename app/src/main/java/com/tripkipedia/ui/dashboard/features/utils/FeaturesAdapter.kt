package com.tripkipedia.ui.dashboard.features.utils

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tripkipedia.R
import com.tripkipedia.apputils.Utils
import com.tripkipedia.databinding.ItemFeaturedBinding
import com.tripkipedia.ui.dashboard.datamodel.ItemsData
import com.tripkipedia.ui.dashboard.home.utils.SlidingImageAdapter
import com.tripkipedia.ui.freebiesDetails.view.FreebiesDetailsActivity
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class FeaturesAdapter() : RecyclerView.Adapter<FeaturesAdapter.MyViewHolder>() {

    private var mContext: Context? = null
    private var data: MutableList<ItemsData.Datum> = ArrayList<ItemsData.Datum>()
    private lateinit var mEventListener: EventListener

    constructor(
        context: Context
    ) : this() {

        mContext = context
    }

    interface EventListener {
        fun onItemViewClicked(position: Int, itemId: String?)
    }

    fun addAll(list: ArrayList<ItemsData.Datum>) {
        data.clear()
        data.addAll(list)
        this.notifyDataSetChanged()
    }

    fun setEventListener(eventListener: EventListener) {
        mEventListener = eventListener
    }

    class MyViewHolder(internal var itemBinder: ItemFeaturedBinding) :
        RecyclerView.ViewHolder(itemBinder.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val v: ItemFeaturedBinding =
            DataBindingUtil.inflate<ItemFeaturedBinding>(
                inflater,
                R.layout.item_featured, parent, false

            )
        return FeaturesAdapter.MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        if (data.equals(null)) {
            return 0
        } else {
            return data.size

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBinder.llItem.setOnClickListener {
            mEventListener.onItemViewClicked(position, data.get(position).itemId)
        }
        Utils.loadImage(
            holder.itemBinder.imgMain,
            data[position].photo!!,
            mContext!!,
            R.mipmap.image
        )
        holder.itemBinder.txtTitle.text = data[position].name
        holder.itemBinder.txtType.text = data[position].type
        holder.itemBinder.txtCategory.text = data[position].category
        holder.itemBinder.txtReview.text = "" + data.get(position).reviews + " Reviews"
    }


}