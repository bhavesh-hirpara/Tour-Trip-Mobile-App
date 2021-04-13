package com.tripkipedia.network


import com.tripkipedia.ui.dashboard.datamodel.CategoriesData
import com.tripkipedia.ui.dashboard.datamodel.ItemsData
import com.tripkipedia.ui.dashboard.datamodel.ItemsDataModel
import com.tripkipedia.ui.dashboard.datamodel.TypesData
import com.tripkipedia.ui.freebiesDetails.datamodel.FreebiesDetailsData
import com.tripkipedia.ui.freebiesDetails.datamodel.FreebiesDetailsDataModel
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
interface APIinterface {



    @POST("item/get-items")
    fun getItems(@Body itemsDataModel: ItemsDataModel): Call<ItemsData>

    @POST("item/get-item")
    fun getFreebiesDetails(@Body freebiesDetailsDataModel: FreebiesDetailsDataModel): Call<FreebiesDetailsData>

    @GET ("item/get-types")
    fun getTypes(): Call<TypesData>

    @GET ("item/get-categories")
    fun getCategories(): Call<CategoriesData>
}