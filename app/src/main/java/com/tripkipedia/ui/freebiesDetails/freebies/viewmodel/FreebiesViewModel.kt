package com.tripkipedia.ui.freebiesDetails.freebies.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import com.tripkipedia.apputils.Constant
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.FragmentFreebiesBinding
import com.tripkipedia.interfaces.CallbackListener
import com.tripkipedia.ui.dashboard.datamodel.ItemsData
import com.tripkipedia.ui.dashboard.datamodel.ItemsDataModel
import com.tripkipedia.ui.dashboard.features.utils.FeaturesAdapter
import com.tripkipedia.ui.freebiesDetails.freebies.utils.FreebiesNearbyAdapter
import com.tripkipedia.ui.freebiesDetails.freebies.utils.GiftsAdapter
import com.tripkipedia.ui.freebiesDetails.freebies.utils.VouchersAdapter

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class FreebiesViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: FragmentFreebiesBinding
    lateinit var mContext: Context
    private var dataGifts: MutableList<String> = ArrayList<String>()
    private var dataVouchers: MutableList<String> = ArrayList<String>()
    lateinit var itemsDataModel: ItemsDataModel
    lateinit var adapterGifts: GiftsAdapter
    lateinit var adapterVouchers: VouchersAdapter
    lateinit var adapterNearby: FreebiesNearbyAdapter


    fun setBinder(binder: FragmentFreebiesBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        itemsDataModel = ItemsDataModel()
        initDashboard()
        init()

    }

    private fun initDashboard() {
        adapterGifts = GiftsAdapter(mContext)
        binder.rvGifts.adapter = adapterGifts


        adapterVouchers = VouchersAdapter(mContext)
        binder.rvVouchers.adapter = adapterVouchers


        adapterNearby = FreebiesNearbyAdapter(mContext)
        binder.rvNearbyFreebies.adapter = adapterNearby

        getItems()

    }

    private fun getItems() {
        isInternetAvailable(mContext, object : CallbackListener {
            override fun onSuccess() {

                itemsDataModel.type = "Restaurants"
                itemsDataModel.page = "1"
                itemsDataModel.per_page = "10"
                itemsDataModel.getItems(mContext).observeForever { itemData ->


                    if (itemData.statusCode == Constant.RESPONSE_FAILURE_CODE) {


                    } else {

                        adapterNearby.addAll(itemData.data as ArrayList<ItemsData.Datum>)

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


    }

    inner class ViewClickHandler {

    }


}


