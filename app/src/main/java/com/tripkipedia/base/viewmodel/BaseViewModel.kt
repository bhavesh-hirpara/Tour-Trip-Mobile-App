package com.tripkipedia.base.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.graphics.Color
import android.net.Uri
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.mikepenz.materialdrawer.Drawer

import com.tripkipedia.R
import com.tripkipedia.apputils.MenuItem
import com.mikepenz.materialdrawer.DrawerBuilder
import com.tripkipedia.apputils.Utils
import com.tripkipedia.databinding.CustomSideMenuBinding
import com.tripkipedia.interfaces.TopBarClickListener
import com.tripkipedia.ui.dashboard.utils.SideMenuAdapter
import com.tripkipedia.ui.dashboard.view.DashboardActivity
import com.tripkipedia.ui.login.view.LoginActivity
import com.tripkipedia.ui.login.viewmodel.LoginViewModel
import com.tripkipedia.ui.myProfile.view.MyProfileActivity
import com.tripkipedia.ui.myRewards.view.MyRewardsActivity
import com.tripkipedia.ui.myTickets.view.MyTicketsActivity
import com.tripkipedia.ui.settings.contactUs.view.ContactUsActivity
import com.tripkipedia.ui.settings.privacyPolicy.view.PrivacyPolicyActivity
import com.tripkipedia.ui.settings.termsAndConditions.view.TermsAndConditionActivity
import com.tripkipedia.ui.settings.view.SettingsActivity

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
open class BaseViewModel(application: Application) : AppViewModel(application) {


    lateinit var result: Drawer
    private lateinit var activity: Activity
    lateinit var customSideMenuBinding: CustomSideMenuBinding
    var mEventListener: EventListener? = null
    public lateinit var supportFragmentManager: FragmentManager


    fun initDrawer(mContext: Context) {
        initDrawer(mContext as Activity)
    }


