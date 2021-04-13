package com.tripkipedia.ui.onboarding.viewmodel

import android.app.Activity
import android.app.Application
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.util.DisplayMetrics
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.tripkipedia.R

import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.ActivityOnboardingBinding
import com.tripkipedia.ui.accountSetup.view.AccountSetupActivity
import com.tripkipedia.ui.dashboard.view.DashboardActivity
import com.tripkipedia.ui.login.view.LoginActivity
import com.tripkipedia.ui.onboarding.datamodel.MenuItem
import com.tripkipedia.ui.onboarding.utils.OnBoardingAdapter
import com.tripkipedia.ui.verify.view.SuccessActivity
/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class OnBoardingViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityOnboardingBinding
    lateinit var mContext: Context
    lateinit var onBoardingAdapter: OnBoardingAdapter

    val data = ArrayList<MenuItem>()


    fun setBinder(
        binder: ActivityOnboardingBinding,
        supportFragmentManager: FragmentManager
    ) {
        this.binder = binder
        this.mContext = binder.root.context
        this.supportFragmentManager = supportFragmentManager
        binder.viewModel = this

        initList()
        init()
    }

    private fun initList() {
        data.add(
            MenuItem(
                R.drawable.illustration,
                "freebies in the hidden gems\n" +
                        "of Singapore", "Explore and Collect"
                ,
                "#9A7AFC"
            )
        )
        data.add(
            MenuItem(
                R.drawable.illustration_green,
                "with anyone nearby",
                "Connect and Interact",
                "#83DC54"
            )
        )
        data.add(
            MenuItem(
                R.drawable.illustration_orange,
                "vouchers with your T-Coins",
                "Earn and Redeem",
                "#EB6159"
            )
        )
    }

    private fun init() {
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        onBoardingAdapter = OnBoardingAdapter(mContext)
        binder.mainViewPager.adapter = onBoardingAdapter
        onBoardingAdapter.addAll(data)

        binder.mainIndicator.setViewPager(binder.mainViewPager)




    }


    inner class ViewClickHandler {

        fun onLogin_Mobile(view: View) {
            try {
                val i = Intent(mContext, LoginActivity::class.java)
                (mContext as Activity).startActivity(i)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun onGuestClick() {
            val i = Intent(mContext, DashboardActivity::class.java)
            mContext.startActivity(i)
            (mContext as Activity).finish()
        }

        fun onFaceIdClick(v: View) {
            showSuccessDialog(R.layout.dialog_face_recognization)
        }
    }

    fun showSuccessDialog(view: Int) {
        val dialog: Dialog = Dialog(mContext)
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
        try {
            dialog.show()
            val handler = Handler()
            val startApp: Runnable = object : Runnable {
                override fun run() {
                    handler.removeCallbacks(this)
                    showDialogSuccessDialog(R.layout.dialog_success)
                    dialog.dismiss()

                }
            }

            fun startApplication(sleepTime: Long) {
                handler.postDelayed(startApp, sleepTime)
            }

            startApplication(2000)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun showDialogSuccessDialog(view: Int) {
        val dialog: Dialog = Dialog(mContext)
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
        try {
            dialog.show()
            val handler = Handler()
            val startApp: Runnable = object : Runnable {
                override fun run() {
                    handler.removeCallbacks(this)
                    dialog.dismiss()
                    val i = Intent(mContext, AccountSetupActivity::class.java)
                    mContext.startActivity(i)

                }
            }

            fun startApplication(sleepTime: Long) {
                handler.postDelayed(startApp, sleepTime)
            }

            startApplication(2000)
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

