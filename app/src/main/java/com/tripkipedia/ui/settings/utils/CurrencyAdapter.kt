package com.tripkipedia.ui.settings.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tripkipedia.R
import com.tripkipedia.databinding.ItemCountrySelectionBinding
import com.tripkipedia.databinding.ItemCurrencySelectionBinding
import com.tripkipedia.ui.settings.datamodel.CurrencyData

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class CurrencyAdapter() : RecyclerView.Adapter<CurrencyAdapter.MyViewHolder>() {

    private var mContext: Context? = null
    private var data: MutableList<CurrencyData> = ArrayList<CurrencyData>()
    private var selectedPosition = 1


    constructor(
        context: Context
    ) : this() {

        mContext = context
    }

    fun addAll(list: ArrayList<CurrencyData>) {
        data.clear()
        data.addAll(list)
        this.notifyDataSetChanged()
    }

    class MyViewHolder(internal val itemBinder: ItemCurrencySelectionBinding) :
        RecyclerView.ViewHolder(itemBinder.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val v: ItemCurrencySelectionBinding =
            DataBindingUtil.inflate<ItemCurrencySelectionBinding>(
                inflater,
                R.layout.item_currency_selection, parent, false

            )
        return CurrencyAdapter.MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBinder.radioBtn.setChecked(position == selectedPosition)
        holder.itemBinder.radioBtn.setTag(position)
        holder.itemBinder.tvDark.setText(data.get(position).title.toString())



        holder.itemBinder.radioBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                itemCheckChanged(v)
            }
        })
    }


    //Return the selectedPosition item
    fun getSelectedItem(): String? {
        if (selectedPosition !== -1) {
            Toast.makeText(
                mContext,
                "Selected Item : " + data.get(selectedPosition),
                Toast.LENGTH_SHORT
            ).show()
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