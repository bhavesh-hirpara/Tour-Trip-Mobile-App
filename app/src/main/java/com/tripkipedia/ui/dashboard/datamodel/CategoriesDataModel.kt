package com.tripkipedia.ui.dashboard.datamodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.tripkipedia.datasource.HomeRepository
import com.tripkipedia.network.APIClient
import com.tripkipedia.network.APIinterface

class CategoriesDataModel {
    fun getCategories(mContext: Context): MutableLiveData<CategoriesData> {
        val apInterface: APIinterface =
            APIClient.newRequestRetrofit(mContext).create(APIinterface::class.java)
        val homeRepository = HomeRepository(apInterface)
        return homeRepository.getCategories()

    }
}