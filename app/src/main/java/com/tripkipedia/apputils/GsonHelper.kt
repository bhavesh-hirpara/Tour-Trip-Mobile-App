package com.tripkipedia.apputils

import com.google.gson.Gson
/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
object GsonHelper{

    fun getGsonString(obj : Any) = Gson().toJson(obj)
}