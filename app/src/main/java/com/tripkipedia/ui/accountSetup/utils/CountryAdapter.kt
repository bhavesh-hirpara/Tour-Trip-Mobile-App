package com.tripkipedia.ui.accountSetup.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tripkipedia.R
import com.tripkipedia.databinding.ItemCountrySelectionBinding
import com.tripkipedia.ui.accountSetup.datamodel.Contry_Data

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class CountryAdapter() : RecyclerView.Adapter<CountryAdapter.MyViewHolder>() {

    private var mContext: Context? = null
    private var data: MutableList<Contry_Data> = ArrayList<Contry_Data>()
    private var selectedPosition = 1


    constructor(
        context: Context
    ) : this() {

        mContext = context
    }

    fun addAll(list: ArrayList<Contry_Data>) {
        data.clear()
        data.addAll(list)
        this.notifyDataSetChanged()
    }

    class MyViewHolder(internal val itemBinder: ItemCountrySelectionBinding) :
        RecyclerView.ViewHolder(itemBinder.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val v: ItemCountrySelectionBinding =
            DataBindingUtil.inflate<ItemCountrySelectionBinding>(
                inflater,
                R.layout.item_country_selection, parent, false

            )
        return MyViewHolder(
            v
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBinder.radioBtn.setChecked(position == selectedPosition)
        holder.itemBinder.llDark.setTag(position)
        holder.itemBinder.radioBtn.setTag(position)
        holder.itemBinder.tvDark.setText(data.get(position).title.toString())
        if (position == 0 || position == 2) {
            holder.itemBinder.llDark.visibility = View.GONE
            holder.itemBinder.llPurple.visibility = View.VISIBLE
            if (position == 0) {
                holder.itemBinder.tvPurple.setText("Based on current location:")
            } else {
                holder.itemBinder.tvPurple.setText("All Countrties:")

            }
        } else {
            holder.itemBinder.llDark.visibility = View.VISIBLE
            holder.itemBinder.llPurple.visibility = View.GONE
        }
        holder.itemBinder.radioBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                itemCheckChanged(v)
            }
        })

        holder.itemBinder.llDark.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                itemCheckChanged(v)
            }
        })
    }


    //Return the selectedPosition item
    fun getSelectedItem(): String? {
        if (selectedPosition !== -1) {

            return data.get(selectedPosition).toString()
        }
        return ""
    }

    //On selecting any view set the current position to selectedPositon and notify adapter
    private fun itemCheckChanged(v: View) {
        selectedPosition = v.tag as Int
        notifyDataSetChanged()
    }

    //Delete the selected position from the arrayList
    fun deleteSelectedPosition() {
        if (selectedPosition !== -1) {
            data.removeAt(selectedPosition)
            selectedPosition = -1 //after removing selectedPosition set it back to -1
            notifyDataSetChanged()
        }
    }

}