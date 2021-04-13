package com.tripkipedia.validator

import java.util.regex.Pattern

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class NameValidator(override var value: String?,var prefix : String? ) : Validatable {

    override var msg: String? = null

    override fun isValid(): Boolean {

        val pattern = Pattern.compile(
                "^[A-Za-z\\s\']*$"
        )
        if (value == null) {
            msg = "$prefix is required."
            return false
        } else if (value!!.isEmpty()) {
            msg = "$prefix is required."
            return false
        } else if(value!!.length < 2){
            msg = "Min. 2 character required."
            return false
        } else if(value!!.length > 15){
            msg = "Max. 15 character required."
            return false
        }else if (!pattern.matcher(value).matches()) {
            msg = "Invalid format. Valid format is : John"
            return false
        }
        return true
    }

}