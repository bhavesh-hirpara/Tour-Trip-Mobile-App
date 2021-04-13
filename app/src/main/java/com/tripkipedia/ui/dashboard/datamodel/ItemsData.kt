package com.tripkipedia.ui.dashboard.datamodel

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class ItemsData {
    @SerializedName("status_code")
    @Expose
    var statusCode = 0

    constructor(statusCode: Int) {
        this.statusCode = statusCode
    }
    @SerializedName("status")
    @Expose
    var status = false

    @SerializedName("page")
    @Expose
    var page = 0

    @SerializedName("per_page")
    @Expose
    var perPage = 0

    @SerializedName("total_page")
    @Expose
    var totalPage = 0

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null

    class Datum {
        @SerializedName("item_id")
        @Expose
        var itemId: String? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("type")
        @Expose
        var type: String? = null

        @SerializedName("category")
        @Expose
        var category: String? = null

        @SerializedName("reviews")
        @Expose
        var reviews = 0

        @SerializedName("photo")
        @Expose
        var photo: String? = null

        @SerializedName("latitude")
        @Expose
        var latitude: String? = null

        @SerializedName("longitude")
        @Expose
        var longitude: String? = null
    }
}