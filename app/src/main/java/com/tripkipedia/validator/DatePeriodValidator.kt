package com.tripkipedia.validator

import java.util.*

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class DatePeriodValidator(var starDate: Date?,var endDate: Date?) {
    var msg: String? = null
    fun isValid() : Boolean{
        if(starDate!!.compareTo(endDate) == 0){
            return true
        }else if(starDate!!.compareTo(endDate) < 0){
            return true
        }else{
            msg = "Event start date should falls between selected period."
            return false
        }

    }

}