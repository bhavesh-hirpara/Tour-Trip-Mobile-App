package com.tripkipedia.base.view

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.*

import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.tripkipedia.BuildConfig
import com.tripkipedia.R
import com.tripkipedia.apputils.*
import com.tripkipedia.apputils.MenuItem
import com.tripkipedia.ui.dashboard.utils.SideMenuAdapter
import com.tripkipedia.ui.dashboard.view.DashboardActivity
import com.tripkipedia.ui.myProfile.view.MyProfileActivity
import com.tripkipedia.ui.myRewards.view.MyRewardsActivity
import com.tripkipedia.ui.myTickets.view.MyTicketsActivity
import com.tripkipedia.ui.settings.view.SettingsActivity

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
open class BaseActivity : AppCompatActivity() {

    internal var ad: AsyncProgressDialog? = null

    private val PermissionsRequestCode = 123

    internal lateinit var commonReciever: MyServiceReciever

    val activity: BaseActivity
        get() = this

    private var checkActivity: AppCompatActivity? = null

    private var tvTitleText: TextView? = null

    internal lateinit var toast: Toast


    internal lateinit var baseCallback: BaseCallback

    @SuppressLint("ShowToast")
    public override fun onCreate(savedInstanceState: Bundle?) {

        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        Utils.setStatusBarGradient(activity, R.drawable.bg_gradiant, false)

        super.onCreate(savedInstanceState)
        toast = Toast.makeText(activity, "", Toast.LENGTH_LONG)

        val intentFilter = IntentFilter()
        intentFilter.addAction(Constant.FINISH_ACTIVITY)
        commonReciever = MyServiceReciever()
        LocalBroadcastManager.getInstance(this).registerReceiver(
            commonReciever, intentFilter
        )
    }

    override fun onResume() {
//        if (dialog == null)
//            checkPermissions(activity)
//        else if (dialog != null && dialog!!.isShowing.not())
//            checkPermissions(activity)
        super.onResume()
    }

