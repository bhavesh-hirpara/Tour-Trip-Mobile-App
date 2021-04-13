package com.tripkipedia.validator
/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class PasswordValidator(override var value: String?,var prefix: String?) : Validatable {
    override var msg: String? = null

    constructor(value: String?) : this(value,"") {
        this.value = value
    }

    override fun isValid(): Boolean {
//        val passwordPatten = Pattern.compile(
//                "[a-zA-Z0-9\\!\\@\\#\\$\\s]{8,20}$")
        if (isEmpty().not()) {
            return false
        } else if (value!!.length < 8) {
            msg = "Min. 8 character is required."
            return false
        } else if (value!!.length > 20) {
            msg = "Max. 20 character is required."
            return false
        } /*else if (!passwordPatten.matcher(value!!).matches()) {
            msg = "Invalid password"
            return false
        }*/
        return true
    }

    private fun isEmpty(): Boolean {
        if (value == null) {
            msg = "$prefix Password is required."
            return false
        } else if (value!!.isEmpty()) {
            msg = "$prefix Password is required."
            return false
        }
        return true
    }

    fun isValidPassword(): Boolean {
        if (isEmpty().not()) {
            return false
        } else if (value!!.length < 8 || value!!.length > 20) {
            msg = "Invalid password"
            return false
        }
        return true
    }


    fun isValidConfirmPassword(cPassword: CharSequence?): Boolean {
        msg = "New password and confirm password should match."
        return (value.toString() == cPassword.toString())
    }

    fun isOldPasswordMatchedNewPassword(oldPassword: CharSequence?): Boolean {
        msg = "Old password and new password should not be same."
        return (value.toString() == oldPassword.toString())
    }
}