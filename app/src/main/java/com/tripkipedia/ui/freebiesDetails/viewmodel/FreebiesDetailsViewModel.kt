package com.tripkipedia.ui.freebiesDetails.viewmodel

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Looper
import android.provider.Settings
import android.view.View
import android.widget.LinearLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentManager
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.tripkipedia.R
import com.tripkipedia.apputils.Constant
import com.tripkipedia.apputils.Debug
import com.tripkipedia.apputils.Utils
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.ActivityFreebiesDetailsBinding
import com.tripkipedia.interfaces.CallbackListener
import com.tripkipedia.interfaces.TopBarClickListener
import com.tripkipedia.ui.freebiesDetails.datamodel.FreebiesDetailsDataModel
import com.tripkipedia.ui.freebiesDetails.utils.ScreenSlidePage
import com.tripkipedia.ui.freebiesDetails.view.FreebiesDetailsActivity


/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class FreebiesDetailsViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityFreebiesDetailsBinding
    lateinit var mContext: Context
    lateinit var screenSlidePage: ScreenSlidePage
    lateinit var freebiesDetailsDataModel: FreebiesDetailsDataModel
    lateinit var gCurrent: LatLng
    lateinit var gDestination: LatLng
    lateinit var flpg: FusedLocationProviderClient

    fun setBinder(
        binder: ActivityFreebiesDetailsBinding,
        supportFragmentManager: FragmentManager
    ) {
        this.binder = binder
        this.mContext = binder.root.context
        this.supportFragmentManager = supportFragmentManager
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        freebiesDetailsDataModel = FreebiesDetailsDataModel()
        binder.topbar.isBackShow = true
        binder.topbar.isTextShow = true
        binder.topbar.isShareShow = true
        binder.topbar.isHomeShow = true
        binder.topbar.tvTitle.text = "Black Ball"
        this.binder.topbar.topBarClickListener = SlideMenuClickListener()
        flpg = LocationServices.getFusedLocationProviderClient(mContext as Activity)
        getCurrentLocation()
        getItems()
        init()

    }

    private fun getItems() {
        isInternetAvailable(mContext, object : CallbackListener {
            override fun onSuccess() {
                showDialog("", (mContext as Activity))
                freebiesDetailsDataModel.item_id =
                    (mContext as Activity).intent.getStringExtra("itemId")
                freebiesDetailsDataModel.getFreebiesDetailsData(mContext)
                    .observeForever { itemData ->

                        dismissDialog()

                        if (itemData.statusCode.equals(Constant.RESPONSE_FAILURE_CODE)) {


                        } else {
                            Utils.loadImage(
                                binder.imgMain,
                                itemData.data!!.photo!!,
                                mContext,
                                R.mipmap.freebies_card_image
                            )
                            binder.topbar.tvTitle.text = itemData.data!!.name
                            binder.txtTitle.text = itemData.data!!.name
                            binder.txtAddress.text =
                                itemData.data!!.address!!.streetName + "No." + itemData.data!!.address!!.unitNumber + "\n" + itemData.data!!.address!!.buildingName + " " + itemData.data!!.address!!.postalCode

                            gDestination = LatLng(
                                itemData.data!!.latitude!!.toDouble(),
                                itemData.data!!.longitude!!.toDouble()
                            )
                        }

                    }
            }

            override fun onCancel() {

            }

            override fun onRetry() {

                getItems()
            }

        })


    }

    fun init() {
        screenSlidePage =
            ScreenSlidePage(
                (mContext as FreebiesDetailsActivity).supportFragmentManager,
                (mContext as Activity).intent.getStringExtra("itemId")
            )
        binder.viewpager.offscreenPageLimit = screenSlidePage.count
        binder.viewpager.adapter = screenSlidePage
        binder.tablayout.setupWithViewPager(binder.viewpager)
        binder.viewpager.measure(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
    }


    inner class ViewClickHandler {

        fun onGetDirectionClick() {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=${gCurrent.latitude},${gCurrent.longitude}&daddr=${gDestination.latitude},${gDestination.longitude}")
            )
            (mContext as Activity).startActivity(intent)
        }


    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        if (checkPermissions()) {
            if (true) {
                flpg.lastLocation.addOnCompleteListener((mContext as Activity)) { task ->

                    val location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {

                        Debug.e(
                            "aaaa",
                            location.latitude.toString() + " - " + location.longitude.toString()
                        )
                        gCurrent = LatLng(location.latitude, location.longitude)
                    }
                }
            } else {
                Utils.showToast(mContext!!, "Turn on location")
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                mContext.startActivity(intent)
            }
        } else {
            // requestPermissions()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        flpg = LocationServices.getFusedLocationProviderClient((mContext as Activity))
        flpg.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var mLastLocation: Location = locationResult.lastLocation
            //   findViewById<TextView>(R.id.latTextView).text = mLastLocation.latitude.toString()
            //  findViewById<TextView>(R.id.lonTextView).text = mLastLocation.longitude.toString()


        }
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                mContext!!,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                mContext!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
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

            if (value.equals(getLabelText(R.string.home))) {
                try {
                    (mContext as Activity).finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

}

