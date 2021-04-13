package com.tripkipedia.ui.freebiesDetails.freebies.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tripkipedia.R
import com.tripkipedia.databinding.ItemFreebiesGiftBinding

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class GiftsAdapter() : RecyclerView.Adapter<GiftsAdapter.MyViewHolder>() {

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

    class MyViewHolder(internal var itemBinder: ItemFreebiesGiftBinding) :
            RecyclerView.ViewHolder(itemBinder.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val v: ItemFreebiesGiftBinding =
                DataBindingUtil.inflate<ItemFreebiesGiftBinding>(
                        inflater,
                        R.layout.item_freebies_gift, parent, false

                )
        return GiftsAdapter.MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return 10
        //return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {



    }


}