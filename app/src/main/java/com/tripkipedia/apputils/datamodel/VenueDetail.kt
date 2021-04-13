package com.tripkipedia.apputils.datamodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class VenueDetail {

    @SerializedName("venueTypeId")
    @Expose
    var venueTypeId: Int? = null
    @SerializedName("venueOwnerId")
    @Expose
    var venueOwnerId: Int? = null
    @SerializedName("venueName")
    @Expose
    var venueName: String? = null
    @SerializedName("companyName")
    @Expose
    var companyName: String? = null
    @SerializedName("companyTypeId")
    @Expose
    var companyTypeId: Int? = null
    @SerializedName("address1")
    @Expose
    var address1: String? = null
    @SerializedName("address2")
    @Expose
    var address2: String? = null
    @SerializedName("stateId")
    @Expose
    var stateId: Int? = null
    @SerializedName("cityId")
    @Expose
    var cityId: Int? = null
    @SerializedName("zipcode")
    @Expose
    var zipcode: String? = null
    @SerializedName("latitude")
    @Expose
    var latitude: Float? = null
    @SerializedName("longitude")
    @Expose
    var longitude: Float? = null
    @SerializedName("capacity")
    @Expose
    var capacity: Int? = null
    @SerializedName("isDeleted")
    @Expose
    var isDeleted: Boolean? = null
    @SerializedName("lastModifiedOn")
    @Expose
    var lastModifiedOn: String? = null
    @SerializedName("lastModifiedBy")
    @Expose
    var lastModifiedBy: String? = null
    @SerializedName("status")
    @Expose
    var status: Int? = null
    @SerializedName("hoursOfOperation")
    @Expose
    var hoursOfOperation: String? = null
    @SerializedName("closeHoursOfOperation")
    @Expose
    var closeHoursOfOperation: String? = null
    @SerializedName("createdOn")
    @Expose
    var createdOn: String? = null
    @SerializedName("isDeletedByVenueOwner")
    @Expose
    var isDeletedByVenueOwner: Boolean? = null
    @SerializedName("state")
    @Expose
    var state: Any? = null
    @SerializedName("cities")
    @Expose
    var cities: Any? = null
    @SerializedName("venueOwner")
    @Expose
    var venueOwner: Any? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
}