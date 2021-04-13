package com.tripkipedia.exceptions.networks

import com.tripkipedia.apputils.Debug
/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class NoInternetException : NetworkException() {

    override var errMessage: String = "No Internet Available"

    override var title: String = "Alert"

    override fun printStackTrace() {
        super.printStackTrace()
        Debug.e("NoInternet","No Internet Connection")
    }

    override fun getLocalizedMessage(): String {
        return super.getLocalizedMessage()
    }
}