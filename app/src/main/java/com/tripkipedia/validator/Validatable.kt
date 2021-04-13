package com.tripkipedia.validator
/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
interface Validatable {

    var value: String?
    var msg: String?

    fun isValid() : Boolean
}