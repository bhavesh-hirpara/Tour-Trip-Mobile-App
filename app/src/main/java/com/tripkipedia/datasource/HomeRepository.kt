package com.tripkipedia.datasource


import androidx.lifecycle.MutableLiveData

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tripkipedia.apputils.Constant
import com.tripkipedia.apputils.Debug
import com.tripkipedia.network.APIinterface
import com.tripkipedia.ui.dashboard.datamodel.CategoriesData
import com.tripkipedia.ui.dashboard.datamodel.ItemsData
import com.tripkipedia.ui.dashboard.datamodel.ItemsDataModel
import com.tripkipedia.ui.dashboard.datamodel.TypesData
import com.tripkipedia.ui.freebiesDetails.datamodel.FreebiesDetailsData
import com.tripkipedia.ui.freebiesDetails.datamodel.FreebiesDetailsDataModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class HomeRepository(apiInterface: APIinterface) {


    private val apiInterface: APIinterface? = apiInterface

    fun getItems(itemsDataModel: ItemsDataModel): MutableLiveData<ItemsData> {
        val data = MutableLiveData<ItemsData>()
        val call = apiInterface!!.getItems(itemsDataModel)
        Debug.e("API", call.request().url().encodedPath())
        call.enqueue(object : Callback<ItemsData> {
            override fun onFailure(call: Call<ItemsData>, t: Throwable) {
                data.value = ItemsData(Constant.getFailureCode())
                Debug.e("getItems", "Fail"+t.message)
            }

            override fun onResponse(
                call: Call<ItemsData>,
                response: Response<ItemsData>
            ) {
                try {
                    if (response.isSuccessful) {
                        val itemsData = response.body()
                        itemsData!!.statusCode = Constant.getSuccessCode()
                        data.value = itemsData
                        Debug.e("getItems", Gson().toJson(itemsData))
                    } else {
                        try {
                            val inputAsString =
                                response.errorBody()!!.source().inputStream().bufferedReader()
                                    .use { it.readText() }
                            Debug.e("Input", inputAsString)
                            val sb = StringBuilder()
                            sb.append(inputAsString)
                            val itemsData =
                                Gson().fromJson<ItemsData>(
                                    JSONObject(inputAsString).toString(),
                                    object : TypeToken<ItemsData>() {}.type
                                )
                            itemsData.statusCode = Constant.getFailureCode()
                            data.value = itemsData
                        } catch (e: Exception) {
                            Debug.e("getItems", e.message)
                            data.value = ItemsData(Constant.getFailureCode())
                        }
                    }
                } catch (e: Exception) {
                    Debug.e("getItems", e.message)
                    data.value = ItemsData(Constant.getFailureCode())
                }
            }
        })
        return data
    }
    fun getFreebiesDetails(freebiesDetailsDataModel: FreebiesDetailsDataModel): MutableLiveData<FreebiesDetailsData> {
        val data = MutableLiveData<FreebiesDetailsData>()
        val call = apiInterface!!.getFreebiesDetails(freebiesDetailsDataModel)
        Debug.e("API", call.request().url().encodedPath())
        call.enqueue(object : Callback<FreebiesDetailsData> {
            override fun onFailure(call: Call<FreebiesDetailsData>, t: Throwable) {
                data.value = FreebiesDetailsData(Constant.getFailureCode())
                Debug.e("getFreebiesDetails", "Fail")
            }

            override fun onResponse(
                call: Call<FreebiesDetailsData>,
                response: Response<FreebiesDetailsData>
            ) {
                try {
                    if (response.isSuccessful) {
                        val freebiesDetailsData = response.body()
                        freebiesDetailsData!!.statusCode = Constant.getSuccessCode()
                        data.value = freebiesDetailsData
                        Debug.e("getFreebiesDetails", Gson().toJson(freebiesDetailsData))
                    } else {
                        try {
                            val inputAsString =
                                response.errorBody()!!.source().inputStream().bufferedReader()
                                    .use { it.readText() }
                            Debug.e("Input", inputAsString)
                            val sb = StringBuilder()
                            sb.append(inputAsString)
                            val freebiesDetailsData =
                                Gson().fromJson<FreebiesDetailsData>(
                                    JSONObject(inputAsString).toString(),
                                    object : TypeToken<FreebiesDetailsData>() {}.type
                                )
                            freebiesDetailsData.statusCode = Constant.getFailureCode()
                            data.value = freebiesDetailsData
                        } catch (e: Exception) {
                            Debug.e("getFreebiesDetails", e.message)
                            data.value = FreebiesDetailsData(Constant.getFailureCode())
                        }
                    }
                } catch (e: Exception) {
                    Debug.e("getFreebiesDetails", e.message)
                    data.value = FreebiesDetailsData(Constant.getFailureCode())
                }
            }
        })
        return data
    }

    fun getTypes(): MutableLiveData<TypesData> {
        val data = MutableLiveData<TypesData>()
        val call = apiInterface!!.getTypes()
        Debug.e("API", call.request().url().encodedPath())
        call.enqueue(object : Callback<TypesData> {
            override fun onFailure(call: Call<TypesData>, t: Throwable) {
                data.value = TypesData(Constant.getFailureCode())
                Debug.e("getTypes", "Fail")
            }

            override fun onResponse(
                call: Call<TypesData>,
                response: Response<TypesData>
            ) {
                try {
                    if (response.isSuccessful) {
                        val typesData = response.body()
                        typesData!!.statusCode = Constant.getSuccessCode()
                        data.value = typesData
                        Debug.e("getTypes", Gson().toJson(typesData))
                    } else {
                        try {
                            val inputAsString =
                                response.errorBody()!!.source().inputStream().bufferedReader()
                                    .use { it.readText() }
                            Debug.e("Input", inputAsString)
                            val sb = StringBuilder()
                            sb.append(inputAsString)
                            val typesData =
                                Gson().fromJson<TypesData>(
                                    JSONObject(inputAsString).toString(),
                                    object : TypeToken<TypesData>() {}.type
                                )
                            typesData.statusCode = Constant.getFailureCode()
                            data.value = typesData
                        } catch (e: Exception) {
                            Debug.e("getTypes", e.message)
                            data.value = TypesData(Constant.getFailureCode())
                        }
                    }
                } catch (e: Exception) {
                    Debug.e("getTypes", e.message)
                    data.value = TypesData(Constant.getFailureCode())
                }
            }
        })
        return data
    }

    fun getCategories(): MutableLiveData<CategoriesData> {
        val data = MutableLiveData<CategoriesData>()
        val call = apiInterface!!.getCategories()
        Debug.e("API", call.request().url().encodedPath())
        call.enqueue(object : Callback<CategoriesData> {
            override fun onFailure(call: Call<CategoriesData>, t: Throwable) {
                data.value = CategoriesData(Constant.getFailureCode())
                Debug.e("getCategories", "Fail")
            }

            override fun onResponse(
                call: Call<CategoriesData>,
                response: Response<CategoriesData>
            ) {
                try {
                    if (response.isSuccessful) {
                        val categoriesData = response.body()
                        categoriesData!!.statusCode = Constant.getSuccessCode()
                        data.value = categoriesData
                        Debug.e("getCategories", Gson().toJson(categoriesData))
                    } else {
                        try {
                            val inputAsString =
                                response.errorBody()!!.source().inputStream().bufferedReader()
                                    .use { it.readText() }
                            Debug.e("Input", inputAsString)
                            val sb = StringBuilder()
                            sb.append(inputAsString)
                            val categoriesData =
                                Gson().fromJson<CategoriesData>(
                                    JSONObject(inputAsString).toString(),
                                    object : TypeToken<CategoriesData>() {}.type
                                )
                            categoriesData.statusCode = Constant.getFailureCode()
                            data.value = categoriesData
                        } catch (e: Exception) {
                            Debug.e("getCategories", e.message)
                            data.value = CategoriesData(Constant.getFailureCode())
                        }
                    }
                } catch (e: Exception) {
                    Debug.e("getCategories", e.message)
                    data.value = CategoriesData(Constant.getFailureCode())
                }
            }
        })
        return data
    }
}






