package com.tripkipedia.ui.dashboard.datamodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.tripkipedia.datasource.HomeRepository
import com.tripkipedia.network.APIClient
import com.tripkipedia.network.APIinterface
import okhttp3.MultipartBody

class ItemsDataModel {
    lateinit var type: String
    lateinit var page: String
    lateinit var per_page: String
    lateinit var requestBody: MultipartBody

    fun getItems(mContext: Context): MutableLiveData<ItemsData> {
        val apInterface: APIinterface =
            APIClient.newRequestRetrofit(mContext).create(APIinterface::class.java)
        val homeRepository = HomeRepository(apInterface)
        return homeRepository.getItems(this)

    }
}