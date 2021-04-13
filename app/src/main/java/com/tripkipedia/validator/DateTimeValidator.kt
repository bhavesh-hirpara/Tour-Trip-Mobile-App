package com.tripkipedia.validator

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class DateTimeValidator(var date: String?,var prefix : String?) {
    var msg: String? = null

    fun isValid(): Boolean {
        if(date == null){
            msg = "$prefix is required."
            return false
        }
        return true
    }
}