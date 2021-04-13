package com.tripkipedia.ui.myProfile.utils


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tripkipedia.R
import com.tripkipedia.databinding.ItemWalletBinding
import com.tripkipedia.ui.myProfile.datamodel.WalletData

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class WalletAdapter() : RecyclerView.Adapter<WalletAdapter.MyViewHolder>() {

    private var mContext: Context? = null
    val data: ArrayList<WalletData> = ArrayList<WalletData>()
    private lateinit var mEventListener: EventListener


    interface EventListener {
        fun onItemClick(pos: Int)
    }


    constructor(
        context: Context
    ) : this() {

        mContext = context
    }

    fun addAll(list: ArrayList<WalletData>) {
        data.clear()
        data.addAll(list)
        this.notifyDataSetChanged()
    }


    class MyViewHolder(internal var itemBinder: ItemWalletBinding) :
        RecyclerView.ViewHolder(itemBinder.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val v: ItemWalletBinding =
            DataBindingUtil.inflate<ItemWalletBinding>(
                inflater,
                R.layout.item_wallet, parent, false

            )
        return WalletAdapter.MyViewHolder(v)
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
        holder.itemBinder.imgCard.setImageResource(data.get(position).img!!)
    }

    fun setEventListener(eventListener: EventListener) {
        mEventListener = eventListener
    }

}