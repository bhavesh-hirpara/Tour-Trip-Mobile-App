package com.tripkipedia.base.datamodel
/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class ResData<T> (
        var status : Int,
        val data : Data<T>
)

data class Data<T> (
        val arrData : ArrayList<T>,
        val message : String
)