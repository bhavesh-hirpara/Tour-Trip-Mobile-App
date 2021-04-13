package com.tripkipedia.ui.myTickets.current.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tripkipedia.R
import com.tripkipedia.databinding.*
import com.tripkipedia.ui.dashboard.view.DashboardActivity
import com.tripkipedia.ui.myTickets.redeemSuccess.view.RedeemSuccessActivity

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class CurrentAdapter() : RecyclerView.Adapter<CurrentAdapter.MyViewHolder>() {

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

    class MyViewHolder(internal var itemBinder: ItemCurrentBinding) :
        RecyclerView.ViewHolder(itemBinder.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val v: ItemCurrentBinding =
            DataBindingUtil.inflate<ItemCurrentBinding>(
                inflater,
                R.layout.item_current, parent, false

            )
        return CurrentAdapter.MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        if (data.equals(null)) {
            return 0
        } else {
            return data.size

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBinder.llSecond.setOnClickListener {
            showRedeemDialog()
        }

    }

    var dialogCurrentRedeemBinding: DialogCurrentRedeemBinding? = null
    lateinit var dialogRedeem: BottomSheetDialog

    fun showRedeemDialog() {

        if (true) {
            val v: View = (mContext as Activity).getLayoutInflater()
                .inflate(R.layout.dialog_current_redeem, null)
            dialogCurrentRedeemBinding = DataBindingUtil.bind(v)
            dialogRedeem = BottomSheetDialog(mContext as Activity, R.style.DialogStyle)
            dialogRedeem.setContentView(v)
            dialogRedeem.setCanceledOnTouchOutside(false)
            dialogRedeem.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.BLACK));
            dialogRedeem.getWindow()
                ?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            dialogCurrentRedeemBinding!!.btnNext.setOnClickListener {
                showMerchantsDialog()
                dialogCurrentRedeemBinding!!.cvRedeem.visibility = View.GONE


            }
            dialogCurrentRedeemBinding!!.close.setOnClickListener {

                dialogRedeem.dismiss()


            }
            dialogRedeem.setOnShowListener { dialogInterface ->

                val bottomSheetBehavior = BottomSheetBehavior.from((v.getParent()) as View)
                val bottomSheetDialog = dialogInterface as BottomSheetDialog
                val bottomSheet = bottomSheetDialog.findViewById<View>(
                    com.google.android.material.R.id.design_bottom_sheet
                )
                bottomSheet?.setBackgroundColor(Color.TRANSPARENT)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED


            }

        }

        dialogRedeem.show()
    }

    var dialogMerchantsOnlyBinding: DialogMerchantsOnlyBinding? = null
    lateinit var dialogMerchant: BottomSheetDialog

    fun showMerchantsDialog() {

        if (true) {
            val v: View = (mContext as Activity).getLayoutInflater()
                .inflate(R.layout.dialog_merchants_only, null)
            dialogMerchantsOnlyBinding = DataBindingUtil.bind(v)
            dialogMerchant = BottomSheetDialog(mContext as Activity, R.style.DialogStyle)
            dialogMerchant.setContentView(v)
            dialogMerchant.setCanceledOnTouchOutside(false)
            dialogMerchant.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.BLACK));
            dialogMerchant.getWindow()
                ?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            dialogMerchantsOnlyBinding!!.btnNext.setOnClickListener {
                dialogRedeem.dismiss()
                dialogMerchant.dismiss()

                val i = Intent(mContext, RedeemSuccessActivity::class.java)
                (mContext as Activity).startActivity(i)

            }
            dialogMerchantsOnlyBinding!!.close.setOnClickListener {
                dialogRedeem.dismiss()
                dialogMerchant.dismiss()


            }
            dialogMerchantsOnlyBinding!!.back.setOnClickListener {
                dialogMerchant.dismiss()
                dialogCurrentRedeemBinding!!.cvRedeem.visibility = View.VISIBLE

            }
            dialogMerchant.setOnShowListener { dialogInterface ->

                val bottomSheetBehavior = BottomSheetBehavior.from((v.getParent()) as View)
                val bottomSheetDialog = dialogInterface as BottomSheetDialog
                val bottomSheet = bottomSheetDialog.findViewById<View>(
                    com.google.android.material.R.id.design_bottom_sheet
                )
                bottomSheet?.setBackgroundColor(Color.TRANSPARENT)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED


            }

        }

        dialogMerchant.show()
    }
}