    var dialog: AlertDialog? = null
    fun showPermissionAlert() {
        val builder = AlertDialog.Builder(activity, R.style.MyAlertDialogStyle)
        builder.setTitle(getString(R.string.need_permission_title))
        builder.setCancelable(false)
        builder.setMessage(getString(R.string.err_need_permission_msg))
        builder.setPositiveButton(R.string.btn_ok) { dialog, which ->
            startActivity(
                Intent(
                    android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + BuildConfig.APPLICATION_ID)
                )
            )
            finish()
        }
        builder.setNeutralButton(R.string.btn_cancel) { dialog, which -> finish() }
        dialog = builder.create()
        dialog!!.show()

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun setStatusBarGradiant(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
            window.navigationBarColor = ContextCompat.getColor(this, android.R.color.transparent)
            window.setBackgroundDrawableResource(R.drawable.top_bar_background)

        }
    }


    internal inner class MyServiceReciever : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            try {
                if (intent.action!!.equals(
                        Constant.FINISH_ACTIVITY, ignoreCase = true
                    )
                ) {
                    finish()
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        val handled = super.onKeyDown(keyCode, event)

        // Eat the long press event so the keyboard doesn't come up.
        return if (keyCode == KeyEvent.KEYCODE_MENU && event.isLongPress) {
            true
        } else handled

    }


    override fun onDestroy() {
        try {
            LocalBroadcastManager.getInstance(applicationContext)
                .unregisterReceiver(commonReciever)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        super.onDestroy()
    }


    internal interface BaseCallback {
        fun onMasterDataLoad()
    }


    fun finishActivity() {
//        if (activity is MainActivity) {
//        } else {
//            activity.finish()
//        }
    }

    lateinit var result: Drawer
    lateinit var customSideMenu: ViewGroup

    fun initDrawer(b: Boolean) {

        val imgMenu: AppCompatImageView
        imgMenu = findViewById<AppCompatImageView>(R.id.imgMenu)

        if (b) {
            customSideMenu =
                layoutInflater.inflate(R.layout.custom_side_menu, null, false) as ViewGroup


            val layoutManager: RecyclerView.LayoutManager
            val menuAdapter: SideMenuAdapter
            val toolbar = findViewById<Toolbar>(R.id.toolbar)
            val mRecyclerView = customSideMenu.findViewById<RecyclerView>(R.id.rvMenuList)
            setSupportActionBar(toolbar)


//            if (getSupportActionBar() != null) {
//                getSupportActionBar()?.setDisplayShowTitleEnabled(false); // hide built-in Title
//
//                // Setting background using a drawable
//                val toolbarBackground = ContextCompat.getDrawable(activity,R.drawable.bg_gradiant);
//                getSupportActionBar()?.setBackgroundDrawable(toolbarBackground);
//            }

            layoutManager = LinearLayoutManager(activity)
            mRecyclerView.layoutManager = layoutManager
            menuAdapter = SideMenuAdapter(activity)
            mRecyclerView.adapter = menuAdapter

            val data = ArrayList<MenuItem>()
            data.add(
                MenuItem(
                    "1",
                    R.mipmap.menu_home,
                    getLabelText(R.string.home)
                )
            )
            data.add(
                MenuItem(
                    "2",
                    R.mipmap.menu_profile,
                    getLabelText(R.string.my_profile)
                )
            )
            data.add(
                MenuItem(
                    "3",
                    R.mipmap.menu_reward,
                    getLabelText(R.string.my_rewards)
                )
            )
            data.add(
                MenuItem(
                    "4",
                    R.mipmap.menu_ticket,
                    getLabelText(R.string.my_tickets)
                )
            )
            data.add(
                MenuItem(
                    "5",
                    R.mipmap.menu_setting,
                    getLabelText(R.string.settings)
                )
            )

            menuAdapter.addAll(data)

            menuAdapter.setEventListener(object : SideMenuAdapter.EventListener {
                override fun onMenuItemClick(position: Int, view: View) {
                    Debug.e("aaa", "one")
                    result.closeDrawer()

                    onMenuItemClicked(menuAdapter.getItem(position).menuId, position)
                }
            })

            result = DrawerBuilder()
                .withActivity(activity)
                .withCloseOnClick(true)
                .withSelectedItemByPosition(-1)
                .withCustomView(customSideMenu)
                .withDrawerWidthDp(300)
                .withDisplayBelowStatusBar(true)
                .withTranslucentStatusBar(false)
                .build()

            result.drawerLayout.fitsSystemWindows = false


            imgMenu.visibility = View.VISIBLE


            imgMenu.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    //Your code here

                    if (result.isDrawerOpen) {
                        result.closeDrawer()
                    } else {
                        result.openDrawer()
                    }
                }
            })

        } else {
            imgMenu.visibility = View.GONE
        }
    }

    fun getLabelText(resId: Int): String {
        return activity.getString(resId)
    }

    private fun onMenuItemClicked(menuId: String, pos: Int) {

        when (menuId) {
            "1" -> {
                if (activity is DashboardActivity) {
                    hideMenu()
                } else {
                    val i = Intent(activity, DashboardActivity::class.java)
                    i.flags = (Intent.FLAG_ACTIVITY_CLEAR_TOP
                            or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    activity.startActivity(i)

                }


            }
            "2" -> {
                if (activity is MyProfileActivity) {
                    hideMenu()
                } else {
                    val i = Intent(activity, MyProfileActivity::class.java)
                    i.flags = (Intent.FLAG_ACTIVITY_CLEAR_TOP
                            or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    activity.startActivity(i)

                }


            }
            "3" -> {
                if (activity is MyRewardsActivity) {
                    hideMenu()
                } else {
                    val i = Intent(activity, MyRewardsActivity::class.java)
                    i.flags = (Intent.FLAG_ACTIVITY_CLEAR_TOP
                            or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    activity.startActivity(i)


                }

            }
            "4" -> {
                if (activity is MyTicketsActivity) {
                    hideMenu()
                } else {
                    val i = Intent(activity, MyTicketsActivity::class.java)
                    i.flags = (Intent.FLAG_ACTIVITY_CLEAR_TOP
                            or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    activity.startActivity(i)


                }

            }
            "5" -> {
                if (activity is SettingsActivity) {
                    hideMenu()
                } else {
                    val i = Intent(activity, SettingsActivity::class.java)
                    i.flags = (Intent.FLAG_ACTIVITY_CLEAR_TOP
                            or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    activity.startActivity(i)


                }
            }

        }
    }

    fun hideMenu() {
        try {
            result.closeDrawer()
        } catch (e: Exception) {
        }
    }

    interface PermissionListener {
        fun onGranted()

        fun onDenied()
    }

    fun checkPermission(activity: Activity, permissionsListener: PermissionListener) {
        Dexter.withActivity(activity)
            .withPermissions(
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener {

                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    Debug.e("onPermissionsChecked", "" + report.areAllPermissionsGranted())
                    Debug.e("onPermissionsChecked", "" + report.isAnyPermissionPermanentlyDenied)

                    if (report.areAllPermissionsGranted()) {
                        permissionsListener.onGranted()
                    } else {
                        permissionsListener.onDenied()
                        showPermissionAlert()
                    }

                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    Debug.e("onPermissionRationale", "" + permissions.size)
                    token.continuePermissionRequest()
                }


            }).check()
    }

}
