package com.tripkipedia.ui.dashboard.datamodel

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class TypesData {
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
    var data: List<String>? = null
}