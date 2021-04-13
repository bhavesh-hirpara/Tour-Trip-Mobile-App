package com.tripkipedia.apputils

import android.content.Context


/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
object UserPermissions {

    var roles: UserRoles? = null

    enum class UserRoles {
        ENDUSER, PROMOTOR, VENUEOWNER, ENDUSERPROMOTERVENUEOWNER
    }

    fun setUserRole(role: String,context: Context) {
        Utils.setPref(context, Constant.ROLE, role)
        if (role == "EndUser") {
            roles = UserPermissions.UserRoles.ENDUSER
        } else if (role == "Promotor") {
            roles = UserPermissions.UserRoles.PROMOTOR
        } else if (role == "VenueOwner") {
            roles = UserPermissions.UserRoles.VENUEOWNER
        } else if (role == "EndUser,VenueOwner,Promotor") {
            roles = UserPermissions.UserRoles.ENDUSERPROMOTERVENUEOWNER
        }
    }

    fun isEndUser(): Boolean {

        if (roles == UserPermissions.UserRoles.ENDUSER) {
            return false
        } else if (roles == UserPermissions.UserRoles.PROMOTOR || roles == UserPermissions.UserRoles.VENUEOWNER ||  roles == UserPermissions.UserRoles.ENDUSERPROMOTERVENUEOWNER) {
            return true
        }
        return false
    }

    fun getUserRole(context: Context): String? {
        val role = Utils.getPref(context, Constant.ROLE, "");
        if (role == "EndUser") {
            roles = UserPermissions.UserRoles.ENDUSER
        } else if (role == "Promotor") {
            roles = UserPermissions.UserRoles.PROMOTOR
        } else if (role == "VenueOwner") {
            roles = UserPermissions.UserRoles.VENUEOWNER
        }else if (role == "EndUser,VenueOwner,Promotor") {
            roles = UserPermissions.UserRoles.ENDUSERPROMOTERVENUEOWNER
        }
        return Utils.getPref(context, Constant.ROLE, "");
    }

//    fun setLoggedInUser(userData: UserData,context: Context) {
//        Utils.setPref(context, Constant.USERDATA, Gson().toJson(userData))
//    }
//
//    fun getLoggedInUser(context: Context): UserData? {
//        val data = Utils.getPref(context, Constant.USERDATA, "")
//        try {
//            if (data!!.isEmpty().not()) {
//                return Gson().fromJson<UserData>(data,object : TypeToken<UserData>() {}.type)
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        return null
//    }
}