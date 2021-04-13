package com.tripkipedia.apputils.datamodel

import com.tripkipedia.apputils.Utils
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class EventDetail {

    fun getShortStartDate(): String {
        return Utils.getStartDate(startDate!!)
    }

    fun getShortEndDate(): String {
        return Utils.getEndDate(endDate!!)
    }

    fun getAgeRequirements(): String {
        return Utils.getAgeRequirement(ageRequirement)
    }

    fun getDistances(): String {
        return Utils.getDistanceVenueName(distance,venue!!.venueName)
    }

    fun isEventFree(): Boolean {
        return Utils.isEventFree(ticketRate)
    }

    fun getEventRate(): String {
        return Utils.getEventAmount(ticketRate)
    }

    fun getAttending(): String {
        return Utils.getAttending(attendingCount,notAttendingCount,maybeCount)
    }

    fun getEventModeStr(): String {
        return Utils.getEventModes(eventMode)
    }

    fun getEventStatusStr(): String {
        return Utils.getEventStatuses(eventStatus)
    }

    fun getTotalAvailability(): String {
        return Utils.getTotalAvailableTickets(availableTickets)
    }

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("venueId")
    @Expose
    var venueId: Int? = null
    @SerializedName("promoterId")
    @Expose
    var promoterId: Int? = null
    @SerializedName("eventName")
    @Expose
    var eventName: String? = null
    @SerializedName("venue")
    @Expose
    var venue: VenueDetail? = null
    @SerializedName("distance")
    @Expose
    var distance: Double? = null
    @SerializedName("startDate")
    @Expose
    var startDate: String? = null
    @SerializedName("city")
    @Expose
    var city: String? = null
    @SerializedName("endDate")
    @Expose
    var endDate: String? = null
    @SerializedName("availableTickets")
    @Expose
    var availableTickets: Int? = null
    @SerializedName("ticketRate")
    @Expose
    var ticketRate: Int? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("imageName")
    @Expose
    var imageName: String? = null
    @SerializedName("eventMode")
    @Expose
    var eventMode: Int? = null
    @SerializedName("eventStatus")
    @Expose
    var eventStatus: Int? = null
    @SerializedName("ageRequirement")
    @Expose
    var ageRequirement: Int? = null
    @SerializedName("attendingCount")
    @Expose
    var attendingCount: Int? = null
    @SerializedName("isLoggedInUserAttending")
    @Expose
    var isLoggedInUserAttending: Int? = null
    @SerializedName("notAttendingCount")
    @Expose
    var notAttendingCount: Int? = null
    @SerializedName("maybeCount")
    @Expose
    var maybeCount: Int? = null
    @SerializedName("createdOn")
    @Expose
    var createdOn: String? = null

}