package com.tripkipedia.ui.myProfile.utils


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tripkipedia.R
import com.tripkipedia.databinding.ItemCreditCardBinding
import com.tripkipedia.ui.myProfile.datamodel.CreditCardData

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class CreditCardAdapter() : RecyclerView.Adapter<CreditCardAdapter.MyViewHolder>() {

    private var mContext: Context? = null
    val data: ArrayList<CreditCardData> = ArrayList<CreditCardData>()
    private lateinit var mEventListener: EventListener


    interface EventListener {
        fun onItemClick(pos: Int)
    }


    constructor(
        context: Context
    ) : this() {

        mContext = context
    }

    fun addAll(list: ArrayList<CreditCardData>) {
        data.clear()
        data.addAll(list)
        this.notifyDataSetChanged()
    }


    class MyViewHolder(internal var itemBinder: ItemCreditCardBinding) :
        RecyclerView.ViewHolder(itemBinder.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val v: ItemCreditCardBinding =
            DataBindingUtil.inflate<ItemCreditCardBinding>(
                inflater,
                R.layout.item_credit_card, parent, false

            )
        return CreditCardAdapter.MyViewHolder(v)
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