package com.tripkipedia.ui.freebiesDetails.datamodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.tripkipedia.datasource.HomeRepository
import com.tripkipedia.network.APIClient
import com.tripkipedia.network.APIinterface

class FreebiesDetailsDataModel {
    lateinit var item_id: String

    fun getFreebiesDetailsData(mContext: Context): MutableLiveData<FreebiesDetailsData> {

        val apInterface: APIinterface =
            APIClient.newRequestRetrofit(mContext).create(APIinterface::class.java)
        val homeRepository = HomeRepository(apInterface)
        return homeRepository.getFreebiesDetails(this)

    }
}