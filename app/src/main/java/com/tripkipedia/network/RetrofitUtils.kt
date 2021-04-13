package com.tripkipedia.network

import okhttp3.ResponseBody

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
object RetrofitUtils {

    fun getResponseString(responseBody: ResponseBody?) : String{
        return responseBody!!.source().inputStream().bufferedReader().use { it.readText() }
    }
}