package com.tripkipedia.ui.notifications.updates.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tripkipedia.R
import com.tripkipedia.databinding.ItemGetRideBinding
import com.tripkipedia.databinding.ItemInvitesBinding
import com.tripkipedia.databinding.ItemUpdatesBinding
import com.tripkipedia.ui.verify.view.SuccessActivity
import kotlinx.android.synthetic.main.dialog_con.*

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class UpdatesAdapter() : RecyclerView.Adapter<UpdatesAdapter.MyViewHolder>() {

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

    class MyViewHolder(internal var itemBinder: ItemUpdatesBinding) :
        RecyclerView.ViewHolder(itemBinder.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val v: ItemUpdatesBinding =
            DataBindingUtil.inflate<ItemUpdatesBinding>(
                inflater,
                R.layout.item_updates, parent, false

            )
        return UpdatesAdapter.MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        if (data.equals(null)) {
            return 0
        } else {
            return data.size

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (position == 0) {
            holder.itemBinder.imgLogo.setImageResource(R.mipmap.kfc_logo)
            holder.itemBinder.tvTitle.setText("Freebies OPEN!")
            holder.itemBinder.tvSecond.setText("Start your journey now")
            holder.itemBinder.btnNext.setText("Go")
        }
        if (position == 1) {
            holder.itemBinder.imgLogo.setImageResource(R.mipmap.pizza_logo)
            holder.itemBinder.tvTitle.setText("Freebies Rewarded")
            holder.itemBinder.tvSecond.setText("Freebies ready for collection")
            holder.itemBinder.btnNext.setText("Collect Now")
            holder.itemBinder.btnNext.setOnClickListener {
                showCongratulationsDialog(R.layout.dialog_con)
            }
        }
        if (position == 2) {
            holder.itemBinder.imgLogo.setImageResource(R.mipmap.coins_logo)
            holder.itemBinder.tvTitle.setText("You have 5 friends!")
            holder.itemBinder.tvSecond.setText("100 T-Coins rewarded")
            holder.itemBinder.btnNext.setText("Collect Now")
            holder.itemBinder.btnNext.setOnClickListener {
                showRewardReceiveDialog(R.layout.dialog_reward_receive)
            }
        }
        if (position == 3) {
            holder.itemBinder.imgLogo.setImageResource(R.mipmap.sp_logo)
            holder.itemBinder.tvTitle.setText("Enjoy up to 10% ")
            holder.itemBinder.tvSecond.setText("Special rewards, just for you!")
            holder.itemBinder.btnNext.setText("Learn More")
        }
        if (position == 4) {
            holder.itemBinder.imgLogo.setImageResource(R.mipmap.coins_logo)
            holder.itemBinder.tvTitle.setText("You have purchased 3 tickets!")
            holder.itemBinder.tvSecond.setText("150 T-Coins rewarded")
            holder.itemBinder.btnNext.setText("Collect Now")
                    holder.itemBinder.btnNext.setOnClickListener {
                showRewardReceiveDialog(R.layout.dialog_reward_receive)
            }
        }
        if (position == 5) {
            holder.itemBinder.imgLogo.setImageResource(R.mipmap.coins_logo)
            holder.itemBinder.tvTitle.setText("You referred to a friend!")
            holder.itemBinder.tvSecond.setText("50 T-Coins rewarded")
            holder.itemBinder.btnNext.setText("Collect Now")
            holder.itemBinder.btnNext.setOnClickListener {
                showRewardReceiveDialog(R.layout.dialog_reward_receive)
            }
        }
        if (position == 6) {
            holder.itemBinder.imgLogo.setImageResource(R.mipmap.coins_logo)
            holder.itemBinder.tvTitle.setText("You registered an account!")
            holder.itemBinder.tvSecond.setText("100 T-Coins rewarded")
            holder.itemBinder.btnNext.setText("Collect Now")
            holder.itemBinder.btnNext.setOnClickListener {
                showRewardReceiveDialog(R.layout.dialog_reward_receive)
            }
        }
        if (position == 7) {
            holder.itemBinder.imgLogo.setImageResource(R.mipmap.coins_logo)
            holder.itemBinder.tvTitle.setText("You have submitted a review!")
            holder.itemBinder.tvSecond.setText("30 T-Coins rewarded")
            holder.itemBinder.btnNext.setText("Collect Now")
            holder.itemBinder.btnNext.setOnClickListener {
                showRewardReceiveDialog(R.layout.dialog_reward_receive)
            }
        }


    }

    fun showCongratulationsDialog(view: Int) {
        val dialog: Dialog = Dialog(mContext!!)
        dialog.setContentView(view)

        val dialogWindow: Window? = dialog.window
        val lp: WindowManager.LayoutParams = dialogWindow!!.getAttributes()
        lp.x = 5 // The new position of the X coordinates
        lp.y = 5 // The new position of the Y coordinates
        lp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lp.width = getWidth()
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.dimAmount = 1f;
        dialog.getWindow()?.setAttributes(lp);
        dialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.BLACK));
        dialog.close.setOnClickListener {
            dialog.dismiss()
        }
        try {
            dialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
    fun showRewardReceiveDialog(view: Int) {
        val dialog: Dialog = Dialog(mContext!!)
        dialog.setContentView(view)

        val dialogWindow: Window? = dialog.window
        val lp: WindowManager.LayoutParams = dialogWindow!!.getAttributes()
        lp.x = 5 // The new position of the X coordinates
        lp.y = 5 // The new position of the Y coordinates
        lp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lp.width = getWidth()
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.dimAmount = 1f;
        dialog.getWindow()?.setAttributes(lp);
        dialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.BLACK));
        dialog.close.setOnClickListener {
            dialog.dismiss()
        }
        try {
            dialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
    fun getWidth(): Int {
        val displayMetrics = DisplayMetrics()
        val windowmanager =
            this!!.mContext!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics)
        return displayMetrics.widthPixels;
    }
}