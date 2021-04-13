package com.tripkipedia.apputils

import android.os.Environment
import java.io.File
/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
object Constant {
  const val FOLDER_NAME = ".diamfair"
  val FOLDER_RIDEINN_PATH = (Environment
          .getExternalStorageDirectory().absolutePath
          + File.separator
          + "DiamFair")
  const val USER_LATITUDE = "lat"
  const val FROM_PROFILE = "FROM_PROFILE"
  const val ISSOCIALLOGIN = "ISSOCIALLOGIN"
  const val miles = "miles"
  const val USER_LONGITUDE = "longi"
  const val LOGIN_INFO = "login_info"
  const val NO_DEVICE_ID = "No DeviceId"
  const val FINISH_ACTIVITY = "finish_activity"
  const val RESPONSE_FAILURE_CODE = 901
  const val RESPONSE_SUCCESS_CODE = 200
  const val VALIDATION_FAILED_CODE = 903
  const val EMAILID = "EmailId"
  const val ROLE = "role"
  const val FACEBOOK = "fb"
  const val GOOGLE = "Google"
  const val APP_JSON = "application/json"
  const val USERDATA = "UserData"
  const val LOCATION_UPDATED = "location_updated"
  const val LATITUDE = "LATITUDE"
  const val LONGITUDE = "LONGITUDE"
  const val SEARCHEVENTMODEL = "SearchEventModel"
  const val EVENTDETAIL = "EventDetail"
  const val SEARCH_EVENT_DATE_PATTERN = "MM/dd/yyyy"
  const val SEARCH_EVENT_TIME_PATTERN = "HH:mm"
  const val SEARCH_EVENT_12_TIME_PATTERN = "hh:mm"
  const val SHARE_FACEBOOK = "fb"
  const val SHARE_TWITTER = "Twitter"
  const val SHARE_INSTAGRAM = "Instagram"
  const val SHARE_OTHER = "Others"
  const val REJECTED = "Rejected"
  const val INACTIVE = "Inactive"
  const val ACTIVE = "Active"
  const val PUBLIC = "Public"
  const val PRIVATE = "Private"
  const val LISTINGTYPE = "ListingType"
  const val MANAGELIST = "ManageList"
  const val SEARCHLIST = "SearchList"
  const val TOKEN_ID = "tokenID"
  const val UID = "userId"
  const val WELCOME_INTRO_DATA = "WelcomeScreenIntroScreen"
  const val FROM = "from"
  const val EMAIL_CHANGE = "email_change"
  const val PHONE_CHANGE = "phone_change"
  const val MY_VEHICLE = "my_vehicle"
  const val IS_FIRST_TIME = "is_first_time"
  const val TYPE = "type"
  const val AGO_TYPE = "ago"
  const val SIGE_TYPE = "sige"


  const val WEBCAST = "webcast"
  const val NEWS = "news"
  const val DEPARTMENT = "DEPARTMENT"
  const val SCHOOLS = "schools"


  fun getSuccessCode(): Int {
    return RESPONSE_SUCCESS_CODE
  }

  fun getFailureCode(): Int {
    return RESPONSE_FAILURE_CODE
  }

  fun getValidationCode(): Int {
    return VALIDATION_FAILED_CODE
  }

}
