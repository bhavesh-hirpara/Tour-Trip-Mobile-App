package com.tripkipedia.apputils

import androidx.databinding.BaseObservable
/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class MenuItem : BaseObservable {

    lateinit var menuId: String
    var icon: Int = 0
    var name: String
    var isSelected: Boolean = false

    constructor(menuId: String,icon: Int,name: String) {
        this.menuId = menuId
        this.icon = icon
        this.name = name
    }

    constructor(icon: Int,name: String) {
        this.icon = icon
        this.name = name
    }

    constructor(menuId: String,icon: Int,name: String,isSelected: Boolean) {
        this.menuId = menuId
        this.icon = icon
        this.name = name
        this.isSelected = isSelected
    }
}
