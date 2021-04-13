package com.tripkipedia.ui.membership.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.LinearLayout
import com.tripkipedia.R
import com.tripkipedia.apputils.Utils
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.ActivityMembershipBinding
import com.tripkipedia.interfaces.TopBarClickListener
import com.tripkipedia.ui.dashboard.view.DashboardActivity
import com.tripkipedia.ui.membership.utils.TCoinAdapter
import com.tripkipedia.ui.tCoinsHistory.view.TCoinHistoryActivity

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class MembershipInfoViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityMembershipBinding
    lateinit var mContext: Context
    private var dataTCoin: MutableList<String> = ArrayList<String>() as MutableList<String>
    lateinit var tCoinAdapter: TCoinAdapter


    fun setBinder(binder: ActivityMembershipBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        binder.topbar.isBackShow = true
        binder.topbar.isTextShow = true
        binder.topbar.tvTitle.setText("Membership Info")
        binder.topbar.topBarClickListener=SlideMenuClickListener()
        init()

    }

    fun init() {

        for (i: Int in 0..4) {
            dataTCoin.add("l" + i)
        }
        tCoinAdapter = TCoinAdapter(mContext)
        tCoinAdapter.addAll(dataTCoin as ArrayList<String>)
        binder.rvTCoins.adapter = tCoinAdapter

        binder.rvTCoins.measure(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
    }

    inner class ViewClickHandler {
        fun onNextClick() {
            val i = Intent(mContext, TCoinHistoryActivity::class.java)
            (mContext as Activity).startActivity(i)

        }

        fun onMemberClick() {

            binder.imgMember.setImageResource(R.drawable.ic_current)
            binder.imgSilver.setImageResource(R.drawable.ic_locked)
            binder.imgGold.setImageResource(R.drawable.ic_locked)
            binder.imgVip.setImageResource(R.drawable.ic_locked)

            binder.imgProgress.setImageResource(R.mipmap.fram_1)

            binder.tvPost.setText("MEMBER")
            binder.tvTitle.setText("Welcome!")
            binder.tvSecond.setText("Progress to the Silver tier provides you more perks. You can earn T-Coins from collecting Freebies, purchasing a ticket in Tripkipedia, referring this app to your friends!")
            binder.tvThird.setText("Earn 50 T-Coins by 31 Dec 2019 to unlock Silver")
        }

        fun onSilverClick() {
            binder.imgMember.setImageResource(R.drawable.ic_current)
            binder.imgSilver.setImageResource(R.drawable.ic_current)
            binder.imgGold.setImageResource(R.drawable.ic_locked)
            binder.imgVip.setImageResource(R.drawable.ic_locked)

            binder.imgProgress.setImageResource(R.mipmap.fram_2)

            binder.tvPost.setText("SILVER")
            binder.tvTitle.setText("Silver Perks!")
            binder.tvSecond.setText("- Earn 10% more T-Coins than Member\n" + "- Exclusive promotional deals and Freebies for\n" + "  Silver Tier only")
            binder.tvThird.setText("Earn 1,400 T-Coins by 31 Dec 2019 to unlock Gold")
        }

        fun onGoldClick() {
            binder.imgMember.setImageResource(R.drawable.ic_current)
            binder.imgSilver.setImageResource(R.drawable.ic_current)
            binder.imgGold.setImageResource(R.drawable.ic_current)
            binder.imgVip.setImageResource(R.drawable.ic_locked)

            binder.imgProgress.setImageResource(R.mipmap.fram_3)

            binder.tvPost.setText("GOLD")
            binder.tvTitle.setText("Gold Perks")
            binder.tvSecond.setText("- Earn 15% more T-Coins than Silver\n" + "- Exclusive promotional deals and Freebies for\n" + "  Gold Tier only")
            binder.tvThird.setText("Earn 200 T-Coins by 31 Dec 2019 to unlock VIP")
        }

        fun onVipClick() {
            binder.imgMember.setImageResource(R.drawable.ic_current)
            binder.imgSilver.setImageResource(R.drawable.ic_current)
            binder.imgGold.setImageResource(R.drawable.ic_current)
            binder.imgVip.setImageResource(R.drawable.ic_current)

            binder.imgProgress.setImageResource(R.mipmap.fram_3)

            binder.tvPost.setText("VIP")
            binder.tvTitle.setText("VIP Perks")
            binder.tvSecond.setText("- Earn 20% more T-Coins than Gold\n" + "- Exclusive promotional deals and Freebies for\n" + "  VIP Tier only")
            binder.tvThird.setText("Earn 200 T-Coins by 31 Dec 2019 to retain as VIP")
        }
    }

    inner class SlideMenuClickListener : TopBarClickListener {
        override fun onTopBarClickListener(view: View?, value: String?) {
            Utils.hideKeyBoard(getContext(), view!!)
            if (value.equals(getLabelText(R.string.back))) {
                try {
                    (mContext as Activity).finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}


