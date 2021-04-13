package com.tripkipedia.exceptions

import com.tripkipedia.apputils.Debug
/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class PermissionException : BaseException() {

    override fun printStackTrace() {
        super.printStackTrace()
        Debug.e("Permission","Permission denied" )
    }
}