package com.tripkipedia.ui.dashboard.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tripkipedia.R
import com.tripkipedia.apputils.Debug
import com.tripkipedia.apputils.Utils
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.ActivityHomePageBinding
import com.tripkipedia.databinding.DialogHomeCountrySelectionBinding
import com.tripkipedia.interfaces.TopBarClickListener
import com.tripkipedia.ui.accountSetup.datamodel.Contry_Data
import com.tripkipedia.ui.accountSetup.utils.CountryAdapter
import com.tripkipedia.ui.dashboard.features.fragment.HomeListFragment
import com.tripkipedia.ui.dashboard.home.fragment.HomeMapFragment
import com.tripkipedia.ui.dashboard.utils.HomeViewPagerAdapter
import com.tripkipedia.ui.dashboard.view.DashboardActivity
import com.tripkipedia.ui.membership.view.MembershipInfoActivity
import com.tripkipedia.ui.myProfile.view.MyProfileActivity
import com.tripkipedia.ui.myRewards.view.MyRewardsActivity
import com.tripkipedia.ui.notifications.view.NotificationsActivity
import com.tripkipedia.ui.search.view.searchActivity
import java.util.ArrayList

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class DashboardViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityHomePageBinding
    lateinit var mContext: Context
    lateinit var homeViewPagerAdapter: HomeViewPagerAdapter


    fun setBinder(
        binder: ActivityHomePageBinding,
        supportFragmentManager: FragmentManager
    ) {
        this.binder = binder
        this.mContext = binder.root.context
        this.supportFragmentManager = supportFragmentManager
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        binder.topbar.isMenuShow = true
        binder.topbar.isNotificationShow = true
        binder.topbar.isSearchShow = true
        binder.topbar.topBarClickListener = SlideMenuClickListener()
        initDrawer(mContext)
        init()

    }

    fun init() {
        homeViewPagerAdapter =
            HomeViewPagerAdapter(
                (mContext as DashboardActivity).supportFragmentManager
            )
        homeViewPagerAdapter.addFragment(HomeListFragment())
        homeViewPagerAdapter.addFragment(HomeMapFragment())
        binder.viewpager.offscreenPageLimit = homeViewPagerAdapter.count
        binder.viewpager.adapter = homeViewPagerAdapter
        binder.tablayout.setupWithViewPager(binder.viewpager)
        binder.appBarLayout.elevation = 0F
        setupTabIcons()
    }

    private fun setupTabIcons() {
        binder.tablayout.getTabAt(0)!!.setIcon(R.mipmap.list)
        binder.tablayout.getTabAt(1)!!.setIcon(R.mipmap.map_icon)
    }

    inner class ViewClickHandler {


        fun onMembershipClick(view: View) {
            try {
                val i = Intent(mContext, MembershipInfoActivity::class.java)
                (mContext as Activity).startActivity(i)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun onMyRewardClick(view: View) {
            try {
                val i = Intent(mContext, MyRewardsActivity::class.java)
                (mContext as Activity).startActivity(i)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun onMyProfileClick(view: View) {
            try {
                val i = Intent(mContext, MyProfileActivity::class.java)
                (mContext as Activity).startActivity(i)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun onCountryClick(view: View) {
            try {
                showCountryDialog()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }

    var dialogHomeCountrySelectionBinding: DialogHomeCountrySelectionBinding? = null
    lateinit var dialogCountrySelection: BottomSheetDialog
    lateinit var countryAdapter: CountryAdapter

    fun showCountryDialog() {

        if (true) {
            val v: View = (mContext as Activity).getLayoutInflater()
                .inflate(R.layout.dialog_home_country_selection, null)
            dialogHomeCountrySelectionBinding = DataBindingUtil.bind(v)
            dialogCountrySelection = BottomSheetDialog(mContext as Activity, R.style.DialogStyle)
            dialogCountrySelection.setContentView(v)
            dialogCountrySelection.setCanceledOnTouchOutside(false)
            dialogCountrySelection.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.BLACK));
            dialogCountrySelection.getWindow()
                ?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            val list: ArrayList<Contry_Data> = ArrayList<Contry_Data>()
            val country: Array<String> =
                mContext.resources.getStringArray(R.array.countries_array)
            for (i in country.indices) {
                list.add(Contry_Data("" + i, country[i]))
                Debug.e("" + i.toString())
            }
            countryAdapter =
                CountryAdapter(mContext)
            countryAdapter.addAll(list)
            dialogHomeCountrySelectionBinding!!.rvCountry.adapter = countryAdapter

            dialogHomeCountrySelectionBinding!!.btnSave.setOnClickListener {
                dialogCountrySelection.dismiss()
            }

            dialogHomeCountrySelectionBinding!!.close.setOnClickListener {

                dialogCountrySelection.dismiss()


            }
            dialogCountrySelection.setOnShowListener { dialogInterface ->
                val bottomSheetBehavior = BottomSheetBehavior.from((v.getParent()) as View)
                val bottomSheetDialog = dialogInterface as BottomSheetDialog
                val bottomSheet = bottomSheetDialog.findViewById<View>(
                    com.google.android.material.R.id.design_bottom_sheet
                )
                bottomSheet?.setBackgroundColor(Color.TRANSPARENT)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED


            }

        }

        dialogCountrySelection.show()
    }

    inner class SlideMenuClickListener : TopBarClickListener {
        override fun onTopBarClickListener(view: View?, value: String?) {
            Utils.hideKeyBoard(getContext(), view!!)
            if (value.equals(getLabelText(R.string.menu))) {
                try {
                    onTopMenuClick()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            if (value.equals(getLabelText(R.string.notifications))) {
                try {
                    val i = Intent(mContext, NotificationsActivity::class.java)
                    (mContext as Activity).startActivity(i)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            if (value.equals(getLabelText(R.string.search))) {
                try {
                    val i = Intent(mContext, searchActivity::class.java)
                    (mContext as Activity).startActivity(i)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

}