    fun initDrawer(activity: Activity) {
        this.activity = activity
        customSideMenuBinding = DataBindingUtil.inflate(
            activity.layoutInflater,
            R.layout.custom_side_menu,
            null,
            false
        )

        val linearLayoutManager = LinearLayoutManager(activity)
        customSideMenuBinding.rvMenuList.layoutManager = linearLayoutManager
        val mAdapter = SideMenuAdapter(activity)
        mAdapter.setEventListener(object : SideMenuAdapter.EventListener {

            override fun onMenuItemClick(position: Int, view: View) {
//                mAdapter.changeSelectedItemUi(position)
                onMenuItemClicked(mAdapter.getItem(position).menuId, view)
            }
        })
        customSideMenuBinding.rvMenuList.adapter = mAdapter
        val data = ArrayList<MenuItem>()
        data.add(MenuItem("1", R.mipmap.menu_home, getLabelText(R.string.home)))
        data.add(MenuItem("2", R.mipmap.menu_profile, getLabelText(R.string.my_profile)))
        data.add(MenuItem("3", R.mipmap.menu_reward, getLabelText(R.string.my_rewards)))
        data.add(MenuItem("4", R.mipmap.menu_ticket, getLabelText(R.string.my_tickets)))
        data.add(MenuItem("5", R.mipmap.menu_setting, getLabelText(R.string.settings)))
//        data.add(MenuItem("6", R.drawable.ic_menu_home, getLabelText(R.string.logout)))

        mAdapter.addAll(data)

        result = DrawerBuilder()
            .withActivity(activity)
            .withCloseOnClick(true)
            .withSelectedItemByPosition(-1)
            .withCustomView(customSideMenuBinding.root)
            .withDrawerWidthDp(300)
            .withDisplayBelowStatusBar(true)
            .withTranslucentStatusBar(true)
            .withSliderBackgroundColor(Color.GREEN)

            .build()
        result.drawerLayout.setScrimColor(Color.BLACK)
        result.drawerLayout.fitsSystemWindows = false

        customSideMenuBinding.tvPrivacy.setOnClickListener {
            val intent = Intent(activity, PrivacyPolicyActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            activity.startActivity(intent)
            hideMenu(false)
            finishActivity()
        }

        customSideMenuBinding.tvContactUs.setOnClickListener {
            val intent = Intent(activity, ContactUsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            activity.startActivity(intent)
            hideMenu(false)
            finishActivity()
        }

        customSideMenuBinding.tvTerms.setOnClickListener {
            val intent = Intent(activity, TermsAndConditionActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            activity.startActivity(intent)
            hideMenu(false)
            finishActivity()
        }

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
        }
    }

    fun onTopMenuClick() {
        toggleDrawer()
    }

    fun toggleDrawer() {
        if (result!!.isDrawerOpen) {
            result!!.closeDrawer()
        } else {
            result!!.openDrawer()
        }
    }

    fun checkDrawerOpenOrNot(): Boolean {
        if (result!!.isDrawerOpen) {
            result!!.closeDrawer()
            return false
        }
        return true
    }

    fun loadUrlinBrowser(context: Context, url: String) {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }


    private fun onMenuItemClicked(menuId: String, view: View) {
        when (menuId) {

            "1" -> {
                if (activity is DashboardActivity) {
                    hideMenu(true)
                } else {
                    val intent = Intent(activity, DashboardActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    activity.startActivity(intent)
                    hideMenu(false)
                    finishActivity()
                }
            }

            "2" -> {
                if (activity is MyProfileActivity) {
                    hideMenu(true)
                } else {
                    val intent = Intent(activity, MyProfileActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    activity.startActivity(intent)
                    hideMenu(false)
                    finishActivity()
                }
            }
            "3" -> {
                if (activity is MyRewardsActivity) {
                    hideMenu(true)
                } else {
                    val intent = Intent(activity, MyRewardsActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    activity.startActivity(intent)
                    hideMenu(false)
                    finishActivity()
                }
            }

            "4" -> {
                if (activity is MyTicketsActivity) {
                    hideMenu(true)
                } else {
                    val intent = Intent(activity, MyTicketsActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    activity.startActivity(intent)
                    hideMenu(false)
                    finishActivity()
                }
            }

            "5" -> {
                if (activity is SettingsActivity) {
                    hideMenu(true)
                } else {
                    val intent = Intent(
                        activity,
                        SettingsActivity::class.java
                    )
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    activity.startActivity(intent)
                    hideMenu(false)
                    finishActivity()
                }
            }
            "6" -> {
                hideMenu(true)
                val intent = Intent(activity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                activity.startActivity(intent)
                hideMenu(false)
                finishActivity()
            }

        }
    }

    private fun hideMenu(b: Boolean) {
        try {
            result!!.closeDrawer()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun finishActivity() {
        if (activity is DashboardActivity) {
        } else {
            activity.finish()
        }
    }


    interface EventListener {
        fun onMenuItemClick(taskType: String)
    }

    fun setEventListener(eventListener: EventListener) {
        this.mEventListener = eventListener
    }

    public fun addFragment(fragment: Fragment, isAddToBackStack: Boolean) {

//        val fragmentTransaction = supportFragmentManager.beginTransaction()
//            .replace(com.tripkipedia.R.id.frame, fragment)
//
//        if (isAddToBackStack)
//            fragmentTransaction.addToBackStack(fragment.tag)
//        else
//            fragmentTransaction.addToBackStack(null)
//
//        fragmentTransaction.commit()
    }

    fun showLocationEnableDialog(activity: AppCompatActivity) {
        val mLocationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval((10 * 1000).toLong())
            .setFastestInterval((1 * 1000).toLong())

        val settingsBuilder = LocationSettingsRequest.Builder()
            .addLocationRequest(mLocationRequest)
        settingsBuilder.setAlwaysShow(true)

        val result = LocationServices.getSettingsClient(getContext())
            .checkLocationSettings(settingsBuilder.build())

        result.addOnCompleteListener { task ->
            try {
                val response = task.getResult(ApiException::class.java)
            } catch (ex: ApiException) {
                when (ex.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                        try {
                            val resolvableApiException = ex as ResolvableApiException
                            resolvableApiException.startResolutionForResult(
                                activity,
                                5001
                            )
                        } catch (e: IntentSender.SendIntentException) {
                            e.printStackTrace()
                        }
                    }

                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                        //nothing
                    }
                }
            }
        }
    }


//    inner class bottomTabClickListener(
//        bottomSheetBinding: HomeBottomBarBinding,
//        mContext: Context
//    ) :
//        BottomClickListener {
//        var bottomSheetBinding: HomeBottomBarBinding = bottomSheetBinding
//        var mContext: Context = mContext
//
//        override fun onBottomClickListener(view: View?, value: String?) {
//
//            Utils.hideKeyBoard(getContext(), view!!)
//            if (value.equals(getLabelText(R.string.nav_documents))) {
//
//                try {
//                    val i = Intent(mContext, DocumentsActivity::class.java)
//                    i.putExtra("type", 0)
//                    mContext.startActivity(i)
//
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            } else {
//                try {
//                    val i = Intent(mContext, DashboardSigeActivity::class.java)
//                    mContext.startActivity(i)
//
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//
//        }
//    }

//    inner class bottomClickListener(bottomSheetBinding: BottomBarBinding, mContext: Context) :
//        BottomClickListener {
//        var bottomSheetBinding: BottomBarBinding = bottomSheetBinding
//        var mContext: Context = mContext
//
//        override fun onBottomClickListener(view: View?, value: String?) {
//
//            Utils.hideKeyBoard(getContext(), view!!)
//            if ((mContext is NewsActivity).not() && (value.equals(getLabelText(R.string.news)))) {
//                try {
//                    val i = Intent(mContext, NewsActivity::class.java)
//                    mContext.startActivity(i)
//                    deselectAll(bottomSheetBinding, mContext, value)
//
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            } else if ((mContext is DepartmentActivity).not() && (value.equals(getLabelText(R.string.department)))) {
//                try {
//                    val i = Intent(mContext, DepartmentActivity::class.java)
//                    mContext.startActivity(i)
//                    deselectAll(bottomSheetBinding, mContext, value)
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//
//            } else if ((mContext is CalenderActivity).not() && (value.equals(getLabelText(R.string.calender)))) {
//                try {
//                    val i = Intent(mContext, CalenderActivity::class.java)
//                    mContext.startActivity(i)
//                    deselectAll(bottomSheetBinding, mContext, value)
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//
//            } else if ((mContext is ContactUsActivity).not() && (value.equals(getLabelText(R.string.contact)))) {
//                try {
//                    val i = Intent(mContext, ContactUsActivity::class.java)
//                    mContext.startActivity(i)
//                    deselectAll(bottomSheetBinding, mContext, value)
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//
//            }
//
//        }
//    }

//    private fun deselectAll(
//        bottomSheetBinding: BottomBarBinding,
//        mContext: Context,
//        value: String?
//    ) {
//        /*bottomSheetBinding.tvBottomService.setTextColor(mContext.resources.getColor(R.color.col_333))
//        bottomSheetBinding.tvBottomAccount.setTextColor(mContext.resources.getColor(R.color.col_333))
//        ImageViewCompat.setImageTintList(bottomSheetBinding.imgBottomHome, ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.col_333)));
//        ImageViewCompat.setImageTintList(bottomSheetBinding.imgBottomAccount, ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.col_333)));
//        fillBottomSheet(bottomSheetBinding, mContext, value)*/
//        finishActivity()
//    }
}
