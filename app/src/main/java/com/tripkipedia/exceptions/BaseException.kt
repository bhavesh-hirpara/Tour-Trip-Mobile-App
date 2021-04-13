package com.tripkipedia.exceptions
/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
open class BaseException() : Exception() {

    open lateinit var errMessage : String
    open lateinit var title : String


}