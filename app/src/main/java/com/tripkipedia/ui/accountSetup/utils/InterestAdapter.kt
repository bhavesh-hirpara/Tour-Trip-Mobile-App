package com.drivo.ui.accountSetup.utils


import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tripkipedia.R
import com.tripkipedia.databinding.ItemInterestBinding
import com.tripkipedia.ui.accountSetup.datamodel.Interest_Data

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class InterestAdapter() : RecyclerView.Adapter<InterestAdapter.MyViewHolder>() {

    private var mContext: Context? = null
    val data: ArrayList<Interest_Data> = ArrayList<Interest_Data>()
    private lateinit var mEventListener: EventListener


    interface EventListener {
        fun onItemClick(pos: Int)
    }


    constructor(
        context: Context
    ) : this() {

        mContext = context
    }

    fun addAll(list: ArrayList<Interest_Data>) {
        data.clear()
        data.addAll(list)
        this.notifyDataSetChanged()
    }


    class MyViewHolder(internal var itemBinder: ItemInterestBinding) :
        RecyclerView.ViewHolder(itemBinder.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val v: ItemInterestBinding =
            DataBindingUtil.inflate<ItemInterestBinding>(
                inflater,
                R.layout.item_interest, parent, false

            )
        return InterestAdapter.MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        if (data.equals(null)) {
            return 0
        } else {
            return data.size

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBinder.tvTitle.setText(data.get(position).title)
        holder.itemBinder.imgIcon.setImageResource(data.get(position).img!!)
        holder.itemBinder.flMain.setOnClickListener {
            holder.itemBinder.imgBackground.setColorFilter(
                ContextCompat.getColor(
                    mContext!!,
                    R.color.l_purple
                ), android.graphics.PorterDuff.Mode.MULTIPLY
            )
            mEventListener.onItemClick(position)

        }


    }

    fun setEventListener(eventListener: EventListener) {
        mEventListener = eventListener
    }

}