package com.tripkipedia.ui.dashboard.home.viewmodel

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.location.Location
import android.location.LocationManager
import android.os.AsyncTask
import android.os.Looper
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.tripkipedia.R
import com.tripkipedia.apputils.Constant
import com.tripkipedia.apputils.Debug
import com.tripkipedia.apputils.Utils
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.FragmentHomeBinding
import com.tripkipedia.interfaces.CallbackListener
import com.tripkipedia.ui.dashboard.datamodel.ItemsData
import com.tripkipedia.ui.dashboard.datamodel.ItemsDataModel
import com.tripkipedia.ui.dashboard.home.utils.HomeAdapter
import com.tripkipedia.ui.dashboard.home.utils.SlidingImageAdapter
import com.tripkipedia.ui.freebiesDetails.view.FreebiesDetailsActivity
import org.json.JSONObject

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class HomeViewModel(application: Application) : BaseViewModel(application), OnMapReadyCallback {

    private lateinit var binder: FragmentHomeBinding
    lateinit var mContext: Context
    lateinit var adapter: HomeAdapter
    lateinit var itemsDataModel: ItemsDataModel
    lateinit var slidingPagerAdapter: SlidingImageAdapter
    var gMap: GoogleMap? = null
    val hashMap = HashMap<LatLng, Int>()
    lateinit var gCurrent: LatLng
    lateinit var flpg: FusedLocationProviderClient
    lateinit var childFragmentManager: FragmentManager


    fun setBinder(
        binder: FragmentHomeBinding,
        childFragmentManager: FragmentManager
    ) {
        this.binder = binder
        this.mContext = binder.root.context
        this.childFragmentManager = childFragmentManager
        binder.viewmodel = this
        binder.viewClickHandler = ViewClickHandler()
        itemsDataModel = ItemsDataModel()
        slidingPagerAdapter = SlidingImageAdapter(mContext)

        initDashboard()
        init()


    }

    private fun initDashboard() {
        flpg = LocationServices.getFusedLocationProviderClient(mContext as Activity)
        binder.pagerProductInfo.isNestedScrollingEnabled = false
        binder.pagerProductInfo.offscreenPageLimit = slidingPagerAdapter!!.count
        binder.pagerProductInfo.adapter = slidingPagerAdapter

        getItems()

    }

    private fun getItems() {
        isInternetAvailable(mContext, object : CallbackListener {
            override fun onSuccess() {
                showDialog("", (mContext as Activity))
                itemsDataModel.type = "Restaurants"
                itemsDataModel.page = "1"
                itemsDataModel.per_page = "10"
                itemsDataModel.getItems(mContext).observeForever { itemData ->

                    dismissDialog()

                    if (itemData.statusCode.equals(Constant.RESPONSE_FAILURE_CODE)) {


                    } else {
                        for (i in itemData.data!!.indices) {
                            hashMap[LatLng(
                                itemData.data!!.get(i).latitude!!.toDouble(),
                                itemData.data!!.get(i).longitude!!.toDouble()
                            )] = R.mipmap.pin_gift
                        }
                        val mapFragment =
                            childFragmentManager.findFragmentById((R.id.gmap)) as SupportMapFragment
                        mapFragment.getMapAsync(this@HomeViewModel)
                        slidingPagerAdapter!!.addAll(itemData.data as MutableList<ItemsData.Datum>)

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
        slidingPagerAdapter.setEventListener(object : SlidingImageAdapter.EventListener {
            override fun onItemViewClicked(position: Int, itemId: String?) {
                val i = Intent(mContext, FreebiesDetailsActivity::class.java)
                i.putExtra("itemId", itemId)
                (mContext as Activity).startActivity(i)
            }


        })
        binder.pagerProductInfo.addOnPageChangeListener(object :
            ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

                val latLng: LatLng? = slidingPagerAdapter.getLatLong(position)
                zoomToLocation(latLng!!)
            }

            override fun onPageSelected(position: Int) {


            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

    }


    inner class ViewClickHandler {

        fun onLocationClick() {
            getLastLocation()
        }

    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
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
                        zoomToLocation(gCurrent)
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

    fun zoomToLocation(gCurrent: LatLng) {
        gMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(gCurrent, 15f));
    }


    private fun bitmapDescriptor(
        context: Context,
        vectorResId: Int
    ): BitmapDescriptor? {
        val ve = ContextCompat.getDrawable(context, vectorResId)!!
        ve.setBounds(0, 0, ve.intrinsicWidth, ve.intrinsicHeight)
        val bitmap = Bitmap.createBitmap(
            ve.intrinsicWidth,
            ve.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val c = Canvas(bitmap)
        ve.draw(c)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    override fun onMapReady(p0: GoogleMap?) {
        gMap = p0
        if (ActivityCompat.checkSelfPermission(
                mContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                mContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        gMap!!.isMyLocationEnabled = true
        for ((key, value) in hashMap) {
            Debug.e("data", key.toString())
            gMap!!.addMarker(MarkerOptions().position(key).title("Black Ball"))
                .setIcon(mContext.let { bitmapDescriptor(it, value) })
            gMap!!.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
            gMap!!.moveCamera(CameraUpdateFactory.newLatLng(key))
        }
    }



}




