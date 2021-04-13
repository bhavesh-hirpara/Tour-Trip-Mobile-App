package com.tripkipedia.ui.freebiesDetails.datamodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class FreebiesDetailsData {
    @SerializedName("status_code")
    @Expose
    var statusCode = 0

    constructor(statusCode: Int) {
        this.statusCode = statusCode
    }

    @SerializedName("status")
    @Expose
    var status = false

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null

    class Data {
        @SerializedName("item_id")
        @Expose
        var itemId: String? = null

        @SerializedName("uuid")
        @Expose
        var uuid: String? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("category")
        @Expose
        var category: String? = null

        @SerializedName("type")
        @Expose
        var type: String? = null

        @SerializedName("description")
        @Expose
        var description: String? = null

        @SerializedName("address")
        @Expose
        var address: Address? = null

        class Address {
            @SerializedName("block")
            @Expose
            var block: String? = null

            @SerializedName("streetName")
            @Expose
            var streetName: String? = null

            @SerializedName("floorNumber")
            @Expose
            var floorNumber: String? = null

            @SerializedName("unitNumber")
            @Expose
            var unitNumber: String? = null

            @SerializedName("buildingName")
            @Expose
            var buildingName: String? = null

            @SerializedName("postalCode")
            @Expose
            var postalCode: String? = null
        }
        @SerializedName("latitude")
        @Expose
        var latitude: String? = null

        @SerializedName("longitude")
        @Expose
        var longitude: String? = null

        @SerializedName("website")
        @Expose
        var website: String? = null

        @SerializedName("mobile")
        @Expose
        var mobile: String? = null

        @SerializedName("rating")
        @Expose
        var rating: String? = null

        @SerializedName("photo")
        @Expose
        var photo: String? = null

        @SerializedName("pin")
        @Expose
        var pin: String? = null

        @SerializedName("hours")
        @Expose
        var hours: List<Hour>? = null

        class Hour {
            @SerializedName("daily")
            @Expose
            var daily = false

            @SerializedName("openTime")
            @Expose
            var openTime: String? = null

            @SerializedName("closeTime")
            @Expose
            var closeTime: String? = null

            @SerializedName("day")
            @Expose
            var day: String? = null

            @SerializedName("description")
            @Expose
            var description: String? = null

            @SerializedName("sequenceNumber")
            @Expose
            var sequenceNumber = 0
        }
        @SerializedName("reviews")
        @Expose
        var reviews: List<Review>? = null

        class Review {
            @SerializedName("authorName")
            @Expose
            var authorName: String? = null

            @SerializedName("authorURL")
            @Expose
            var authorURL: String? = null

            @SerializedName("profilePhoto")
            @Expose
            var profilePhoto: String? = null

            @SerializedName("rating")
            @Expose
            var rating = 0

            @SerializedName("text")
            @Expose
            var text: String? = null

            @SerializedName("time")
            @Expose
            var time: String? = null

            @SerializedName("language")
            @Expose
            var language: String? = null
        }
    }
}