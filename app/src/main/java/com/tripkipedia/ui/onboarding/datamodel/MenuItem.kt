package com.tripkipedia.ui.onboarding.datamodel
/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class MenuItem {

    var image: Int
    var title: String
    var title_collect: String
    var color:String

    // var data : ArrayList<MovieData>


    // var data = ArrayList<String>()

    constructor(
        image: Int,
        title: String, title_collect: String, color: String
    ) {
        this.image = image
        this.title = title
        this.title_collect = title_collect
        this.color = color
    }
}