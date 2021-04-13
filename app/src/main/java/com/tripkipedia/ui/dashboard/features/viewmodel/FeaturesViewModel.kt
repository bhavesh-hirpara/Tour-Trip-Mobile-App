package com.tripkipedia.ui.dashboard.features.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.tripkipedia.R
import com.tripkipedia.apputils.Constant
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.FragmentFeaturesBinding
import com.tripkipedia.interfaces.CallbackListener
import com.tripkipedia.ui.dashboard.datamodel.ItemsData
import com.tripkipedia.ui.dashboard.datamodel.ItemsDataModel
import com.tripkipedia.ui.dashboard.features.utils.FeaturesAdapter
import com.tripkipedia.ui.dashboard.home.utils.SlidingImageAdapter
import com.tripkipedia.ui.freebiesDetails.view.FreebiesDetailsActivity

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class FeaturesViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: FragmentFeaturesBinding
    lateinit var mContext: Context
    lateinit var itemsDataModel: ItemsDataModel
    private var data: MutableList<String> = ArrayList<String>()
    lateinit var adapter: FeaturesAdapter


    fun setBinder(binder: FragmentFeaturesBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        binder.viewmodel = this
        binder.viewClickHandler = ViewClickHandler()
        itemsDataModel = ItemsDataModel()
        initDashboard()
        init()
    }

    private fun initDashboard() {
        adapter = FeaturesAdapter(mContext)
        binder.rvFeatures.adapter = adapter

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

                        adapter.addAll(itemData.data as ArrayList<ItemsData.Datum>)

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
        adapter.setEventListener(object : FeaturesAdapter.EventListener {
            override fun onItemViewClicked(position: Int, itemId: String?) {
                val i = Intent(mContext, FreebiesDetailsActivity::class.java)
                i.putExtra("itemId", itemId)
                (mContext as Activity).startActivity(i)
            }


        })
    }

    inner class ViewClickHandler {
        fun onExtendClick() {


        }

    }


}


